package uy.robotica;

import lejos.nxt.ColorSensor;
import lejos.nxt.LCD;
import lejos.nxt.NXTRegulatedMotor;
import lejos.nxt.ColorSensor.Color;
import lejos.nxt.addon.CompassHTSensor;
import lejos.util.Delay;
import config.Globals;

public class Commons {

	
	public static float getCompassAngle(CompassHTSensor compass){
		
		float degrees =  compass.getDegreesCartesian();
		if (Globals.debug) LCD.drawString("Degrees: " + Float.toString(degrees), 0, 0);
		
		return degrees;
	}
	
	public static void liftLoader(NXTRegulatedMotor motor){
		
		motor.setSpeed(Globals.gripperSpeed);
		motor.rotate(-Globals.gripperRotationAngle);
	}
	
	
	public static void downLoader(NXTRegulatedMotor motor){
		
		motor.setSpeed(Globals.gripperSpeed);
		motor.rotate(Globals.gripperRotationAngle);
	}
	
	
	public static boolean isOrange(ColorSensor cs){
		
		
		if (Globals.debug) LCD.clear();
		
		//Get multiple color readings
		int colorId = 0;
		
		for (int i=0; i < 2; i++){
			Color color = cs.getColor();
			
			colorId = color.getColor();

			int red = color.getRed();
			int green = color.getGreen();
			int blue = color.getBlue();
			
			if (Globals.debug){
				LCD.drawString("(" + red + "," + green + "," + blue + ")", 0, 3+i);
			}
			
			Delay.msDelay(100);
			
		}
		
			
		return  (colorId == Color.RED);
		
	}

	

	
}
