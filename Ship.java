import java.awt.Color;

/////////////////////////////
// Enumerates ship.
////////////////////////////

class Pair{
    public double x;
    public double y;
    public Pair(double initX, double initY){
    	x = initX;
    	y = initY;
        }
    
    public Pair add(Pair toAdd){
    	return new Pair(x + toAdd.x, y + toAdd.y);

    }
    
    public void flipX(){
    	x = -x;
    }
    
    public void flipY(){
    	y = -y;
    }
}

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
	
	
	
}

