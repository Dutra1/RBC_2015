package behaviours;

import config.Globals;
import lejos.nxt.SensorPort;
import lejos.nxt.TouchSensor;
import lejos.robotics.navigation.DifferentialPilot;
import lejos.robotics.subsumption.Behavior;

public class Turn implements Behavior{

	private DifferentialPilot pilot;
	private TouchSensor touch;
	
	public Turn (DifferentialPilot pilot, SensorPort touchPort) {
		this.pilot = pilot;
		this.touch = new TouchSensor(touchPort);
	}
	
	@Override
	public boolean takeControl() {
		return !pilot.isMoving() && !touch.isPressed();
	}

	@Override
	public void action() {
		pilot.setTravelSpeed(Globals.rotateSpeed);
		pilot.arc(-Globals.backwardsDistance + (Globals.wallDistance - Globals.widthDistanceToCenter), 90, false);
		pilot.setTravelSpeed(Globals.travelSpeed);
		pilot.forward();
	}

	@Override
	public void suppress() {}
}
