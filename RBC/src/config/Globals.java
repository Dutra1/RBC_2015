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
	public static final SensorPort touchPort =  SensorPort.S1;
	public static final SensorPort irPort =  SensorPort.S2;
	public static final SensorPort compassPort =  SensorPort.S3;
	
	//Motors
	public static final int travelSpeed = 70;
	public static final int rotateSpeed = 40;
	public static final int scoopSpeed = 100;
	public static final int scoopRotationAngle = 180;
	public static final NXTRegulatedMotor leftMotor =  Motor.A;
	public static final NXTRegulatedMotor rightMotor =  Motor.B;
	public static final NXTRegulatedMotor scoopMotor = Motor.C;
	
	//General Navigation
	public static final int backwardsDistance = 10;
	public static final int wallDistance = 150; //mm
		
	//Vector Navigation
	public static int desiredAngle = 90;
	public static final int minimunWallDistance = 50;
	
	//Simple Navigation
	public static int distanceTolerance = 50; //mm
	public static int turnAngle = 30;
	public static int angleTolerance = 10;
	public static int turnRadius = 10;
}