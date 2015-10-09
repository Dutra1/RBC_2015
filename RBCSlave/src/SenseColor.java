import lejos.nxt.ColorSensor;
import lejos.nxt.LCD;
import lejos.nxt.ColorSensor.Color;
import lejos.robotics.subsumption.Behavior;
import lejos.util.Delay;

public class SenseColor implements Behavior{

	private ColorSensor cs = Globals.enableColorSensorFL ? new ColorSensor(Globals.colorPort, Globals.colorSensorFLColor) : new ColorSensor(Globals.colorPort);
	
	@Override
	public boolean takeControl() {
		return true;
	}

	@Override
	public void action() {
		BallColor isItNothing = getBallColor(cs);
		if (isItNothing != BallColor.NOTHING) {
			Delay.msDelay(Globals.msBallInPosition);
    		Slave.ballColor = getBallColor(cs);
		}
	}

	@Override
	public void suppress() {}

	public static boolean isInRange(int red, int green, int blue, int[] range) {
		return (red > range[0]) && (red < range[1]) && (green > range[2]) && (green < range[3]) && (blue > range[4]) && (blue < range[5]);
	}
	
	public static double getDistanceFromColorRange(int red, int green, int blue, int[] range) {
		//Usa los ranges hardcodeados!
		double distance = 0.0;
		
		//Distancias ponderadas segun la variancia en el rango
		distance += Math.abs(red - (range[1] + range[0]) / 2) / (double) (range[1] - range[0]);
		distance += Math.abs(green - (range[3] + range[2]) / 2) / (double) (range[3] - range[2]);
		distance += Math.abs(blue - (range[5] + range[4]) / 2) / (double) (range[5] - range[4]);
				
		return distance;
	}
	
	public static BallColor getBallColor(ColorSensor cs){	
		
		if (Globals.debug) LCD.clear();
		
		//Get multiple readings
		int red = 0, green = 0, blue = 0;	
		for (int i = 0; i < Globals.colorSensorSamples; i++){
			Color color = cs.getColor();

			red += color.getRed();
			green += color.getGreen();
			blue += color.getBlue();
			
			if (i < Globals.colorSensorSamples - 1) Delay.msDelay(Globals.msBetweenReadings);
			
			if (Globals.debug){
				//LCD.drawString("RGB Sensing", 0, 0);
				//LCD.drawString("(" + color.getRed() + "," + color.getGreen() + "," + color.getBlue() + ")", 0, i+1);
			}
		}
		
		int redAvg = red/Globals.colorSensorSamples;
		int greenAvg = green/Globals.colorSensorSamples;
		int blueAvg = blue/Globals.colorSensorSamples;
		
		if (isInRange(redAvg, greenAvg, blueAvg, Globals.nothingColorRanges)) return BallColor.NOTHING;
		if (isInRange(redAvg, greenAvg, blueAvg, Globals.orangeColorRanges)) return BallColor.ORANGE;
		if (isInRange(redAvg, greenAvg, blueAvg, Globals.lightblueColorRanges)) return BallColor.LIGHTBLUE;
		if (isInRange(redAvg, greenAvg, blueAvg, Globals.purpleColorRanges)) return BallColor.PURPLE;
		
		//Si no reconoce ninguno, busca en que este mas cerca
		
		double nothingDistance = getDistanceFromColorRange(redAvg, greenAvg, blueAvg, Globals.nothingColorRanges);
		double orangeDistance = getDistanceFromColorRange(redAvg, greenAvg, blueAvg, Globals.orangeColorRanges);
		double lightblueDistance = getDistanceFromColorRange(redAvg, greenAvg, blueAvg, Globals.lightblueColorRanges);
		double purpleDistance = getDistanceFromColorRange(redAvg, greenAvg, blueAvg, Globals.purpleColorRanges);
		
		double minDistance = Math.min(Math.min(Math.min(nothingDistance, orangeDistance), lightblueDistance), purpleDistance);
		
		if (nothingDistance == minDistance) return BallColor.NOTHING;
		else if (orangeDistance == minDistance) return BallColor.ORANGE;
		else if (lightblueDistance == minDistance) return BallColor.LIGHTBLUE;
		else return BallColor.PURPLE;	
	}
}
