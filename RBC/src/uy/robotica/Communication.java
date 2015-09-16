package uy.robotica;

import java.io.*;
import lejos.nxt.comm.*;

public class Communication extends Thread {

	private DataInputStream input;
	private DataOutputStream output;
	private NXTConnection connection = null;
	
	public Communication(){

		  try {
			  this.connection = USB.waitForConnection();
			  //this.connection = Bluetooth.waitForConnection();
          
          }
		  catch (Exception e) {
			  System.out.println("Waiting connection error " + e);
		  }
	}
	
	
  @Override
  public void run() {
	  
	  super.run();
		
	  while (true) {
		  try{
	           //output = new DataOutputStream(connection.openOutputStream());
	           output = connection.openDataOutputStream();           
	           
	               try {
	            	   
	            	   String msg = Integer.toString(55);
	            	   
	            	   byte[] b = msg.getBytes("UTF-8");
	            	   output.writeInt(b.length);
	            	   output.write(b);
	                   output.flush();
	               }
	               catch (Exception e) {
	            	   System.out.println("Read/Write error " + e);
	               }
	           
	          try {
	               
	               output.close();
	               //connection.close();
	           }
	           catch (IOException ioe){
	            	System.out.println("Close stream error " + ioe);
	           }
	           
		  }
		  catch(Exception e){
			  
			  System.out.println("Open stream error " + e);
	      }
		  try {
			  Thread.sleep(550);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
	  }          
            
  }


}