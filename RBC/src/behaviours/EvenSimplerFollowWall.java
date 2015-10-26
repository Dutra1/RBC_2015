package behaviours;

import lejos.robotics.navigation.DifferentialPilot;
import lejos.robotics.subsumption.Behavior;
import config.Globals;
import lejos.nxt.UltrasonicSensor;

public class EvenSimplerFollowWall implements Behavior{

	private DifferentialPilot pilot;
	private UltrasonicSensor usR;
	private UltrasonicSensor usL;
	private Turn turn;
	
	public EvenSimplerFollowWall(DifferentialPilot pilot, Turn turn) {
		this.pilot = pilot;
		this.usR = new UltrasonicSensor(Globals.usR);
		this.usL = new UltrasonicSensor(Globals.usL);
		this.turn = turn;
		this.usR.continuous();
		this.usL.continuous();
	}
	
	@Override
	public boolean takeControl() {
		return true;
	}
	
	@Override
	public void action() {
		pilot.forward();
		int dl;
		int dr;
		dl = usL.getDistance();
		dr = usR.getDistance();
		if(dr < Globals.objectDistance){
			turn.setNextTurn(false); //izquierda
		} else if(dl<Globals.objectDistance){
			turn.setNextTurn(true); //derecha
		}
		
	}

	@Override
	public void suppress() {}
}
