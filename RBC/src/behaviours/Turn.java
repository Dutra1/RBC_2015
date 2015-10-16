package behaviours;

import config.Globals;
import lejos.robotics.navigation.DifferentialPilot;
import lejos.robotics.subsumption.Behavior;
import uy.robotica.Robot;

public class Turn implements Behavior{

	private DifferentialPilot pilot;
	
	public Turn (DifferentialPilot pilot) {
		this.pilot = pilot;
	}
	
	@Override
	public boolean takeControl() {
		return !pilot.isMoving() && Robot.hasToTurn;
	}

	@Override
	public void action() {
		pilot.arc(-Globals.backwardsDistance + (Globals.wallDistance - Globals.widthDistanceToCenter), 90, true);
		Robot.hasToTurn = false;
	}

	@Override
	public void suppress() {}
}
