import lejos.nxt.Button;
import lejos.nxt.LCD;
import lejos.nxt.MotorPort;
import lejos.nxt.SensorPort;
import lejos.nxt.Sound;
import lejos.nxt.TachoMotorPort;
import lejos.nxt.addon.CompassHTSensor;
import lejos.nxt.addon.LnrActrFirgelliNXT;
import lejos.util.Delay;

public class CompassTesting {
	
	public static final TachoMotorPort mastMotor = MotorPort.C;
	
	public static final SensorPort compassPort =  SensorPort.S4;
	
	public static final int motorSpeed = 100;
	public static final int delay = 100;
	
	public static void main(String argv[]) {
		CompassHTSensor compass = new CompassHTSensor(compassPort);
		LnrActrFirgelliNXT mast = new LnrActrFirgelliNXT(mastMotor);
		
		//Start
		LCD.drawString("Compass testing", 0, 1);
		LCD.drawString("Click to start", 0, 0);
		Button.waitForAnyPress();
		
		//Raise mast
		mast.setPower(100);
		mast.move(200, false);
		
		//Test
		compass.resetCartesianZero();
		LCD.clear();
		LCD.drawString("Click to stop", 0, 0);
		
		boolean stop = false;
		while(!stop) {
			LCD.clear(3);
			LCD.drawString("Angle: " + compass.getDegreesCartesian(), 0, 3);
			Sound.playTone((int) Math.abs(compass.getDegreesCartesian()) + 100, delay);
			Delay.msDelay(delay);
			
			if (Button.readButtons() != 0) stop = true;
		}
		
		//LowerMast
		LCD.drawString("Click left or right to lower mast", 0, 0);
		int button = Button.waitForAnyPress();
		if ((button == Button.ID_LEFT) || (button == Button.ID_RIGHT)) {
			mast.move(-200, false);
		}
	}
}
