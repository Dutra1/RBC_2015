package behaviours;

import lejos.nxt.Sound;
import lejos.robotics.navigation.DifferentialPilot;
import lejos.robotics.subsumption.Behavior;

public class Forward implements Behavior{

	private DifferentialPilot pilot;
	private boolean supressed;
	
	public Forward(DifferentialPilot pilot) {
		this.pilot = pilot;
		supressed = false;
	}
	
	@Override
	public boolean takeControl() {
		return true;
	}
	
	@Override
	public void action() {
		supressed = false;
		pilot.forward();
		while(!supressed) 
			Thread.yield();
		pilot.stop();
	}

	@Override
	public void suppress() {
		supressed = true;
		Sound.beep();
		pilot.stop();
	}
}
