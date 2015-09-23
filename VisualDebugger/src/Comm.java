
import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.IOException;

import javax.swing.SwingUtilities;

import lejos.pc.comm.NXTComm;
import lejos.pc.comm.NXTCommException;
import lejos.pc.comm.NXTCommFactory;
import lejos.pc.comm.NXTInfo;


public class Comm extends Thread {


	NXTComm nxtComm ;
	
	public Comm(){

		try {
			//this.connection = USB.waitForConnection();
			this.nxtComm = NXTCommFactory.createNXTComm(NXTCommFactory.BLUETOOTH);
			  
			NXTInfo nxtInfo = new NXTInfo(NXTCommFactory.BLUETOOTH, "NXT", "00:16:53:0B:9A:B5");
			  		  
			try{
				nxtComm.open(nxtInfo);
				System.out.println("Conectado!");
			}
			catch (NXTCommException e) {
				System.out.println("Open error " + e);  
			}
		}
		catch (Exception e) {
			System.out.println("Connection error " + e);
		}
	}
	
	
	private void updateGUI(final String [] data){

		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				
				Command.textWorld.setText(data[0]);
				Command.textBehavior.setText(data[1]);
				Command.textX.setText(data[2]);
				Command.textY.setText(data[3]);
				Command.textAngle.setText(data[4]);
				Command.map.setRobotPosition(Integer.parseInt(data[2]), Integer.parseInt(data[3]),(int)Float.parseFloat(data[4]));
				Command.map.repaint();
				Command.progressBattery.setValue((int)(Float.parseFloat(data[5])*1000));
			}
		});		
	}
	
	
	@Override
	public void run() {
	  
		super.run();		
		
		byte[] b = null;
		while (true) {
			
			try{
				DataInputStream input = new DataInputStream(nxtComm.getInputStream());
							
				
		        try {
		        	
		        	int size = input.readInt();
		        	b = new byte[size];
		        	
		        	int read = input.read(b,0,size);
		        	input.mark(read);
		        	//input.reset();
		        	String r = new String(b, "UTF-8");
		        	System.out.println(r);
		        	String[] data = r.split(";");
		        	updateGUI(data);
		        	
		        }
		        catch (Exception e) {
		        	System.out.println("Read/Write error " + e);
		        }
		           
		           
		        try {
		        	input.close();
		        	//output.close();
		        	//nxtComm.close();
		        }
		        catch (IOException ioe){
		        	System.out.println("Close stream error " + ioe);
		        }
		           
			}
			catch(Exception e){			  
				System.out.println("Open stream error " + e);
		    }
			
			
			try {
				Thread.sleep(500);
			} 
			catch (InterruptedException e) {
				  e.printStackTrace();
			}
		}          
            
	}


}