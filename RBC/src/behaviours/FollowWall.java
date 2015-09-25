package behaviours;

import config.Globals;
import lejos.nxt.SensorPort;
import lejos.nxt.TouchSensor;
import lejos.nxt.addon.CompassHTSensor;
import lejos.nxt.addon.OpticalDistanceSensor;
import lejos.robotics.navigation.DifferentialPilot;
import lejos.robotics.subsumption.Behavior;


public class FollowWall implements Behavior {

	private boolean suppressed = false;
	DifferentialPilot pilot;
	private CompassHTSensor compass;
	private OpticalDistanceSensor ir;
	private TouchSensor touch;
	static private int[] position;
	static private int correctionAngle = 0;
	static private double correctionDistance = 0;
	static private boolean colissionDetected = false;
	static private int umbral = 15;
	static private int correctionFactor = 10;
	
	private void readSensors(){
		
		FollowWall.position[1] = (int) this.compass.getDegreesCartesian();
		if(FollowWall.position[1] > 315){
			FollowWall.position[1] = FollowWall.position[1]-360;
		}
		FollowWall.position[0] = this.ir.getDistance();
		FollowWall.colissionDetected = this.touch.isPressed(); 
	}
	
	private void calculateForce(){
		
		FollowWall.correctionDistance = 0;
		FollowWall.correctionAngle = 0;
		if (FollowWall.position[1] > Globals.desiredAngle + umbral ){
			FollowWall.correctionDistance = -0.1;
		}
		else if (FollowWall.position[1] < Globals.desiredAngle - umbral){
			FollowWall.correctionDistance = 0.1;			
		}
		else{
			FollowWall.correctionDistance = (FollowWall.position[0] - Globals.wallDistance)/correctionFactor;
		}
	}
	
	private void move(){
		
		if (!FollowWall.colissionDetected){
			if (FollowWall.correctionAngle != 0){
				this.pilot.rotate(FollowWall.correctionAngle);
			} else if (FollowWall.correctionDistance != 0){
				this.pilot.arcForward( FollowWall.correctionDistance);
			} else {
				this.pilot.forward();
			}
		} else {
			pilot.stop();
			pilot.travel(-10);
			Globals.desiredAngle = (Globals.desiredAngle + 270)%360;
		}
		
	}
	
	
	public FollowWall(DifferentialPilot pilot,SensorPort compassPort,SensorPort irPort,SensorPort touchPort) {

		this.pilot = pilot;
		this.compass = new CompassHTSensor(compassPort);
		compass.resetCartesianZero();
		
		this.ir = new OpticalDistanceSensor(irPort);
		this.ir.powerOn();
		
		this.touch = new TouchSensor(touchPort);
		
		FollowWall.position = new int[2];
		
	}

	public boolean takeControl() {
		return true;
	}

	public void suppress() {
		suppressed = true;
	}

	public void action() {
		
		suppressed = false;
		
		while (!suppressed){
			this.readSensors();
			this.calculateForce();
			this.move();
		}

	}

}