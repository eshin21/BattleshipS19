import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

/////////////////////////////
// Enumerates ship.
////////////////////////////

public class Ship{
	Pair position; //locked position of anchor rectangle
	int length;
	int health;
	Color color;
	public ArrayList<Rectangle> body = new ArrayList<Rectangle>();
	int player; //denotes which player this ship belongs to
	int index; //this ship's location in the armada ArrayList

	public Ship(Pair p, int length, int health, Color color, int player){

		this.position = p;
		this.length = length;
		this.health = health;
		this.color = color;
		this.player = player;
		
		for(int i = 0; i<length; ++i) {
			
			this.body.add(new Rectangle((int)p.x, (int)p.y+45*i, 45, 45, color));
		//add if statement to handle when ship goes off the grid
			
		}
	}

	public void changePosition(Pair p) {
		this.position.x = p.x;
		this.position.y = p.y;

	}

//	public void draw(Graphics g){
//    	Color c = g.getColor();
//    	g.setColor(c);
//    	for(int i = 0; i<body.size(); ++i) {
//    		
//    		g.drawRect(body.get(i).x, body.get(i).y, 45, 45);
//    		
//    		
//    	}
    	
//	}
	
    public static ArrayList<Ship> buildArmada(int size){
    	
    	ArrayList<Ship> armada = new ArrayList<Ship>(size);
    	
    	// for(int i = 0; i<armada.size(); ++i) {
    		
    	// 	armada.get(i) = new Ship(0,0, 45, 45 );
    		
    	// }
    	return armada;

    }
    	
    	
    

    	
    	
    


}
