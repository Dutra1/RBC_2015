import lejos.nxt.NXTRegulatedMotor;
import lejos.robotics.subsumption.Behavior;
import lejos.util.Delay;

public class LetGo implements Behavior{

	private NXTRegulatedMotor kicker = Globals.kicker;
	
	@Override
	public boolean takeControl() {
		return (Slave.ballColor == BallColor.LIGHTBLUE) || (Slave.ballColor == BallColor.PURPLE);
	}

	@Override
	public void action() {
		kicker.setSpeed(Globals.letGoSpeed);
		kicker.rotate(60 / Globals.kickerGearReduction);
		kicker.rotate(-60 / Globals.kickerGearReduction);
		Delay.msDelay(Globals.msAfterKick);
		Slave.ballColor = BallColor.NOTHING;
	}

	@Override
	public void suppress() {}

}
