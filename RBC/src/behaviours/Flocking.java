package behaviours;


import config.Globals;
import lejos.nxt.SensorPort;
import lejos.nxt.Sound;
import lejos.nxt.UltrasonicSensor;
import lejos.robotics.navigation.DifferentialPilot;
import lejos.robotics.subsumption.Behavior;
import lejos.util.Delay;

public class Flocking implements Behavior {

	private DifferentialPilot pilot;
	private UltrasonicSensor us;
	private int[] distances;
	
	public Flocking(DifferentialPilot pilot, SensorPort usPort) {
		this.pilot = pilot;
		this.us = new UltrasonicSensor(usPort);

		distances = new int[8];
	}
	
	@Override
	public boolean takeControl() {
		return canSeeFriendlyRobot(us);
	}

	@Override
	public void action() {
		Sound.beepSequenceUp();
		
		while (takeControl()) {
			us.continuous();
			Delay.msDelay(20); //Recomendacion de lejos
			int robotDistance = us.getDistance();	
			
			if (robotDistance > Globals.forwardMaxFlockingDistance) {
				pilot.forward();
			} else if (robotDistance < Globals.forwardMinFlockingDistance) {
				pilot.backward();
			} else {
				pilot.stop();
			}
			
			Thread.yield();
		}
		
		Sound.beepSequence();
		pilot.forward();
		Delay.msDelay(Globals.isMovingDelay);
	}

	@Override
	public void suppress() {}
	
	private boolean canSeeFriendlyRobot(UltrasonicSensor us) {
		us.ping();
		int cant = us.getDistances(distances);
		orderList(distances, cant);
		
		if (cant < 2) {
			//Vio solo una cosa
			return false;
		} else {
			int firstDistanceIndex = cant - 1;
			for (int i = 0; i < cant; i++) {
				if (distances[i] > Globals.minForwardDistance) {
					firstDistanceIndex = i;
				}
			}
			int previousDistance = distances[firstDistanceIndex];
			for (int i = firstDistanceIndex + 1; i < cant; i++) {
				int currentDistance = distances[i];
				if (currentDistance > Globals.maxForwardDistance) {
					return false;
				} else if (currentDistance - previousDistance > Globals.friendlyRobotFootprint) {
					return true;
				} else {
					previousDistance = currentDistance;
				}
			}
			return false;
		}		
	}
	
	private void orderList(int[] list, int cantidad) {
		for(int i = 0; i < cantidad - 1; i++) {
			for (int j = i; j < cantidad - 1; j++) {
				if (list[j] > list[j + 1]) {
					int aux = list[j];
					list[j] = list[j + 1];
					list[j + 1] = aux;
				}
			}
		}
	}
}
