package uy.robotica;

import behaviours.Backoff;
import behaviours.Forward;
import behaviours.LiftLoader;
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
	
	public static void main(String argv[]) {

		pilot = new DifferentialPilot(Globals.wheelDiameter, Globals.trackWidth, Globals.leftMotor, Globals.rightMotor, true);
		pilot.setTravelSpeed(Globals.travelSpeed);
		
		LCD.drawString(Globals.introMsg, 0, 0);
		LCD.drawString(Globals.pressButton, 0, 1);
		
		Button.waitForAnyPress();
		LCD.clear();
		
		/*Music music = new Music();
		music.playMarch();*/
		
		/*comm = new Communication();
	    comm.start();*/
	    
	    /*commdebugger = new CommDebugger();
	    commdebugger.start();*/
	    
	    pilot.forward();
	    
		Behavior f = new Forward(pilot, Globals.usL, Globals.usR);
		Behavior t = new Turn(pilot, Globals.touchPort);
		Behavior ll = new LiftLoader(pilot, Globals.scoopMotor, Globals.touchPort);
		Behavior bo = new Backoff(pilot, Globals.touchPort);

		Behavior [] hierarchy = {f, t, ll, bo};
		Arbitrator arbitrator = new Arbitrator(hierarchy);
		arbitrator.start();
	}
	
	

}
