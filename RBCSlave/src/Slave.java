import lejos.robotics.subsumption.Arbitrator;
import lejos.robotics.subsumption.Behavior;
import lejos.nxt.Button;
import lejos.nxt.LCD;

public class Slave {
	
	/*public static Communication comm;
	public static int compassValue;*/
	
	public static void main(String argv[]) {
       
        LCD.drawString("Kicker!", 0, 0);
        
        Button.waitForAnyPress();
        LCD.clear();
        
        /*compassValue = 0;
        comm = new Communication();
        comm.start();*/
        
        Behavior raiseMast = new RaiseMast(Globals.mastMotor);
        Behavior senseAndKick = new SenseAndKick(Globals.kicker, Globals.colorPort, Globals.compassPort);
        
        Behavior[] hierarchy = {senseAndKick, raiseMast};
        Arbitrator arbitrator = new Arbitrator(hierarchy);
        arbitrator.start();
	}
}

