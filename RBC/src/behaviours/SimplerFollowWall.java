package behaviours;

import config.Globals;
import lejos.nxt.SensorPort;
import lejos.nxt.TouchSensor;
import lejos.nxt.addon.CompassHTSensor;
import lejos.nxt.addon.OpticalDistanceSensor;
import lejos.robotics.navigation.DifferentialPilot;
import lejos.robotics.subsumption.Behavior;

public class SimplerFollowWall implements Behavior{

	DifferentialPilot pilot;
	private OpticalDistanceSensor ir;
	private TouchSensor touch;
	private CompassHTSensor compass;
	
	public SimplerFollowWall(DifferentialPilot pilot, SensorPort irPort, SensorPort touchPort, SensorPort compassPort) {
		this.pilot = pilot;
		this.ir = new OpticalDistanceSensor(irPort);
		this.touch = new TouchSensor(touchPort);
		this.compass = new CompassHTSensor(compassPort);
		
		ir.powerOn();
		compass.resetCartesianZero();
	}
	
	@Override
	public boolean takeControl() {
		return true;
	}
	
	@Override
	public void action() {
		float currentAngle = compass.getDegreesCartesian();
		int irDistance = ir.getDistance();
		
		float wallAngle = getWallAngle(currentAngle);
		double absAngleDifference = Math.abs(angleDifference(currentAngle, wallAngle));
		double actualDistance = irDistance * Math.cos(Math.toRadians(absAngleDifference));
		
		float angleDifference;
		if (actualDistance < Globals.wallDistance - Globals.distanceTolerance) {
			//Too close! Back off
			angleDifference = angleDifference(currentAngle, wallAngle - Globals.turnAngle);
		} else if (actualDistance > Globals.wallDistance + Globals.distanceTolerance) {
			//Too far! Get closer
			angleDifference = angleDifference(currentAngle, wallAngle + Globals.turnAngle);
		} else {
			//Correct distance to wall
			angleDifference = angleDifference(currentAngle, wallAngle);
		}
		
		adjustAngle(angleDifference);
	}

	@Override
	public void suppress() {
	}
	
	/***** Auxiliary Function *****/
	
	public float getWallAngle(float angle) {
		if ((angle > 45) && (angle <= 135)) {
			return 90;
		} else if ((angle > 135) && (angle <= 225)) {
			return 180;
		} else if ((angle > 225) && (angle <= 315)) {
			return 270;
		} else {
			return 0;
		}
	}
	
	public float angleDifference(float angle1, float angle2) {
		float minus = angle1 - angle2;
		if (minus <= -180) return minus + 360;
		if (minus >= 180) return minus - 360;
		return minus;
	}
	
	public void adjustAngle(float angleDifference) {
		if(Math.abs(angleDifference) > Globals.angleTolerance) {
			//Too off course! Adjust
			if (angleDifference > 0) {
				//Turn right
				pilot.arc(-Globals.turnRadius, 5);
			} else {
				//Turn left
				pilot.arc(Globals.turnRadius, 5);
			}
		} else {
			//On our way!
			pilot.forward();
		}
		
	}

}
