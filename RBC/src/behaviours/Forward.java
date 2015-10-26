package behaviours;

import config.Globals;
import lejos.nxt.SensorPort;
import lejos.nxt.Sound;
import lejos.nxt.UltrasonicSensor;
import lejos.robotics.navigation.DifferentialPilot;
import lejos.robotics.subsumption.Behavior;

public class Forward implements Behavior{

	private DifferentialPilot pilot;
	private UltrasonicSensor usL;
	private UltrasonicSensor usR;
	private boolean supressed;
	
	public Forward(DifferentialPilot pilot, SensorPort usLPort, SensorPort usRPort) {
		this.pilot = pilot;
		this.usL = new UltrasonicSensor(usLPort);
		this.usR = new UltrasonicSensor(usRPort);
		
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
		while(!supressed) {
			int dl = usL.getDistance();
			int dr = usR.getDistance();
			if(dr < Globals.objectDistance){
				Turn.setNextTurn(false); //izquierda
			} else if(dl < Globals.objectDistance){
				Turn.setNextTurn(true); //derecha
			}
			Thread.yield();
		}
		pilot.stop();
	}

	@Override
	public void suppress() {
		supressed = true;
		Sound.beep();
		pilot.stop();
	}
}
