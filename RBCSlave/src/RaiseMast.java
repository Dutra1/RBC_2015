import lejos.nxt.MotorPort;
import lejos.nxt.addon.LnrActrFirgelliNXT;
import lejos.robotics.subsumption.Behavior;

public class RaiseMast implements Behavior{

	private LnrActrFirgelliNXT mast;

	public RaiseMast(MotorPort mastPort) {
		mast = new LnrActrFirgelliNXT(mastPort);
	}
	
	@Override
	public boolean takeControl() {
		return (mast.getTachoCount() < 10) && !mast.isStalled();
	}

	@Override
	public void action() {
		mast.move(200, false);
	}

	@Override
	public void suppress() {}

}
