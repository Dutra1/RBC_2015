import lejos.robotics.subsumption.Arbitrator;
import lejos.robotics.subsumption.Behavior;
import lejos.nxt.Button;
import lejos.nxt.LCD;

public class Slave {
	
	public static void main(String argv[]) {
       
        LCD.drawString("Kicker!", 0, 0);
        
        Button.waitForAnyPress();
        LCD.clear();
        
        Behavior raiseMast = new RaiseMast(Globals.mastMotor);
        Behavior senseAndKick = new SenseAndKick(Globals.kicker, Globals.colorPort, Globals.compassPort);
        
        Behavior[] hierarchy = {senseAndKick, raiseMast};
        Arbitrator arbitrator = new Arbitrator(hierarchy);
        arbitrator.start();
	}
}

