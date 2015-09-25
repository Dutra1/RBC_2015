package uy.robotica;

public class Music {

	private int cL = 130;
	private int cSL = 139;
	private int dL = 147;
	private int dSL = 156;
	private int eL = 165;
	private int fL = 175;
	private int fSL = 185;
	private int gL = 196;
	private int gSL = 208;
	private int aL = 220;
	private int aSL = 233;
	private int bL = 247;
	
	private int c = 262;
	private int cS = 277;
	private int d = 294;
	private int dS = 311;
	private int e = 329;
	private int f = 349;
	private int fS = 370;
	private int g = 391;
	private int gS = 415;
	private int a = 440;
	private int aS = 455;
	private int b = 466;
	
	private int cH = 523;
	private int cSH = 554;
	private int dH = 587;
	private int dSH = 622;
	private int eH = 659;
	private int fH = 698;
	private int fSH = 740;
	private int gH = 784;
	private int gSH = 830;
	private int aH = 880;
	private int aSH = 932;
	private int bH = 988;

	public void playMarch()  {
	
		// Time = 500ms
		
		playTone(this.a, 500);
		playTone(this.a, 500);
		playTone(this.a, 500);
		playTone(this.f, 350);
		playTone(this.cH, 150);
		playTone(this.a, 500);
		playTone(this.f, 350);
		playTone(this.cH, 150);
		playTone(this.a, 1000);
		playTone(this.eH, 500);
		playTone(this.eH, 500);
		playTone(this.eH, 500);
		playTone(this.fH, 350);
		playTone(this.cH, 150);
		playTone(this.gS, 500);
		playTone(this.f, 350);
		playTone(this.cH, 150);
		playTone(this.a, 1000);
		playTone(this.aH, 500);
		playTone(this.a, 350);
		playTone(this.a, 150);
		playTone(this.aH, 500);
		playTone(this.gSH, 250);
		playTone(this.gH, 250);
		playTone(this.fSH, 125);
		playTone(this.fH, 125);
		playTone(this.fSH, 250);
		mash_sleep(250);
		playTone(this.aS, 250);
		playTone(this.dSH, 500);
		playTone(this.dH, 250);
		playTone(this.cSH, 250);
		playTone(this.cH, 125);
		playTone(this.b, 125);
		playTone(this.cH, 250);
		mash_sleep(250);
		playTone(this.f, 125);
		playTone(this.gS, 500);
		playTone(this.f, 375);
		playTone(this.a, 125);
		playTone(this.cH, 500);
		playTone(this.a, 375);
		playTone(this.cH, 125);
		playTone(this.eH, 1000);
		playTone(this.aH, 500);
		playTone(this.a, 350);
		playTone(this.a, 150);
		playTone(this.aH, 500);
		playTone(this.gSH, 250);
		playTone(this.gH, 250);
		playTone(this.fSH, 125);
		playTone(this.fH, 125);
		playTone(this.fSH, 250);
		mash_sleep(250);
		playTone(this.aS, 250);
		playTone(this.dSH, 500);
		playTone(this.dH, 250);
		playTone(this.cSH, 250);
		playTone(this.cH, 125);
		playTone(this.b, 125);
		playTone(this.cH, 250);
		mash_sleep(250);
		playTone(this.f, 250);
		playTone(this.gS, 500);
		playTone(this.f, 375);
		playTone(this.cH, 125);
		playTone(this.a, 500);
		playTone(this.f, 375);
		playTone(this.c, 125);
		playTone(this.a, 1000);
	}
	
	public void playMarioDeath() {
		
		int tempo = 150; //ms per tempo
		
		playTone(this.cH, tempo/4);
		playTone(this.cSH, tempo/4);
		playTone(this.dH, tempo/2);
		mash_sleep(tempo*3);
		
		playTone(this.b, tempo);
		playTone(this.fH, tempo);
		mash_sleep(tempo);
		playTone(this.fH, tempo);
		
		playTone(this.fH, tempo*4/3);
		playTone(this.eH, tempo*4/3);
		playTone(this.dH, tempo*4/3);
		
		playTone(this.cH, tempo);
		playTone(this.e, tempo);
		playTone(this.gL, tempo);
		playTone(this.e, tempo);
		
		playTone(this.cL, tempo);
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