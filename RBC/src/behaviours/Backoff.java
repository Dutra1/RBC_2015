package behaviours;

import config.Globals;
import lejos.nxt.LCD;
import lejos.nxt.SensorPort;
import lejos.nxt.Sound;
import lejos.nxt.TouchSensor;
import lejos.nxt.addon.OpticalDistanceSensor;
import lejos.robotics.navigation.DifferentialPilot;
import lejos.robotics.subsumption.Behavior;
import lejos.util.Delay;

public class Backoff implements Behavior{
	
	private DifferentialPilot pilot;
	private SensorPort touchPort;
	OpticalDistanceSensor ir;
	
	public Backoff (DifferentialPilot pilot, SensorPort touchPort, SensorPort irPort) {
		this.pilot = pilot;
		this.touchPort = touchPort;
		this.ir = new OpticalDistanceSensor(irPort);
	}

	@Override
	public boolean takeControl() {
		//TouchSensor touch = new TouchSensor(touchPort);
		//return touch.isPressed() && pilot.getMovementIncrement() >= 0;
		//return pilot.isStalled();
		int distance = ir.getDistance();
		boolean hasToStop = distance < 170 && distance > 50;
		if (hasToStop) Sound.playTone(distance + 100, 100);
		return hasToStop;
	}

	@Override
	public void action() {
		pilot.travel(-Globals.backwardsDistance, true);
		Delay.msDelay(Globals.backDelay);
	}

	@Override
	public void suppress() {}
}
