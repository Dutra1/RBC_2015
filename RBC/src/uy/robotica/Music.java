package uy.robotica;

public class Music {

	private static final int cL = 130;
	private static final int cSL = 139;
	private static final int dL = 147;
	private static final int dSL = 156;
	private static final int eL = 165;
	private static final int fL = 175;
	private static final int fSL = 185;
	private static final int gL = 196;
	private static final int gSL = 208;
	private static final int aL = 220;
	private static final int aSL = 233;
	private static final int bL = 247;
	
	private static final int c = 262;
	private static final int cS = 277;
	private static final int d = 294;
	private static final int dS = 311;
	private static final int e = 329;
	private static final int f = 349;
	private static final int fS = 370;
	private static final int g = 391;
	private static final int gS = 415;
	private static final int a = 440;
	private static final int aS = 455;
	private static final int b = 466;
	
	private static final int cH = 523;
	private static final int cSH = 554;
	private static final int dH = 587;
	private static final int dSH = 622;
	private static final int eH = 659;
	private static final int fH = 698;
	private static final int fSH = 740;
	private static final int gH = 784;
	private static final int gSH = 830;
	private static final int aH = 880;
	private static final int aSH = 932;
	private static final int bH = 988;

	public void playMarch()  {
	
		// Time = 500ms
		
		playTone(a, 500);
		playTone(a, 500);
		playTone(a, 500);
		playTone(f, 350);
		playTone(cH, 150);
		playTone(a, 500);
		playTone(f, 350);
		playTone(cH, 150);
		playTone(a, 1000);
		playTone(eH, 500);
		playTone(eH, 500);
		playTone(eH, 500);
		playTone(fH, 350);
		playTone(cH, 150);
		playTone(gS, 500);
		playTone(f, 350);
		playTone(cH, 150);
		playTone(a, 1000);
		playTone(aH, 500);
		playTone(a, 350);
		playTone(a, 150);
		playTone(aH, 500);
		playTone(gSH, 250);
		playTone(gH, 250);
		playTone(fSH, 125);
		playTone(fH, 125);
		playTone(fSH, 250);
		mash_sleep(250);
		playTone(aS, 250);
		playTone(dSH, 500);
		playTone(dH, 250);
		playTone(cSH, 250);
		playTone(cH, 125);
		playTone(b, 125);
		playTone(cH, 250);
		mash_sleep(250);
		playTone(f, 125);
		playTone(gS, 500);
		playTone(f, 375);
		playTone(a, 125);
		playTone(cH, 500);
		playTone(a, 375);
		playTone(cH, 125);
		playTone(eH, 1000);
		playTone(aH, 500);
		playTone(a, 350);
		playTone(a, 150);
		playTone(aH, 500);
		playTone(gSH, 250);
		playTone(gH, 250);
		playTone(fSH, 125);
		playTone(fH, 125);
		playTone(fSH, 250);
		mash_sleep(250);
		playTone(aS, 250);
		playTone(dSH, 500);
		playTone(dH, 250);
		playTone(cSH, 250);
		playTone(cH, 125);
		playTone(b, 125);
		playTone(cH, 250);
		mash_sleep(250);
		playTone(f, 250);
		playTone(gS, 500);
		playTone(f, 375);
		playTone(cH, 125);
		playTone(a, 500);
		playTone(f, 375);
		playTone(c, 125);
		playTone(a, 1000);
	}
	
	public void playMarioDeath() {
		
		int tempo = 150; //ms per tempo
		
		playTone(cH, tempo/4);
		playTone(cSH, tempo/4);
		playTone(dH, tempo/2);
		mash_sleep(tempo*3);
		
		playTone(b, tempo);
		playTone(fH, tempo);
		mash_sleep(tempo);
		playTone(fH, tempo);
		
		playTone(fH, tempo*4/3);
		playTone(eH, tempo*4/3);
		playTone(dH, tempo*4/3);
		
		playTone(cH, tempo);
		playTone(e, tempo);
		playTone(gL, tempo);
		playTone(e, tempo);
		
		playTone(cL, tempo);
		mash_sleep(tempo*3);
	}
	
	private void playTone(int tone, int wait)  {	
		lejos.nxt.Sound.playTone(tone, wait*9/10);
		mash_sleep(wait);
	}
	
	
	private void mash_sleep(int ms) {
		try {
			java.lang.Thread.sleep(ms);
		} catch (java.lang.Exception e) {
		}
	}

}