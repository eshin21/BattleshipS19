import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;

public class Game{
	
	private Ship[] grid;
	private int moveCount;
	public int shipsPlacedA; //keeps track of whether player A has placed all ships
	public int shipsPlacedB; //keeps track of whether player B has placed all ships
	public boolean gameOver; //need to keep track of when game stops
	
	public Game() {
		
		this.grid = new Ship[];
		this.moveCount = 0;
		
	}
	
	public void play() {
		
		
		if(moveCount % 2 == 0) {
			System.out.println("It's Player 1's turn: pass the computer.");
			
		}
		
		else
			System.out.println("It's Player 2's turn: pass the computer.");
		
		
		if(moveCount <= 1) {
			System.out.println("Place your ships.")
			
			while(shipsPlaced == false) {
				
				
				
			}

			
		}
		
		
	
		
		
	}
	
}
