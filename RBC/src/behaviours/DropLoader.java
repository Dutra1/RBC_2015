package behaviours;

import lejos.nxt.NXTRegulatedMotor;
import lejos.robotics.subsumption.Behavior;

public class DropLoader implements Behavior{

	private NXTRegulatedMotor scoop;
	
	public DropLoader (NXTRegulatedMotor scoop) {
		this.scoop = scoop;
	}
	
	@Override
	public boolean takeControl() {
		return !scoop.isMoving() && scoop.getTachoCount() <= -150;
	}

	@Override
	public void action() {
		scoop.rotateTo(0, true);	
	}

	@Override
	public void suppress() {}
}
