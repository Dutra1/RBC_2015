import lejos.nxt.Button;
import lejos.nxt.LCD;
import lejos.nxt.Motor;
import lejos.nxt.MotorPort;
import lejos.nxt.NXTRegulatedMotor;
import lejos.nxt.SensorPort;
import lejos.nxt.TachoMotorPort;
import lejos.nxt.addon.CompassHTSensor;
import lejos.nxt.addon.LnrActrFirgelliNXT;
import lejos.util.Delay;

public class Calibration {

	public static final NXTRegulatedMotor leftMotor =  Motor.A;
	public static final NXTRegulatedMotor rightMotor =  Motor.B;
	public static final TachoMotorPort mastMotor = MotorPort.C;
	
	public static final SensorPort compassPort =  SensorPort.S4;
	
	public static final int motorSpeed = 75;
	public static final int delay = 75 * 1000;
	 
	public static void main(String argv[]) {
		CompassHTSensor compass = new CompassHTSensor(compassPort);
		LnrActrFirgelliNXT mast = new LnrActrFirgelliNXT(mastMotor);
		
		//Start
		LCD.drawString("Compass calibration", 0, 0);
		LCD.drawString("Click any button to start", 0, 1);
		Button.waitForAnyPress();
		
		LCD.clear();
		LCD.drawString("Don't touch while it's calibrating", 0, 0);
		
		//Raise mast
		
		/*mast.setPower(100);
		mast.move(200, false);*/
		
		//Rotate Slowly
		leftMotor.setSpeed(motorSpeed);
		rightMotor.setSpeed(motorSpeed);
		
		leftMotor.forward();
		rightMotor.backward();
		
		//Calibrate
		compass.startCalibration();
		Delay.msDelay(delay);
		compass.stopCalibration();
		
		//Stop
		leftMotor.stop();
		rightMotor.stop();
		
		//LowerMast
		LCD.drawString("Click left or right to lower mast", 0, 0);
		int button = Button.waitForAnyPress();
		if ((button == Button.ID_LEFT) || (button == Button.ID_RIGHT)) {
			mast.move(-200, false);
		}
	}
}
