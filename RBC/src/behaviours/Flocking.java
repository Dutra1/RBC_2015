package behaviours;

import java.awt.Rectangle;
import config.Globals;
import lejos.nxt.SensorPort;
import lejos.nxt.Sound;
import lejos.nxt.addon.NXTCam;
import lejos.nxt.addon.OpticalDistanceSensor;
import lejos.robotics.navigation.DifferentialPilot;
import lejos.robotics.subsumption.Behavior;

public class Flocking implements Behavior {

	private DifferentialPilot pilot;
	private NXTCam cam;
	private OpticalDistanceSensor ir;
	
	public Flocking(DifferentialPilot pilot, SensorPort camPort, SensorPort irPort) {
		this.pilot = pilot;
		this.cam = new NXTCam(camPort);
		this.ir = new OpticalDistanceSensor(irPort);
		
		cam.setTrackingMode(NXTCam.OBJECT_TRACKING);
		cam.enableTracking(true);
		cam.sortBy(NXTCam.SIZE);
	}
	
	@Override
	public boolean takeControl() {
		return canSeeFriendlyRobot(cam);
	}

	@Override
	public void action() {
		Sound.beepSequenceUp();
		int distance = ir.getDistance();
		if (distance > Globals.forwardMaxFlockingDistance) {
			pilot.forward();
		} else if ((distance < Globals.forwardMinFlockingDistance) && (distance > Globals.IRMinSafeDistance)) {
			pilot.backward();
		}
	}

	@Override
	public void suppress() {}
	
	private boolean canSeeFriendlyRobot(NXTCam cam) {
		//cam.readAll();
		int numberOfObjects = cam.getNumberOfObjects();
		if (numberOfObjects == 0) {
			return false;
		}
		Sound.playTone(numberOfObjects * 100, 100);
		
		Rectangle rectangle = cam.getRectangle(0);
		return (rectangle.x < Globals.midWallPixelWidth) && (rectangle.getHeight() * rectangle.getWidth() > Globals.minAreaRectangle);
	}

}
