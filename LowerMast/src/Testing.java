import lejos.nxt.MotorPort;
import lejos.nxt.TachoMotorPort;
import lejos.nxt.addon.LnrActrFirgelliNXT;

public class Testing {
	public static final TachoMotorPort mastMotor = MotorPort.B;

	public static void main(String argv[]) {
		LnrActrFirgelliNXT mast = new LnrActrFirgelliNXT(mastMotor);
		mast.moveTo(0, false);
	}
}
