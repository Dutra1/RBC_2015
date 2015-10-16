package behaviours;

import config.Globals;
import lejos.nxt.SensorPort;
import lejos.nxt.TouchSensor;
import lejos.robotics.navigation.DifferentialPilot;
import lejos.robotics.subsumption.Behavior;

public class Backoff implements Behavior{
	
	private DifferentialPilot pilot;
	private TouchSensor touch;
	
	public Backoff (DifferentialPilot pilot, SensorPort touchPort) {
		this.pilot = pilot;
		this.touch = new TouchSensor(touchPort);
	}

	@Override
	public boolean takeControl() {
		return touch.isPressed();
	}

	@Override
	public void action() {
		pilot.setTravelSpeed(Globals.travelSpeed);
		pilot.travel(-Globals.backwardsDistance, true);
		pilot.stop();
	}

	@Override
	public void suppress() {}
}
