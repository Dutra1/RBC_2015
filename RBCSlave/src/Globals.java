import lejos.nxt.Motor;
import lejos.nxt.NXTRegulatedMotor;
import lejos.nxt.SensorPort;
import lejos.robotics.Color;

public class Globals{
	
	public static final boolean debug = true;	
	
	//Color Sensor
	public static final SensorPort colorPort = SensorPort.S4;
	public static final boolean enableColorSensorFL = true;
	public static final int colorSensorFLColor = Color.WHITE;
	public static final int colorSensorSamples = 5;
	public static final int msBallInPosition = 500;
	public static final int msBetweenReadings = 50;
	
	public static final int[] nothingColorRanges = {0,15, 0,15, 0,15};
	public static final int[] orangeColorRanges = {70,130, 70,140, 20,70};
	public static final int[] lightblueColorRanges = {100,160, 100,180, 80,150};
	public static final int[] purpleColorRanges = {50,120, 40,80, 60,120};
	
	//Compass
	public static final SensorPort compassPort = SensorPort.S1;
	public static final int idealAngle = 90;
	public static final int allowedAngleError = 20;
	
	//Kicker
	public static final NXTRegulatedMotor kicker = Motor.C;
	public static final int kickSpeed = 2000;
	public static final int letGoSpeed = 100;
	public static final int kickerGearReduction = 3;
	public static final int msAfterKick = 100;
	
	//Lineal
	//public static final Linear
}