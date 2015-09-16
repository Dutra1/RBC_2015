package behaviours;


import config.Globals;
import lejos.nxt.LCD;
import lejos.nxt.Motor;
import lejos.nxt.NXTRegulatedMotor;
import lejos.nxt.TouchSensor;
import lejos.robotics.navigation.DifferentialPilot;
import lejos.robotics.subsumption.Behavior;


public class ShootCrossbow implements Behavior {

	private boolean suppressed = false;
	private TouchSensor ts;
	private NXTRegulatedMotor m;

	public ShootCrossbow(TouchSensor ts) {
		this.ts = ts;
		m = Globals.crossbowMotor;
		m.setSpeed(3000);
	}

	public boolean takeControl() {
		return ts.isPressed();
	}

	public void suppress() {
		suppressed = true;
	}

	public void action() {
		suppressed = false;
		m.backward();
		while (!suppressed ) {
			Thread.yield();
		}
		m.stop();
	}

}