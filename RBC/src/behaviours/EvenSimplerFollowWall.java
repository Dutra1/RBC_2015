package behaviours;

import lejos.robotics.navigation.DifferentialPilot;
import lejos.robotics.subsumption.Behavior;

public class EvenSimplerFollowWall implements Behavior{

	private DifferentialPilot pilot;
	
	public EvenSimplerFollowWall(DifferentialPilot pilot) {
		this.pilot = pilot;
	}
	
	@Override
	public boolean takeControl() {
		return true;
	}
	
	@Override
	public void action() {
		pilot.forward();
	}

	@Override
	public void suppress() {}
}
