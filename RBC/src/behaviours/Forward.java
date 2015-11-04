package behaviours;

import config.Globals;
import lejos.nxt.SensorPort;
import lejos.nxt.Sound;
import lejos.nxt.UltrasonicSensor;
import lejos.robotics.navigation.DifferentialPilot;
import lejos.robotics.subsumption.Behavior;

public class Forward implements Behavior{

	private DifferentialPilot pilot;
	private UltrasonicSensor usL;
	private UltrasonicSensor usR;
	private boolean supressed;
	
	public Forward(DifferentialPilot pilot, SensorPort usLPort, SensorPort usRPort) {
		this.pilot = pilot;
		this.usL = new UltrasonicSensor(usLPort);
		this.usR = new UltrasonicSensor(usRPort);
		
		supressed = false;
	}
	
	@Override
	public boolean takeControl() {
		return true;
	}
	
	@Override
	public void action() {
		supressed = false;
		pilot.forward();
		
		int minLeftDistance = 500;
		int minRightDistance = 500;
		int maxLeftDistance = 0;
		int maxRightDistance = 0;
		
		while(!supressed) {
			int dl = usL.getDistance();
			int dr = usR.getDistance();
			
			if (dl < minLeftDistance) minLeftDistance = dl;
			if (dr < minRightDistance) minRightDistance = dr;
			if (dl > maxLeftDistance) maxLeftDistance = dl;
			if (dr > maxRightDistance) maxRightDistance = dr;
			
			if ((dr < Globals.dangerZone) || (dl < Globals.dangerZone)) {
				supressed = true;
				Sound.playTone(120, 1000);;
			}
			
			Thread.yield();
		}
		
		pilot.stop();
		
		boolean isRobotLeft = isRobot(minLeftDistance, maxLeftDistance);
		boolean isRobotRight = isRobot(minRightDistance, maxRightDistance);
		boolean tooCloseLeft = tooClose(isRobotLeft, minLeftDistance);
		boolean tooCloseRight = tooClose(isRobotRight, minRightDistance);
		boolean tooFarLeft = tooFar(isRobotLeft, maxLeftDistance);
		boolean tooFarRight = tooFar(isRobotRight, maxRightDistance);
		
		if (isRobotLeft) Sound.beep();
		if (isRobotRight) Sound.twoBeeps();
		
		//Flocking
		if (tooCloseLeft && tooCloseRight) {
			Turn.setTurnInPlace();
		} else if (tooCloseLeft) {
			Turn.setNextTurnRight();
		} else if (tooCloseRight) {
			Turn.setNextTurnLeft();
		}/* else if (tooFarLeft) {
			Turn.setNextTurnLeft();
		} else if (tooFarRight) {
			Turn.setNextTurnRight();
		}*/
	}

	@Override
	public void suppress() {
		supressed = true;
		pilot.stop();
	}
	
	public boolean isRobot(int min, int max) {
		return (max - min) > Globals.robotFingerprint;
	}
	
	public boolean tooClose(boolean isRobot, int minDist) {
		return (isRobot && (minDist < Globals.minRobotDistance)) || (!isRobot && (minDist < Globals.minWallDistance)); 
	}
	
	public boolean tooFar(boolean isRobot, int maxDist) {
		return isRobot && (maxDist > Globals.maxRobotDistance);
	}
}











