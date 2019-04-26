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


     public boolean needsAdj() {

         if(this.y + this.height <= 475 && this.x + this.width <= 475 && this.x - this.width>=25-45 ||  this.y + this.height <= 475 && this.x + this.width <= 975 && this.x - this.width >=520-45) {
             return false;

         }

         return true;

     }

     public Rectangle checkEdge(Pair p) {


         Rectangle toReturn = null;

    	 if(p.y + this.height <= 475 && p.x + this.width <= 475 && p.x - this.width>=25-45 ||  p.y + this.height <= 475 && p.x + this.width <= 975 && p.x - this.width >=520-45) {

    		return this;

    	 }


    	 else {
    	     return measureOver(p);
    	 }


     }

     public Rectangle rotate() { //rotate clockwise 90 deg

    	 Rectangle toReturn = new Rectangle(this.x, this.y, this.height, this.width, this.color);

    	 Pair anchor = new Pair(this.x, this.y);

    	 if(!this.needsAdj())
    	     return toReturn;
    	 else {

    	     toReturn = this.checkEdge(anchor);
    	     return toReturn;
    	 }



     }

     public String toString() {

         String myPrint = (width + " w" + " by " + height + " l " + "Rectnangle at " + "(" + x + ", " + "y"  + ")");
         return myPrint;


     }

     public Rectangle measureOver(Pair p) { //if rect is OOB, remake the anchor rectangle

         int excess  = 0;
         Rectangle toReturn = new Rectangle(excess, excess, excess, excess, color);

         if(p.y + this.height > 475) { //goes off the bottom

            excess = ((int)p.y+this.height) - 475;
            toReturn = new Rectangle((int)p.x, (int)(p.y - excess), this.width, this.height, this.color);

            System.out.println("The ship went off the bottom. I recorrected this to shift up by " + excess/45 + " squares.");

         }

         if(p.x + this.width > 475 && p.x < 475){ //goes off the right PlayerA

//             excess = (475 - this.width);
             excess = ((int) p.x + this.width) - 475;
             toReturn = new Rectangle((int)(p.x - excess), (int)(p.y), this.width, this.height, this.color);

             System.out.println("The ship went off the right in Player A's Grid. I recorrected this to shift left by " + excess/45 + " squares.");


         }

         if(p.x + this.width*45 > 975 && p.x > 520) {


//             excess = 975 - this.width;
             excess = ((int) p.x + this.width) - 975;

             toReturn = new Rectangle((int)(p.x - excess), (int)(p.y), this.width, this.height, this.color);

             System.out.println("The ship went off the right in Player B's Grid. I recorrected this to shift left by " + excess/45 + " squares.");


         }

         return toReturn;

     }

     public boolean equals(Rectangle r) {


         if(this.x == r.x && this.y==r.y && this.width == r.width
                 && this.height == r.height && this.color == r.color) {

             return true;
         }

         return false;


     }


}
