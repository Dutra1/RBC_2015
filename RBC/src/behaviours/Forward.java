package behaviours;

import config.Globals;
import lejos.nxt.SensorPort;
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
		int minLeftDistance = 500;
		int minRightDistance = 500;
		
		while(!supressed) {
			int dl = usL.getDistance();
			int dr = usR.getDistance();
			
			if (dl < minLeftDistance) minLeftDistance = dl;
			if (dr < minRightDistance) minRightDistance = dr;
			
			Thread.yield();
		}
		
		if(minLeftDistance < Globals.minObjectDistance){
			Turn.setNextTurnRight();
		} else if(minRightDistance < Globals.minObjectDistance){
			Turn.setNextTurnLeft();
		} else if (minLeftDistance > Globals.maxObjectDistance) {
			Turn.setNextTurnLeft();
		} else if (minRightDistance > Globals.maxObjectDistance) {
			Turn.setNextTurnRight();
		}
		
		pilot.stop();
	}

	@Override
	public void suppress() {
		supressed = true;
		pilot.stop();
	}
}
