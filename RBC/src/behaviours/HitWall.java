package behaviours;


import uy.robotica.Commons;
import uy.robotica.Robot;
import config.Globals;
import lejos.nxt.LCD;
import lejos.nxt.SensorPort;
import lejos.nxt.TouchSensor;
import lejos.nxt.UltrasonicSensor;
import lejos.robotics.navigation.DifferentialPilot;
import lejos.robotics.navigation.Navigator;
import lejos.robotics.subsumption.Behavior;

public class HitWall implements Behavior {

	private UltrasonicSensor s1;
	private TouchSensor t1;
	private boolean suppressed = false;
	DifferentialPilot pilot;


	public HitWall(DifferentialPilot pilot,UltrasonicSensor s1,TouchSensor t1) {

		this.pilot = pilot;
		this.s1 = s1;
		this.t1 = t1;
	}

	public boolean takeControl() {
		
		//LCD.drawString("Distance: " + Float.toString(s1.getDistance()), 0, 2);
		//return (s1.getDistance() < 10);
		if (t1.isPressed()){
			if (Globals.debug) LCD.drawString("Button pressed!", 0, 0);

		}
		// || (s1.getDistance() < Globals.hitDistance)
		return (t1.isPressed());
		//return false;
		
			
	}

	public void suppress() {
		suppressed = true;
	}

	public void action() {
		suppressed = false;
		
		// Ver si el stop no demora el backwards
		pilot.stop();
		pilot.travel(-Globals.backwardsDistance);
		
		Commons.liftLoader(Globals.gripperMotor);
		Commons.downLoader(Globals.gripperMotor);

		//Direccion de rotacion dependiendo del estado
		pilot.rotate(90);
		

	}

}