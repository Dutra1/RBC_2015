package uy.robotica;

import behaviours.Dummy;
import behaviours.FollowWall;
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

	public static boolean holding = false;
	
	public Robot(){
		
	}
	
	
	public static void main(String argv[]) {

		pilot = new DifferentialPilot(Globals.wheelDiameter, Globals.trackWidth, Globals.leftMotor,Globals.rightMotor,true);

		
		LCD.drawString(Globals.introMsg, 0, 0);
		LCD.drawString(Globals.pressButton, 0, 1);
		
		Button.waitForAnyPress();
		
		Behavior b1 = new FollowWall(pilot,Globals.compassPort,Globals.irPort,Globals.touchPort);
		Behavior [] bArray = {b1};
		Arbitrator a = new Arbitrator(bArray);
		a.start();
		
	}
	
	

}
