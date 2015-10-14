
import java.io.DataInputStream;
import java.io.IOException;

import javax.swing.SwingUtilities;

import lejos.pc.comm.NXTComm;
import lejos.pc.comm.NXTCommException;
import lejos.pc.comm.NXTCommFactory;
import lejos.pc.comm.NXTInfo;


public class Comm extends Thread {


	NXTComm connection ;
	DataInputStream input;
	
	public Comm(){

		try {
		
			this.connection = NXTCommFactory.createNXTComm(NXTCommFactory.BLUETOOTH);
			  
			NXTInfo nxtInfo = new NXTInfo(NXTCommFactory.BLUETOOTH, "NXT", "00:16:53:10:31:55");
			  		  
			try{
				this.connection.open(nxtInfo);
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
				
				Command.textBehavior.setText(data[0]);
				Command.textCompass.setText(data[1]);
				Command.textDistance.setText(data[2]);
				Command.progressBattery.setValue((int)(Float.parseFloat(data[3])*1000));
				Command.progressSpeed.setValue((int)(Float.parseFloat(data[4])*1000));
				Command.messageArea.append("Hola");
			}
		});		
	}
	
	
	@Override
	public void run() {
	  
		super.run();		
		
		byte[] b = null;
		while (true) {
			
			try{
				input = new DataInputStream(connection.getInputStream());
											
		        try {
		        	
		        	int size = input.readInt();
		        	b = new byte[size];
		        	
		        	int read = input.read(b,0,size);
		        	input.mark(read);
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
				Thread.sleep(100);
			} 
			catch (InterruptedException e) {
				  e.printStackTrace();
			}
		}          
            
	}


}