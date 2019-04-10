/////////////////////////////
// Enumerates ship shape, health, weapons, explosions.
////////////////////////////

public interface Ship{
	
	int x;
	int y;
	int length;
	int health;
	Color color;
	Cell start;
	
	public Ship(int x, int y,  int length, int health, Color color){
		this.x = x;
		this.y = y;
		this.length = length;
		this.health = health;
		this.color = color;		
	}
	
	
	
	
}

class Schooner implements Ship{
	
	
	
	
	
}
