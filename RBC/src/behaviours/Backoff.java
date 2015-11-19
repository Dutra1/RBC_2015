package behaviours;

import config.Globals;
import lejos.nxt.SensorPort;
import lejos.nxt.addon.OpticalDistanceSensor;
import lejos.robotics.navigation.DifferentialPilot;
import lejos.robotics.subsumption.Behavior;
import lejos.util.Delay;

public class Backoff implements Behavior{
	
	private DifferentialPilot pilot;
	OpticalDistanceSensor ir;
	
	public Backoff (DifferentialPilot pilot, SensorPort irPort) {
		this.pilot = pilot;
		this.ir = new OpticalDistanceSensor(irPort);
	}

	@Override
	public boolean takeControl() {
		int distance = ir.getDistance();
		return (distance < Globals.IRMaxWallDistance) && (distance > Globals.IRMinSafeDistance);
	}

	@Override
	public void action() {
		pilot.travel(-Globals.backwardsDistance, true);
		Delay.msDelay(Globals.backDelay);
	}

	@Override
	public void suppress() {}
}
