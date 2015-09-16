

import lejos.nxt.Motor;
import lejos.nxt.NXTRegulatedMotor;
import lejos.nxt.SensorPort;
import lejos.robotics.Color;

public class Globals{
	
	public static final boolean debug = true;	
	
	//Strings
	public static final String introMsg = "Robot ready.";
	public static final String pressButton = "Press any button to start!";
	
	//Color Sensor
	public static final boolean enableColorSensorFL = true;
	public static final int colorSensorFLColor = Color.WHITE;
	public static final int colorSensorSamples = 5;
	
	//Sensor Ports
	public static final SensorPort colorPort =  SensorPort.S4;
	
	//Motors
	public static final int kickSpeed = 2000;
	public static final int letGoSpeed = 40;
	public static final NXTRegulatedMotor kicker =  Motor.C;
}