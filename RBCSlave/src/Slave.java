import lejos.robotics.subsumption.Arbitrator;
import lejos.robotics.subsumption.Behavior;
import lejos.nxt.LCD;

public class Slave {
	
	public static BallColor ballColor = BallColor.NOTHING;
	
	public static void main(String argv[]) {
       
        LCD.drawString("Kicker!", 0, 0);
        
        Behavior kick = new Kick();
        Behavior letGo = new LetGo();
        Behavior senseColor = new SenseColor();
        
        Behavior[] hierarchy = {senseColor, letGo, kick};
        Arbitrator larrionda = new Arbitrator(hierarchy);
        larrionda.start();
	}
}

