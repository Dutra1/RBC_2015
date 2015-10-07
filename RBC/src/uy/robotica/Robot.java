package uy.robotica;

import behaviours.FollowWall;
import behaviours.IRFollowWall;
import lejos.nxt.Button;
import lejos.nxt.LCD;
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

		Music music = new Music();
		//music.playMarioDeath();
		
		LCD.drawString(Globals.introMsg, 0, 0);
		LCD.drawString(Globals.pressButton, 0, 1);
		
		Button.waitForAnyPress();
		
		//Behavior b1 = new FollowWall(pilot,Globals.compassPort,Globals.irPort,Globals.touchPort);
		Behavior b1 = new IRFollowWall(pilot, Globals.irPort,Globals.touchPort);
		Behavior [] bArray = {b1};
		Arbitrator a = new Arbitrator(bArray);
		a.start();
		
	}
	
	

}
