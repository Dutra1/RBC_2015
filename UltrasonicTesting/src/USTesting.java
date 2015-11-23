import lejos.nxt.Button;
import lejos.nxt.LCD;
import lejos.nxt.SensorPort;
import lejos.nxt.UltrasonicSensor;

public class USTesting {

	private static UltrasonicSensor usL;
	private static UltrasonicSensor usR;
	private static SensorPort usLPort = SensorPort.S3;
	private static SensorPort usRPort = SensorPort.S2;
	
	public static void main(String[] args) {
		usL = new UltrasonicSensor(usLPort);
		usR = new UltrasonicSensor(usRPort);
		
		int minLeft = 500;
		int minRight = 500;
		int maxLeft = 0;
		int maxRight = 0;
		

		while (true) {
			if (Button.readButtons() != 0) {
				minLeft = 500;
				minRight = 500;
				maxLeft = 0;
				maxRight = 0;
			}
			
			int dl = usL.getDistance();
			int dr = usR.getDistance();
			
			if (dl < minLeft) minLeft = dl;
			if (dr < minRight) minRight = dr;
			if (dl > maxLeft) maxLeft = dl;
			if (dr > maxRight) maxRight = dr;
			
			//Muchas distancias
			usL.ping();
			usR.ping();
			
			int[] leftDistances = new int[8];
			int[] rightDistances = new int[8];
			int leftAmount = usL.getDistances(leftDistances);
			int rightAmount = usR.getDistances(rightDistances);
			
			LCD.clear();
			LCD.drawString("DL:" + dl, 0, 0);
			LCD.drawString("DR:" + dr, 0, 1);
			LCD.drawString("MinL:" + minLeft, 0, 2);
			LCD.drawString("MinR:" + minRight, 0, 3);
			LCD.drawString("MaxL:" + maxLeft, 0, 4);
			LCD.drawString("MaxR:" + maxRight, 0, 5);
			LCD.drawString("AmountL:" + leftAmount, 0, 6);
			LCD.drawString("Amountr:" + rightAmount, 0, 7);
			
		}
	}

}
