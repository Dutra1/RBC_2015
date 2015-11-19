package behaviours;

import config.Globals;
import lejos.nxt.NXTRegulatedMotor;
import lejos.robotics.navigation.DifferentialPilot;
import lejos.robotics.subsumption.Behavior;

public class LiftLoader implements Behavior{

	private DifferentialPilot pilot;
	private NXTRegulatedMotor scoop;
	
	public LiftLoader (DifferentialPilot pilot, NXTRegulatedMotor scoop) {
		this.pilot = pilot;
		this.scoop = scoop;
	}
	
	@Override
	public boolean takeControl() {
		return pilot.isMoving() && (pilot.getMovementIncrement() < 0) && (Math.abs(pilot.getAngleIncrement()) < 30) && scoop.getTachoCount() > -30;
	}

	@Override
	public void action() {
		scoop.setSpeed(Globals.liftScoopSpeed);
		scoop.rotateTo(-Globals.scoopRotationAngle, false);
		
		scoop.setSpeed(Globals.dropScoopSpeed);
		scoop.rotateTo(0, false);
		
		scoop.flt();
	}

	@Override
	public void suppress() {}

}
