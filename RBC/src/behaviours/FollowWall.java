package behaviours;


import config.Globals;
import lejos.nxt.SensorPort;
import lejos.nxt.TouchSensor;
import lejos.nxt.addon.CompassHTSensor;
import lejos.nxt.addon.OpticalDistanceSensor;
import lejos.robotics.navigation.DifferentialPilot;
import lejos.robotics.subsumption.Behavior;
import java.lang.Math;

public class FollowWall implements Behavior {

	private boolean suppressed = false;
	DifferentialPilot pilot;
	private CompassHTSensor compass;
	private OpticalDistanceSensor ir;
	private TouchSensor touch;
	static private int[] position;
	static private double[] map;
	static private double correctionAngle = 0;
	static private double correctionDistance = 0;
	static private boolean colissionDetected = false;
	
	private void readSensors(){
		
		FollowWall.position[1] = (int) this.compass.getDegreesCartesian();
		if(FollowWall.position[1] > 315){
			FollowWall.position[1] = FollowWall.position[1]-360;
		}
		FollowWall.position[0] = this.ir.getDistance();
		FollowWall.colissionDetected = this.touch.isPressed(); 
		
	}
	
	private void mapPosition(){
		map[1] = FollowWall.position[1] - Globals.desiredAngle; /*Angulo relativo a la pared*/
		map[0] = FollowWall.position[0] * Math.cos(position[1]) ; /*Distancia relativa a la pared*/
		
	} 
	
	private void calculateForce(){
		/*El nuevo vector de dirección es el producto escalar del vector velocidad por el campo de fuerza que
		 * sigue a la pared. El campo de la pared se calcula como 1/distancia y es perpendicular a la pared.
		 * Ademas el centro del campo tiene que formar un campo expulsor de forma 1/distancia
		 */
		
		/*Vector repulsion + vector orientación + vector pared*/
		double[] vectorAux= {0,0};
		vectorAux[0] = map[0]*Math.cos(map[1]) + 1/map[0]; /*Real*/
		vectorAux[0] = vectorAux[0];
		vectorAux[1] = map[0]*Math.sin(map[1]) + 1/(100000 - map[0]); /*Imaginario*/
		correctionDistance = Math.sqrt(vectorAux[0]*vectorAux[0] + vectorAux[1]*vectorAux[1]); 
		correctionAngle = Math.atan(vectorAux[1]/vectorAux[0]);
		
		/*
		/*
		
		FollowWall.correctionDistance = 0;
		FollowWall.correctionAngle = 0;
		if (FollowWall.position[1] > Globals.desiredAngle + umbral ){
			FollowWall.correctionDistance = -0.01;
		}
		else if (FollowWall.position[1] < Globals.desiredAngle - umbral){
			FollowWall.correctionDistance = 0.01;			
		}
		else{
			FollowWall.correctionDistance = correctionFactor/(FollowWall.position[0] - Globals.wallDistance);
		}*/
	}
	
	private void move(){
		if (!FollowWall.colissionDetected){
			/*if (FollowWall.correctionAngle != 0){
				this.pilot.rotate(FollowWall.correctionAngle);
			} else if (FollowWall.correctionDistance != 0){
				this.pilot.arcForward( FollowWall.correctionDistance);
			} else {
				this.pilot.forward();
			}*/
			pilot.rotate(correctionAngle);
			pilot.travel(correctionDistance, true);
		} else {
			pilot.stop();
			pilot.travel(-10);
			Globals.desiredAngle = (Globals.desiredAngle + 270)%360;
			pilot.rotate(270);
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
		FollowWall.map = new double[2];
		
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
			this.mapPosition();
			this.calculateForce();
			this.move();
		}

	}

}