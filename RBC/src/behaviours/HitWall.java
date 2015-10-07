package behaviours;


import uy.robotica.Commons;
import config.Globals;
import lejos.nxt.LCD;
import lejos.nxt.TouchSensor;
import lejos.robotics.navigation.DifferentialPilot;
import lejos.robotics.subsumption.Behavior;

public class HitWall implements Behavior {

	private TouchSensor t1;
	DifferentialPilot pilot;


	public HitWall(DifferentialPilot pilot, TouchSensor t1) {

		this.pilot = pilot;
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

	public void suppress() {}

	public void action() {
		
		// Ver si el stop no demora el backwards
		pilot.stop();
		pilot.travel(-Globals.backwardsDistance);
		
		Commons.liftLoader(Globals.scoopMotor);
		Commons.downLoader(Globals.scoopMotor);

		//Direccion de rotacion dependiendo del estado
		pilot.rotate(90);
		

	}

}