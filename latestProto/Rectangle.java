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
    	 this.width = 45;
    	 this.height = 45;
    	 this.color = color;
     }

     public Color getColor() {
         return color;
     }

     public void paint(Graphics g) {

         g.setColor(getColor());
         g.fillRect(this.x, this.y, 45,45);
         

     }
	
}
