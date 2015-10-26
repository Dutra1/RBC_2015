package behaviours;

import config.Globals;
import lejos.nxt.SensorPort;
import lejos.nxt.TouchSensor;
import lejos.robotics.navigation.DifferentialPilot;
import lejos.robotics.subsumption.Behavior;

public class Backoff implements Behavior{
	
	private DifferentialPilot pilot;
	private SensorPort touchPort;
	
	public Backoff (DifferentialPilot pilot, SensorPort touchPort) {
		this.pilot = pilot;
		this.touchPort = touchPort;
	}

	@Override
	public boolean takeControl() {
		TouchSensor touch = new TouchSensor(touchPort);
		return touch.isPressed() && pilot.getMovementIncrement() >= 0;
		
	}

	@Override
	public void action() {
		pilot.stop();
		pilot.travel(-Globals.backwardsDistance, false);
	}

	@Override
	public void suppress() {}
}
