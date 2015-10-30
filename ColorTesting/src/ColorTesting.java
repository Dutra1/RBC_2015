import lejos.nxt.Button;
import lejos.nxt.ColorSensor;
import lejos.nxt.LCD;
import lejos.nxt.SensorPort;
import lejos.nxt.ColorSensor.Color;
import lejos.util.Delay;

public class ColorTesting {

	public static final int colorSensorSamples = 6;
	public static final SensorPort colorPort = SensorPort.S3;
	public static final boolean enableColorSensorFL = false;
	public static final int colorSensorFLColor = Color.WHITE;
	public static final int msBetweenReadings = 50;
	
	public static void main(String[] args) {
		ColorSensor cs = enableColorSensorFL ? new ColorSensor(colorPort, colorSensorFLColor) : new ColorSensor(colorPort);
		
		while (true) {
			int button = Button.waitForAnyPress();
			if (button == Button.ID_LEFT) cs.setFloodlight(false);
			else if (button == Button.ID_RIGHT) cs.setFloodlight(true);
			
			LCD.clear();
			
			int redAvg = 0, greenAvg = 0, blueAvg = 0;	
			for (int i = 0; i < colorSensorSamples; i++){
				Color color = cs.getColor();

				int red = color.getRed();
				int green = color.getGreen();
				int blue = color.getBlue();
				
				redAvg += red;
				greenAvg += green;
				blueAvg += blue;
				
				LCD.drawString(red + ", " + green + ", " + blue, 0, i);
				
				if (i < colorSensorSamples - 1) Delay.msDelay(msBetweenReadings);
			}
			
			LCD.drawString("A: " + redAvg / colorSensorSamples + ", "
								 + greenAvg / colorSensorSamples + ", "
								 + blueAvg / colorSensorSamples, 0, colorSensorSamples);
			
			LCD.drawString("R: " + redAvg / (1.0f * greenAvg) + ", "
								 + greenAvg / (1.0f * blueAvg) + ", "
								 + blueAvg / (1.0f * redAvg), 0, colorSensorSamples + 1);
			
		}
	}
}
