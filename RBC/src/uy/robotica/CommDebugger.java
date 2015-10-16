package uy.robotica;

import java.io.*;
import lejos.nxt.comm.*;

public class CommDebugger extends Thread {

	private DataOutputStream output;
	private NXTConnection connection = null;
	
	public CommDebugger(){

	}
	
	
  @Override
  public void run() {
	  
	  super.run();
	
	  try {
		  this.connection = Bluetooth.waitForConnection();
      
      }
	  catch (Exception e) {
		  System.out.println("Waiting connection error " + e);
	  }
	  
	  while (true) {
		  try{
	           //output = new DataOutputStream(connection.openOutputStream());
	           output = connection.openDataOutputStream();
	                     
               try {
            	
            	   String behavior = "Algo";
            	   String compass =  Float.toString(40.0f);
            	   String distance =  Float.toString(25);
            	   String battery =  Float.toString(1200);
            	   String speed =  Float.toString(12);
            	   String msg = behavior + ";" + compass + ";" + distance + ";" + battery + ";"+ speed;
            	   
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
			  Thread.sleep(100);
			  
		  } catch (InterruptedException e) {
			  e.printStackTrace();
		  }
	  }          
            
  }


}

