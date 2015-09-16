package behaviours;


import config.Globals;
import lejos.nxt.LCD;
import lejos.robotics.navigation.DifferentialPilot;
import lejos.robotics.subsumption.Behavior;


public class Wander implements Behavior {

	private boolean suppressed = false;
	DifferentialPilot pilot;


	public Wander(DifferentialPilot pilot) {

		this.pilot = pilot;
	}

	public boolean takeControl() {
		return true;
	}

	public void suppress() {
		suppressed = true;
	}

	public void action() {
		suppressed = false;
		pilot.forward();
		while (!suppressed ) {
			Thread.yield();
		}
		if (Globals.debug) LCD.drawString("Sali del comportamiento", 0, 4);

		pilot.stop();

	}

}