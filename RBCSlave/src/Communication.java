import java.io.DataInputStream;
import java.io.IOException;

import lejos.nxt.comm.NXTConnection;
import lejos.nxt.comm.RS485;
import lejos.nxt.comm.RS485Connection;


public class Communication extends Thread {

	private DataInputStream input;
	private NXTConnection connection = null;
	
	public Communication(){

	}
	
	@Override
	public void run() {
		  
		super.run();
		
		this.connection = RS485.getConnector().waitForConnection(0,RS485Connection.PACKET);
		
		while (true) {
			try{	
				input = connection.openDataInputStream();
				
		        try {
		        	
		        	int read = input.readInt();
		        	System.out.println(read);
		        	    	
		        }
		        catch (Exception e) {
		        	System.out.println("Read error " + e);
		        }
		           
				try {
					input.close();
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
			  
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	
	}
}
