import lejos.nxt.Motor;
import lejos.nxt.MotorPort;
import lejos.nxt.NXTRegulatedMotor;
import lejos.nxt.SensorPort;
import lejos.robotics.Color;

public class Globals{
	
	public static final boolean debug = true;	
	
	//Color Sensor
	public static final SensorPort colorPort = SensorPort.S3;
	public static final boolean enableColorSensorFL = false;
	public static final int colorSensorFLColor = Color.WHITE;
	public static final int colorSensorSamples = 5;
	public static final int msBallInPosition = 500;
	public static final int msBetweenReadings = 50;
	
	//Relations - Measure correctly!
	public static final float[] orangeColorRelations = {0.95f, 2.1f, 0.5f};
	public static final float[] yellowColorRelations = {0.85f, 1.05f, 1.15f};
	public static final float[] lightBlueColorRelations = {0.85f, 1.3f, 0.8f};
	public static final float[] purpleColorRelations = {1.5f, 0.66f, 1};
	
	//Compass
	public static final SensorPort compassPort = SensorPort.S4;
	public static final int idealAngle = 270;
	public static final int allowedAngleError = 20;
	
	//Kicker
	public static final NXTRegulatedMotor kicker = Motor.C;
	public static final int kickSpeed = 2000;
	public static final int letGoSpeed = 100;
	public static final int kickerGearReduction = 3;
	public static final int msAfterKick = 100;
	
	//Linear
	public static final MotorPort mastMotor = MotorPort.B;
}