package behaviours;

import config.Globals;
import lejos.nxt.SensorPort;
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
	
	public static void setNextTurnLeft() {
		nextTurn = false;
	}
	
	public static void setNextTurnRight() {
		nextTurn = true;
	}
	
	public static void setTurnInPlace() {
		turnInPlace = true;
	}
}
