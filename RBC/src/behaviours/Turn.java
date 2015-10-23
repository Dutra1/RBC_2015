package behaviours;

import config.Globals;
import lejos.nxt.SensorPort;
import lejos.nxt.TouchSensor;
import lejos.robotics.navigation.DifferentialPilot;
import lejos.robotics.subsumption.Behavior;

public class Turn implements Behavior{

	private DifferentialPilot pilot;
	private TouchSensor touch;
	private boolean nextTurn; //0 left - 1 right
	
	public Turn (DifferentialPilot pilot, SensorPort touchPort) {
		this.pilot = pilot;
		this.touch = new TouchSensor(touchPort);
		
		nextTurn = false;
	}
	
	@Override
	public boolean takeControl() {
		return !pilot.isMoving() && !touch.isPressed();
	}

	@Override
	public void action() {
		pilot.setTravelSpeed(Globals.rotateSpeed);
		if (nextTurn) {
			pilot.steer(-100, 180);
		} else {
			pilot.steer(100, 180);
		}
		nextTurn = !nextTurn;
		
		pilot.setTravelSpeed(Globals.travelSpeed);
		pilot.forward();
	}

	@Override
	public void suppress() {}
}
