import lejos.nxt.ColorSensor;
import lejos.robotics.subsumption.Behavior;
import lejos.util.Delay;

public class SenseColor implements Behavior{

	private ColorSensor cs = Globals.enableColorSensorFL ? new ColorSensor(Globals.colorPort, Globals.colorSensorFLColor) : new ColorSensor(Globals.colorPort);
	
	@Override
	public boolean takeControl() {
		return true;
	}

	@Override
	public void action() {
		BallColor isItNothing = Commons.getBallColor(cs);
		if (isItNothing != BallColor.NOTHING) {
			Delay.msDelay(Globals.msBallInPosition);
    		Slave.ballColor = Commons.getBallColor(cs);
		}
	}

	@Override
	public void suppress() {}

}
