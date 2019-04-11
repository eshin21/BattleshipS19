import java.awt.Color;

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



}
