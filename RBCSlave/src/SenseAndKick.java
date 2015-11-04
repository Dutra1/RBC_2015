import lejos.nxt.ColorSensor;
import lejos.nxt.NXTRegulatedMotor;
import lejos.nxt.SensorPort;
import lejos.nxt.Sound;
import lejos.nxt.addon.CompassHTSensor;
import lejos.nxt.ColorSensor.Color;
import lejos.robotics.subsumption.Behavior;
import lejos.util.Delay;

public class SenseAndKick implements Behavior{

	private NXTRegulatedMotor kicker;
	private ColorSensor cs;
	private CompassHTSensor compass;
	
	public SenseAndKick(NXTRegulatedMotor kicker, SensorPort colorPort, SensorPort compassPort) {
		this.kicker = kicker;
		this.cs = Globals.enableColorSensorFL ? new ColorSensor(colorPort, Globals.colorSensorFLColor) : new ColorSensor(colorPort);
		this.compass = new CompassHTSensor(Globals.compassPort);
		
		compass.resetCartesianZero();
	}
	
	@Override
	public boolean takeControl() {
		return true;
	}

	@Override
	public void action() {
		BallColor isItNothing = getBallColor(cs);
		if (isItNothing != BallColor.NOTHING) {
			Delay.msDelay(Globals.msBallInPosition);
    		BallColor ballColor = getBallColor(cs);
    		
    		if ((ballColor == BallColor.LIGHTBLUE) || (ballColor == BallColor.PURPLE)) {
    			kicker.setSpeed(Globals.letGoSpeed);
    			kicker.rotate(360 / Globals.kickerGearReduction);
    		} else if ((ballColor == BallColor.ORANGE) || (ballColor == BallColor.YELLOW)) {
    			while(!isOriented(compass.getDegreesCartesian())) {}
    			
				kicker.setSpeed(Globals.kickSpeed);
				kicker.rotate(-90 / Globals.kickerGearReduction);
				kicker.rotate(360 / Globals.kickerGearReduction);
				kicker.setSpeed(Globals.letGoSpeed);
    			kicker.rotate(90 / Globals.kickerGearReduction);
    		}
    		
    		Delay.msDelay(Globals.msAfterKick);
		}
	}

	@Override
	public void suppress() {}

	public static boolean isOriented(float compassValue) {
		//return true;
		return (compassValue > Globals.idealAngle - Globals.allowedAngleError) && 
			   (compassValue < Globals.idealAngle + Globals.allowedAngleError);
	}
	
	public static BallColor getBallColor(ColorSensor cs){	
		
		//Get multiple readings
		int red = 0, green = 0, blue = 0;	
		for (int i = 0; i < Globals.colorSensorSamples; i++){
			Color color = cs.getColor();

			red += color.getRed();
			green += color.getGreen();
			blue += color.getBlue();
			
			if (i < Globals.colorSensorSamples - 1) Delay.msDelay(Globals.msBetweenReadings);
		}
		
		int redAvg = red/Globals.colorSensorSamples;
		int greenAvg = green/Globals.colorSensorSamples;
		int blueAvg = blue/Globals.colorSensorSamples;
		
		if (isNothing(redAvg, greenAvg, blueAvg)) return BallColor.NOTHING;
		if (isOrange(redAvg, greenAvg, blueAvg)) return BallColor.ORANGE;
		if (isYellow(redAvg, greenAvg, blueAvg)) return BallColor.YELLOW;
		if (isPurple(redAvg, greenAvg, blueAvg)) return BallColor.PURPLE;
		if (isLightBlue(redAvg, greenAvg, blueAvg)) return BallColor.LIGHTBLUE;
		
		//Si no reconoce ninguno, busca en que este mas cerca
		Sound.beep();
		
		double orangeDistance = getDistanceFromColor(redAvg, greenAvg, blueAvg, Globals.orangeColorRelations);
		double yellowDistance = getDistanceFromColor(redAvg, greenAvg, blueAvg, Globals.yellowColorRelations);
		double lightblueDistance = getDistanceFromColor(redAvg, greenAvg, blueAvg, Globals.lightBlueColorRelations);
		double purpleDistance = getDistanceFromColor(redAvg, greenAvg, blueAvg, Globals.purpleColorRelations);
		
		double minDistance = Math.min(Math.min(Math.min(orangeDistance, yellowDistance), lightblueDistance), purpleDistance);
		
		if (orangeDistance == minDistance) return BallColor.ORANGE;
		else if (yellowDistance == minDistance) return BallColor.YELLOW;
		else if (lightblueDistance == minDistance) return BallColor.LIGHTBLUE;
		else return BallColor.PURPLE;	
	}
	
	//Medir bien estas cosas
	
	public static boolean isNothing(int red, int green, int blue) {
		return (red < 15) && (green < 15) && (blue < 15);
	}
	
	public static boolean isOrange(int red, int green, int blue) {
		return (green > 150) && (Math.abs(red - green) < 15) && (blue < 100);
	}
	
	public static boolean isYellow(int red, int green, int blue) {
		return (red > 190) && (green > 190) && (blue > 190);
	}
	
	public static boolean isLightBlue(int red, int green, int blue) {
		return (green > red) && (green > blue) && (green < 210) && (red > 150) && (blue > 130);
	}
	
	public static boolean isPurple(int red, int green, int blue) {
		return (green < 100) && (Math.abs(red - blue) < 15) && (red > 110);
	}
	
	public static double getDistanceFromColor(int red, int green, int blue, float[] relations) {
		double distance = 0.0;
		
		if (red == 0) red++;
		if (green == 0) green++;
		if (blue == 0) blue++;
		
		
		//Distancias ponderadas segun las relaciones entre colores
		distance += Math.abs( red  / green - relations[0]);
		distance += Math.abs(green / blue  - relations[1]);
		distance += Math.abs(blue  /  red  - relations[2]);
				
		return distance;
	}
}
