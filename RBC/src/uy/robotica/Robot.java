package uy.robotica;

import behaviours.Backoff;
import behaviours.Flocking;
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
	public static void main(String argv[]) {

		DifferentialPilot pilot = new DifferentialPilot(Globals.wheelDiameter, Globals.trackWidth, Globals.leftMotor, Globals.rightMotor, true);
		pilot.setTravelSpeed(Globals.travelSpeed);
		
		LCD.drawString(Globals.introMsg, 0, 0);
		LCD.drawString(Globals.pressButton, 0, 1);
		
		Button.waitForAnyPress();
		LCD.clear();
	    
	    pilot.forward();
	    
		Behavior f = new Forward(pilot, Globals.usL, Globals.usR);
		Behavior t = new Turn(pilot, Globals.touchPort);
		Behavior ll = new LiftLoader(pilot, Globals.scoopMotor);
		Behavior flocking = new Flocking(pilot, Globals.usF);
		Behavior bo = new Backoff(pilot, Globals.touchPort);

		Behavior [] hierarchy = {f, t, ll, flocking, bo};
		Arbitrator arbitrator = new Arbitrator(hierarchy);
		arbitrator.start();
	}
}
