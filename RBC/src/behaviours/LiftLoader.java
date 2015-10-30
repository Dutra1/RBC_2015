package behaviours;

import config.Globals;
import lejos.nxt.NXTRegulatedMotor;
import lejos.nxt.SensorPort;
import lejos.nxt.TouchSensor;
import lejos.robotics.navigation.DifferentialPilot;
import lejos.robotics.subsumption.Behavior;

public class LiftLoader implements Behavior{

	private DifferentialPilot pilot;
	private NXTRegulatedMotor scoop;
	private TouchSensor touch;
	
	public LiftLoader (DifferentialPilot pilot, NXTRegulatedMotor scoop, SensorPort touchPort) {
		this.pilot = pilot;
		this.scoop = scoop;
		this.touch = new TouchSensor(touchPort);
	}
	
	@Override
	public boolean takeControl() {
		return pilot.isMoving() && (pilot.getMovementIncrement() < 0) && (Math.abs(pilot.getAngleIncrement()) < 30) && !touch.isPressed() && scoop.getTachoCount() > -30;
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
