package uy.robotica;

import behaviours.Dummy;
import behaviours.HitWall;
import behaviours.ShootCrossbow;
import behaviours.Wander;
import lejos.nxt.Button;
import lejos.nxt.ColorSensor;
import lejos.nxt.LCD;
import lejos.nxt.TouchSensor;
import lejos.nxt.UltrasonicSensor;
import lejos.nxt.addon.CompassHTSensor;
import lejos.robotics.navigation.DifferentialPilot;
import lejos.robotics.subsumption.Arbitrator;
import lejos.robotics.subsumption.Behavior;
import config.Globals;


public class Robot {
	
	public static DifferentialPilot pilot;
	public static Map map;
	public static float x;
	public static float y;
	public static boolean holding = false;
	
	public Robot(){
		
	}
	
	
	public static void main(String argv[]) {

		pilot = new DifferentialPilot(Globals.wheelDiameter, Globals.trackWidth, Globals.leftMotor,Globals.rightMotor,true);
		
		CompassHTSensor compass = new CompassHTSensor(Globals.compassPort);
		compass.resetCartesianZero();
		
		UltrasonicSensor us1 = new UltrasonicSensor(Globals.distance1Port);
		TouchSensor ts1 = new TouchSensor(Globals.touch1Port);
		
		ColorSensor cs = new ColorSensor(Globals.colorPort);
		//Enables or disables Flood Light
		if (Globals.enableColorSensorFL)
			cs.setFloodlight(Globals.colorSensorFLColor);
		else
			cs.setFloodlight(false);

		
		LCD.drawString(Globals.introMsg, 0, 0);
		LCD.drawString(Globals.pressButton, 0, 1);
		
		Button.waitForAnyPress();
		
		Behavior b1 = new Wander(pilot);
		Behavior b2 = new HitWall(pilot,us1,ts1);
		Behavior b3 = new ShootCrossbow(ts1);
		Behavior b4 = new Dummy();
		Behavior [] bArray = {b1,b2};
		Arbitrator a = new Arbitrator(bArray);
		a.start();
		
	}
	
	

}
