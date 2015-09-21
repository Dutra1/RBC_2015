package behaviours;


import config.Globals;
import lejos.nxt.LCD;
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
	static private int correctionDistance = 0;
	static private boolean colissionDetected = false;

	private void readSensors(){
		
		FollowWall.position[1] = (int) this.compass.getDegreesCartesian();
		FollowWall.position[0] = this.ir.getDistance();
		FollowWall.colissionDetected = this.touch.isPressed(); 
	}
	
	private void calculateForce(){
		
		FollowWall.correctionDistance = 0;
		if (FollowWall.position[1] > Globals.desiredAngle + 10){
			FollowWall.correctionAngle = -10;
		}
		else if (FollowWall.position[1] < Globals.desiredAngle - 10){
			FollowWall.correctionAngle = 10;
		}
		else{
			FollowWall.correctionAngle = 0;
			if (FollowWall.position[0] > Globals.wallDistance){
				FollowWall.correctionDistance = 1;
				
			}
			else if (FollowWall.position[0] < Globals.wallDistance){
				FollowWall.correctionDistance = -1;
			}
		}
	}
	
	private void move(){
		
		if (!FollowWall.colissionDetected){
			if (FollowWall.correctionAngle != 0){
				this.pilot.rotate(FollowWall.correctionAngle);
			}
			
			if (FollowWall.correctionDistance != 0){
				this.pilot.travelArc(20, FollowWall.correctionDistance);
			}
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