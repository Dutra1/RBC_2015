package behaviours;

import java.util.ArrayList;
import java.util.List;

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
	private long nextMeasureTime;
	
	public Forward(DifferentialPilot pilot, SensorPort usLPort, SensorPort usRPort) {
		this.pilot = pilot;
		this.usL = new UltrasonicSensor(usLPort);
		this.usR = new UltrasonicSensor(usRPort);
		
		supressed = false;
		nextMeasureTime = System.currentTimeMillis();
	}
	
	@Override
	public boolean takeControl() {
		return true;
	}
	
	@Override
	public void action() {
		supressed = false;
		pilot.forward();
		
		/*
		 * while(!supressed) {
			long currentTime = System.currentTimeMillis();
			int n = Globals.SamplesForFilter;
			if (currentTime > nextMeasureTime) {
				nextMeasureTime = currentTime + Globals.msBetweenReadings;
				
				int dl = usL.getDistance();
				int dr = usR.getDistance();
				for(int i =0;i<Globals.n-1;i++){
					leftMeasures[i] = leftMeasures[i+1];
					rightMeasures[i] = rightMeasures[i+1];
				}

				leftMeasures[n] = dl*0.4 + leftMeasures[n-1]*0.3 + leftMeasures[n-2]*0.2 + leftMeasures[n-3]*0.1;
				rightMeasures[n] = dr + rightMeasures[n-1]*0.3 + rightMeasures[n-2]*0.2 + righttMeasures[n-3]*0.1;
				
				
				
				if ((dr < Globals.dangerZone) || (dl < Globals.dangerZone)) {
					supressed = true;
					Sound.playTone(120, 1000);;
				}
			}
			
			
			Thread.yield();
		}
		 */
		
		List<Integer> leftMeasures = new ArrayList<>();
		List<Integer> rightMeasures = new ArrayList<>();
		
		while(!supressed) {
			long currentTime = System.currentTimeMillis();
			if (currentTime > nextMeasureTime) {
				nextMeasureTime = currentTime + Globals.msBetweenReadings;
				
				int dl = usL.getDistance();
				int dr = usR.getDistance();
				leftMeasures.add(dl);
				rightMeasures.add(dr);
				
				if(rightMeasures.size() % Globals.robotCheckPeriod == 0){
					Turn.calculateNextTurn(leftMeasures,rightMeasures);
				}
				
				if ((dr < Globals.dangerZone) || (dl < Globals.dangerZone)) {
					supressed = true;
					Sound.playTone(120, 1000);;
				}
			}
			
			
			Thread.yield();
		}
		
		pilot.stop();
		
		//Turn.calculateNextTurn(leftMeasures, rightMeasures);
	}

	@Override
	public void suppress() {
		supressed = true;
		pilot.stop();
	}
}











