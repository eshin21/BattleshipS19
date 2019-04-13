import java.awt.Color;
import java.awt.Graphics;

/////////////////////////////
// Enumerates ship.
////////////////////////////

public class Ship{
	Pair position;
	int length;
	int health;
	Color color;
	Cell start;
	int player; //denotes which player this ship belongs to

	public Ship(Pair p, int length, int health, Color color, int player){

		this.position = p;
		this.length = length;
		this.health = health;
		this.color = color;
		this.player = player;
	}

	public void changePosition(Pair p) {
		this.position.x = p.x;
		this.position.y = p.y;

	}

	public void draw(Graphics g){
    	Color c = g.getColor();
    	g.setColor(color);
 ///   	g.drawSquare((int)(position.x - radius), (int)(position.y - radius), (int)(2*radius), (int)(2*radius));
    //	g.setColor(c);
    	
    }


}
