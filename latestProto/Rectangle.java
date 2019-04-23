import javax.swing.JPanel;
import javax.swing.JFrame;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Dimension;
import java.util.Random;
import java.awt.event.*;
import java.util.ArrayList;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.*;



public class Rectangle {

	public int x;
	public int y;
	public int width;
	public int height;
	public Color color;

     public Rectangle(int x, int y, int width, int height, Color color) {
    	 this.x = x;
    	 this.y = y;
    	 this.width = width;
    	 this.height = height;
    	 this.color = color;
     }

     public Color getColor() {
         return color;
     }

     public void paint(Graphics g) {

         g.setColor(getColor());
         g.fillRect(this.x, this.y, 45,45);
         

     }
     
     public boolean checkEdge(Pair p) {
    	 
    	 if(p.y + this.height <= 475 && p.x + this.width <= 475 && p.x - this.width>=25 ||  p.y + this.height <= 475 && p.x + this.width <= 975 && p.x-this.width>=520) {
    		
    		return true; 
    		 
    	 }
    	 
    	 
    	 else
    		 return false;
    	 
     }
     
     public Rectangle rotate() { //rotate clockwise 90 deg
    	
    	 Rectangle toReturn = new Rectangle(this.x, this.y, this.height, this.width, this.color);
    	 
    	 return toReturn;
    	 
    	 
     }
	
}
