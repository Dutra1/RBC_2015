package behaviours;

import java.util.ArrayList;
import java.util.List;

import config.Globals;
import lejos.nxt.LCD;
import lejos.nxt.SensorPort;
import lejos.nxt.Sound;
import lejos.nxt.TouchSensor;
import lejos.robotics.navigation.DifferentialPilot;
import lejos.robotics.subsumption.Behavior;
import lejos.util.Delay;

public class Turn implements Behavior{

	private DifferentialPilot pilot;
	private TouchSensor touch;
	private static boolean nextTurn; //0 left - 1 right
	private static boolean turnInPlace;

	public Turn (DifferentialPilot pilot, SensorPort touchPort) {
		this.pilot = pilot;
		this.touch = new TouchSensor(touchPort);
		
		nextTurn = false;
		turnInPlace = false;
	}
	
	@Override
	public boolean takeControl() {
		return !pilot.isMoving() && !touch.isPressed();
	}

	@Override
	public void action() {
		pilot.setTravelSpeed(Globals.rotateSpeed);
		if (turnInPlace) {
			if (nextTurn) {
				//Right in place
				pilot.steer(-200, -180);
			} else {
				//Left in place
				pilot.steer(200, 180);
			}
		} else { 
			if (nextTurn) {
				//Right
				pilot.steer(-100, -180);
			} else {
				//Left
				pilot.steer(100, 180);
			}
		}
			
		nextTurn = !nextTurn;
		turnInPlace = false;
		
		pilot.setTravelSpeed(Globals.travelSpeed);
		pilot.forward();
		Delay.msDelay(Globals.isMovingDelay);
	}

	@Override
	public void suppress() {}
	
	public static void calculateNextTurn(List<Integer> leftMeasures, List<Integer> rightMeasures) {
		List<Double> filteredLeft = applySquareFilter(leftMeasures, Globals.windowSize);
		List<Double> filteredRight = applySquareFilter(rightMeasures, Globals.windowSize);
		
		double leftVariance = getVariance(filteredLeft);
		double rightVariance = getVariance(filteredRight);
		
		boolean isRobotRight = rightVariance > leftVariance;
		boolean isRobotLeft = !isRobotRight;
		
		double minLeftDistance = getMinimun(filteredLeft);
		
		double minRightDistance = getMinimun(filteredRight);
		
		boolean tooCloseLeft = tooClose(!isRobotLeft, minLeftDistance);
		boolean tooCloseRight = tooClose(isRobotRight, minRightDistance);
		boolean tooFarLeft = tooFar(isRobotLeft, minLeftDistance);
		boolean tooFarRight = tooFar(isRobotRight, minRightDistance);
		
		if (isRobotLeft) Sound.beep();
		else if (isRobotRight) Sound.twoBeeps();
		LCD.clear();
		LCD.drawString("Cant" + filteredLeft.size(), 0, 5);
		LCD.drawString("Left var" + leftVariance, 0, 6);
		LCD.drawString("Right var" + rightVariance, 0, 7);
		
		//Flocking
		if (tooCloseLeft && tooCloseRight) {
			setTurnInPlace();
		} else if (tooCloseLeft) {
			setNextTurnRight();
		} else if (tooCloseRight) {
			setNextTurnLeft();
		} else if (tooFarLeft) {
			setNextTurnLeft();
		} else if (tooFarRight) {
			setNextTurnRight();
		}
		
	}
	
	private static List<Double> applySquareFilter(List<Integer> measures, int windowSize) {
		List<Double> filtered = new ArrayList<>();
		
		for (int i = 0; i < measures.size(); i++) {
			Double filt = 0d;
			for (int w = 0; w < windowSize; w++) {
				filt += getValueWithLimits(measures, i - windowSize / 2 + w);
			}
			
			filtered.add(i, filt / windowSize);
		}
		
		return filtered;
	}
	
	private static Integer getValueWithLimits(List<Integer> list, int index) {
		int ind;
		
		if (index < 0) ind = 0;
		else if (index >= list.size()) ind = list.size() - 1;
		else ind = index;
		
		return list.get(ind);
	}
	
	private static double getVariance(List<Double> measures) {
		double total = 0;
		for (Double measure : measures) {
			total += measure;
		}
		double avg = total / measures.size();
		
		double notReallyVariance = 0;
		for (Double measure : measures) {
			notReallyVariance += Math.abs(measure - avg);
		}
		
		return notReallyVariance;
	}
	
	private static double getMinimun(List<Double> measures) {
		double min = Double.MAX_VALUE;
		for (Double measure : measures) {
			if (measure < min) min = measure;
		}
		return min;
	}
	
	private static boolean tooClose(boolean isRobot, Double minDist) {
		return (isRobot && (minDist < Globals.minRobotDistance)) || (!isRobot && (minDist < Globals.minWallDistance)); 
	}
	
	private static boolean tooFar(boolean isRobot, Double minDist) {
		return isRobot && (minDist > Globals.maxRobotDistance);
	}
	
	private static void setNextTurnLeft() {
		nextTurn = false;
	}
	
	private static void setNextTurnRight() {
		nextTurn = true;
	}
	
	private static void setTurnInPlace() {
		turnInPlace = true;
	}
}
