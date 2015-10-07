package behaviours;

import config.Globals;
import lejos.nxt.SensorPort;
import lejos.nxt.TouchSensor;
import lejos.nxt.addon.OpticalDistanceSensor;
import lejos.robotics.navigation.DifferentialPilot;
import lejos.robotics.subsumption.Behavior;

public class IRFollowWall implements Behavior{

	DifferentialPilot pilot;
	private OpticalDistanceSensor ir;
	private TouchSensor touch;
	
	private int lastDistanceMeasure;
	private long lastTimeMeasure;
	
	public IRFollowWall(DifferentialPilot pilot, SensorPort irPort, SensorPort touchPort) {
		this.pilot = pilot;
		this.ir = new OpticalDistanceSensor(irPort);
		this.ir.powerOn();
		this.touch = new TouchSensor(touchPort);
		
		lastDistanceMeasure = this.ir.getDistance();
		lastTimeMeasure = System.currentTimeMillis();
	}
	
	@Override
	public boolean takeControl() {
		return true;
	}
	
	@Override
	public void action() {
		long currentTime = System.currentTimeMillis();
		int currentDistance = ir.getDistance();
		if (touch.isPressed()) {
			//Back off
		} else if (currentTime - lastTimeMeasure < Globals.timeBetweenIRMeasures) {
			//Do Nothing
		} else {
			double IR1 = lastDistanceMeasure;
			double IR2 = currentDistance;
			long deltaTime = currentTime - lastTimeMeasure;
			
			//Do Stuff
			if((IR1 > IR2) && (IR2 > Globals.idealWallDistance)) {
				double distance = deltaTime * Globals.estimatedMMperSec / 1000.0;
				double m = Math.sqrt(distance * distance + IR2 * IR2);
				double alpha1 = Math.asin(m / IR1);
				double t = Math.tan(alpha1) / IR2;
				double alpha2 = (90 + alpha1) / 2;
				double radius = Math.tan(alpha2) / t;
				
				pilot.arc(radius, alpha1, true);
			} else {
				pilot.forward();
			}
			
			lastDistanceMeasure = currentDistance;
			lastTimeMeasure = currentTime;
		}
	}

	@Override
	public void suppress() {
	}

}
