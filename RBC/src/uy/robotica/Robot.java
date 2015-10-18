package uy.robotica;

import behaviours.Backoff;
import behaviours.DropLoader;
import behaviours.EvenSimplerFollowWall;
import behaviours.LiftLoader;
import behaviours.SimplerFollowWall;
import behaviours.Turn;
import lejos.nxt.Button;
import lejos.nxt.LCD;
import lejos.robotics.navigation.DifferentialPilot;
import lejos.robotics.subsumption.Arbitrator;
import lejos.robotics.subsumption.Behavior;
import config.Globals;


public class Robot {
	
	public static DifferentialPilot pilot;

	public static Communication comm;
	public static CommDebugger commdebugger;
	public static String behavior;
	
	//State?
	
	public static void main(String argv[]) {

		pilot = new DifferentialPilot(Globals.wheelDiameter, Globals.trackWidth, Globals.leftMotor, Globals.rightMotor, true);
		pilot.setTravelSpeed(Globals.travelSpeed);
		Globals.scoopMotor.setSpeed(Globals.scoopSpeed);
		
		LCD.drawString(Globals.introMsg, 0, 0);
		LCD.drawString(Globals.pressButton, 0, 1);
		
		/*Music music = new Music();
		music.playMarch();*/
		
		Button.waitForAnyPress();
		LCD.clear();
		
		comm = new Communication();
	    comm.start();
	    
	    /*commdebugger = new CommDebugger();
	    commdebugger.start();*/
		
		//Behavior esfw = new EvenSimplerFollowWall(pilot);
		Behavior sfw = new SimplerFollowWall(pilot, Globals.irPort, Globals.compassPort);
		Behavior t = new Turn(pilot, Globals.touchPort);
		Behavior dl = new DropLoader(Globals.scoopMotor);
		Behavior ll = new LiftLoader(pilot, Globals.scoopMotor, Globals.touchPort);
		Behavior bo = new Backoff(pilot, Globals.touchPort);
		
		//Behavior [] hierarchy = {sfw};
		Behavior [] hierarchy = {sfw, t, dl, ll, bo};
		//Behavior [] hierarchy = {esfw, t, dl, ll, bo};
		Arbitrator arbitrator = new Arbitrator(hierarchy);
		arbitrator.start();
	}
	
	

}
