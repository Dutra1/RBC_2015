import lejos.nxt.NXTRegulatedMotor;
import lejos.nxt.addon.CompassHTSensor;
import lejos.robotics.subsumption.Behavior;
import lejos.util.Delay;

public class Kick implements Behavior{

	private NXTRegulatedMotor kicker = Globals.kicker;
	private CompassHTSensor compass;
	
	public Kick(){
		compass = new CompassHTSensor(Globals.compassPort);
		compass.resetCartesianZero();
	}
	
	@Override
	public boolean takeControl() {
		boolean isCorrectAngle = Math.abs(compass.getDegreesCartesian() - Globals.idealAngle) < Globals.allowedAngleError;
		return (Slave.ballColor == BallColor.ORANGE) && isCorrectAngle;
	}

	@Override
	public void action() {
		kicker.setSpeed(Globals.kickSpeed);
		kicker.rotate(-90 / Globals.kickerGearReduction);
		kicker.rotate(450 / Globals.kickerGearReduction);
		Delay.msDelay(Globals.msAfterKick);
		Slave.ballColor = BallColor.NOTHING;
	}

	@Override
	public void suppress() {}

}
