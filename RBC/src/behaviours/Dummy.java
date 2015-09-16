package behaviours;

import lejos.robotics.subsumption.Behavior;

public class Dummy implements Behavior {

	public Dummy() {}

	public boolean takeControl() {
		return true;
	}

	public void suppress() {}

	public void action() {
		Thread.yield();
	}

}