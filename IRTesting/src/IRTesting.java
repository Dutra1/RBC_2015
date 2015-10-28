import lejos.nxt.Button;
import lejos.nxt.LCD;
import lejos.nxt.SensorPort;
import lejos.nxt.addon.OpticalDistanceSensor;
import lejos.util.Delay;

public class IRTesting {
	
	public static final SensorPort irPort =  SensorPort.S2;
	public static final int delay = 100;
	
	public static void main(String argv[]) {
		OpticalDistanceSensor ir = new OpticalDistanceSensor(irPort);
		
		//Start
		LCD.drawString("IR testing", 0, 1);
		LCD.drawString("Click to start", 0, 0);
		Button.waitForAnyPress();
		Delay.msDelay(500);
		
		//Test
		LCD.clear();
		LCD.drawString("Click to stop", 0, 0);
		
		boolean stop = false;
		while(!stop) {
			LCD.clear(3);
			LCD.drawString("IR: " + ir.getDistance(), 0, 3);
			Delay.msDelay(delay);
			
			if (Button.readButtons() != 0) stop = true;
		}
	}
}
