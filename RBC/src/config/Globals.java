package config;

import lejos.nxt.Motor;
import lejos.nxt.NXTRegulatedMotor;
import lejos.nxt.SensorPort;

public class Globals{
	
	public static final boolean debug = true;	
	
	//Strings
	public static final String introMsg = "Robot ready.";
	public static final String pressButton = "Press any button to start!";
	
	//Tracks
	public static final float wheelDiameter = 3.0f;
	public static final float trackWidth = 19f; 
	
	//Sensor Ports
	public static final SensorPort colorPort =  SensorPort.S4;
	public static final SensorPort irPort =  SensorPort.S2;
	public static final SensorPort compassPort =  SensorPort.S3;
	public static final SensorPort touchPort =  SensorPort.S1;
	
	//Motors
	public static final int travelSpeed = 70;
	public static final int rotateSpeed = 40;
	public static final int scoopSpeed = 100;
	public static final int scoopRotationAngle = 180;
	public static final NXTRegulatedMotor leftMotor =  Motor.A;
	public static final NXTRegulatedMotor rightMotor =  Motor.B;
	public static final NXTRegulatedMotor scoopMotor = Motor.C;
	
	//Compass Navigation
	public static int desiredAngle = 90;
	public static final int wallDistance = 150;
	public static final int minimunWallDistance = 50;
	
	//IR Navigation
	public static int timeBetweenIRMeasures = 1000;
	public static int idealWallDistance = 500;
	public static int estimatedMMperSec = 5000;
	
	//General Navigation
	public static final int backwardsDistance = 10;
	
	
	
}