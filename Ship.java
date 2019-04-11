import java.awt.Color;

/////////////////////////////
// Enumerates ship shape, health, weapons, explosions.
////////////////////////////

public class Ship{
	
	int x;
	int y;
	int length;
	int health;
	Color color;
	Cell start;
	int player; //denotes which player this ship belongs to 
	
	public Ship(int x, int y,  int length, int health, Color color, int player){
		this.x = x;
		this.y = y;
		this.length = length;
		this.health = health;
		this.color = color;		
		this.player = player
	}
	
	
	
	
}

