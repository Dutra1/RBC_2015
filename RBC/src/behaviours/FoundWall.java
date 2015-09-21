package behaviours;


import uy.robotica.Commons;
import uy.robotica.Robot;
import config.Globals;
import lejos.nxt.ColorSensor;
import lejos.nxt.LCD;
import lejos.nxt.NXTRegulatedMotor;
import lejos.nxt.SensorPort;
import lejos.nxt.UltrasonicSensor;
import lejos.nxt.ColorSensor.Color;
import lejos.robotics.navigation.DifferentialPilot;
import lejos.robotics.navigation.Navigator;
import lejos.robotics.subsumption.Behavior;
import lejos.util.Delay;

public class FoundWall implements Behavior {
	
	private boolean suppressed = false;
	private UltrasonicSensor s1;
	private ColorSensor cs;
	DifferentialPilot pilot;
	
	
	public FoundWall(DifferentialPilot pilot,UltrasonicSensor s1,ColorSensor cs){

		this.pilot = pilot;
		this.s1 = s1;
		this.cs = cs;
	}
	
	public boolean takeControl() {
		
		LCD.drawString("Distance: " + Float.toString(s1.getDistance()), 0, 2);
		return (s1.getDistance() < 10);
		
			
	}
	
	public void suppress() {
		suppressed = true;
	}

	public void action() {
		suppressed = false;
	
		pilot.stop();
		
		//pilot.rotate(90);
		if (!Robot.holding){
			//Commons.closeGripper(Globals.gripperMotor);
			Robot.holding = true;
		}
		
		if (!Commons.isOrange(cs)){
			//Commons.openGripper(Globals.gripperMotor);
			Robot.holding = false;
		}

		//pilot.stop();

	}

}