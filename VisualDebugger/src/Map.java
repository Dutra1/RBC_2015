import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;


public class Map extends JPanel {

	
	// In reality you will probably want a class here to represent a map tile,
	// which will include things like dimensions, color, properties in the
	// game world.  Keeping simple just to illustrate.
	private final Tile [][] world2;
	private final Tile [][] world1;
	private int robotX = 0;
	private int robotY = 0;
	private int actualWorld=2;
	private float robotAngle = 0;
	
	public Map(){
		
	    this.world1 = new Tile [Globals.MAP_SIZE][Globals.MAP_SIZE];
	    this.world2 = new Tile [Globals.MAP_SIZE][Globals.MAP_SIZE];
	    
	    // Fill with free spaces
	    for (int i = 0; i < Globals.MAP_SIZE; i++) {
	        for (int j = 0; j < Globals.MAP_SIZE; j++) {
	            Color c = Globals.FREE;
	            Tile t = new Tile(c);
	            this.world1[i][j] = t;
	            this.world2[i][j] = t;
	        }
	    }
	    
	    int preferredWidth = Globals.MAP_SIZE * Globals.PREFERRED_GRID_SIZE_PIXELS;
	    int preferredHeight = Globals.MAP_SIZE * Globals.PREFERRED_GRID_SIZE_PIXELS;
	    setPreferredSize(new Dimension(preferredWidth, preferredHeight));
	}
	
	
	public void setTile(int x, int y,Color c){
		
		if (this.actualWorld == 2)
			this.world1[x][y].setColor(c);
		
		else if (this.actualWorld == 3)
			this.world2[x][y].setColor(c);
			
	}
	
	
	public void setRobotPosition(int x, int y, float angle){
		
		this.robotX = x;
		this.robotY = y;
		this.robotAngle = angle;
	}
	
	
	public void setWorld(int world){
		
		this.actualWorld = world;

	}
	
	private void paintRobot(Graphics g){
		
		int rectWidth = getWidth() / Globals.MAP_SIZE;
	    int rectHeight = getHeight() / Globals.MAP_SIZE;
	    Color terrainColor;
	    
	    int i = robotX;
	    int j = robotY;
	    
	    int x = i * rectWidth;
	    int y = j * rectHeight;
	    
	    terrainColor = Globals.ROBOT;
	    g.setColor(terrainColor);
	    g.fillRect(x, y, rectWidth, rectHeight);
	    
	}
	
	
	@Override
	public void paintComponent(Graphics g) {
		
	    // Important to call super class method
	    super.paintComponent(g);
	    // Clear the board
	    g.clearRect(0, 0, getWidth(), getHeight());
	    // Draw the grid
	    int rectWidth = getWidth() / Globals.MAP_SIZE;
	    int rectHeight = getHeight() / Globals.MAP_SIZE;
	
	    
	    for (int i = 0; i < Globals.MAP_SIZE; i++) {
	        for (int j = 0; j < Globals.MAP_SIZE; j++) {
	            // Upper left corner of this terrain rect
	            int x = i * rectWidth;
	            int y = j * rectHeight;
	            

	            Color terrainColor = Globals.FREE;
	            
	          
            	if (this.actualWorld == 1)
            		terrainColor = this.world1[i][j].getColor();
            	else if (this.actualWorld == 2)
            		terrainColor = this.world2[i][j].getColor();
	            
	            
	            g.setColor(terrainColor);
	            g.fillRect(x, y, rectWidth, rectHeight);
	        }
	    }
	    paintRobot(g);
	}

}