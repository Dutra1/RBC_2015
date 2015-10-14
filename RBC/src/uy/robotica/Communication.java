package uy.robotica;

import java.io.*;

import config.Globals;
import lejos.nxt.addon.CompassHTSensor;
import lejos.nxt.comm.*;

public class Communication extends Thread {

	private DataOutputStream output;
	private NXTConnection connection = null;
	private CompassHTSensor compass;
	
	public Communication(){
		compass = new CompassHTSensor(Globals.compassPort);
	}
	
	
  @Override
  public void run() {
	  
	  super.run();
	  
	  while (this.connection == null)
		  this.connection = RS485.getConnector().connect("rbc5_2", RS485Connection.PACKET);
		
	  while (true) {
		  try{
	           output = connection.openDataOutputStream();           
	           
	               try {        		
	            	   int compassValue = (int) Commons.getCompassAngle(compass);
	            	   
	            	   output.writeInt(compassValue);
	                   output.flush();
	               }
	               catch (Exception e) {
	            	   System.out.println("Write error " + e);
	               }
	           
	          try {
	               
	               output.close();
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