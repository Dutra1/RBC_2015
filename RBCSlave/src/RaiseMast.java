import lejos.nxt.addon.LnrActrFirgelliNXT;
import lejos.robotics.subsumption.Behavior;

public class RaiseMast implements Behavior{

	private LnrActrFirgelliNXT mast = new LnrActrFirgelliNXT(Globals.mastMotor);

	@Override
	public boolean takeControl() {
		return mast.getTachoCount() < 10;
	}

	@Override
	public void action() {
		mast.moveTo(200, true);
	}

	@Override
	public void suppress() {}

}
