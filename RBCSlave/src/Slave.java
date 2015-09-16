import lejos.nxt.Button;
import lejos.nxt.ColorSensor;
import lejos.nxt.LCD;
import lejos.nxt.ColorSensor.Color;
import lejos.nxt.NXTRegulatedMotor;
import lejos.nxt.SensorPort;
import lejos.util.Delay;

public class Slave {
    
	public static boolean enableFloodLight = true;
	
	public static int getBallColor(){	
		
		ColorSensor cs = new ColorSensor(Globals.colorPort);
		
		//Get multiple color readings
		int colorId = 0;
		int red = 0,green = 0,blue = 0;
		
		if (Globals.debug) LCD.clear();
		
		for (int i=0; i < Globals.colorSensorSamples; i++){
			Color color = cs.getColor();
			
			colorId = color.getColor();

			red += color.getRed();
			green += color.getGreen();
			blue += color.getBlue();
			
			if (Globals.debug){
				LCD.drawString("(" + red + "," + green + "," + blue + ")", 0, 3+i);
			}
			
			Delay.msDelay(50);		
		}	
		
		int redAvg = red/Globals.colorSensorSamples;
		int greenAvg = green/Globals.colorSensorSamples;
		int blueAvg = blue/Globals.colorSensorSamples;
		
		
		return  (colorId);
		
	}
	
	public static void main(String argv[]) {
       
        LCD.drawString("Kicker!", 0, 0);
        NXTRegulatedMotor kicker = Globals.kicker;
        
        while(true){
        	int color = getBallColor();
        	if (color == color) {
        		kicker.setSpeed(Globals.kickSpeed);
        		kicker.backward();
        	} else {
        		kicker.setSpeed(Globals.letGoSpeed);
        		kicker.backward();
        	}
        }
	}
}

