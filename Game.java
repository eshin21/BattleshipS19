import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import javax.swing.JPanel;
import javax.swing.JFrame;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Dimension;
import java.util.Random;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;

public class Game{
	
	private Ship[] armada_A;
	private Ship[] armada_B;
	public int moveCount;
	public int shipsPlacedA; //keeps track of whether player A has placed all ships
	public int shipsPlacedB; //keeps track of whether player B has placed all ships
	public boolean gameOver; //need to keep track of when game stops
	public int playerA_status;
	public int playerB_status;
	
	public Game() {
		
		this.grid = new Ship[];
		this.moveCount = 0;
		
	}
	
	public void play() {
		
		
		if(moveCount % 2 == 0 & this.checkGameStatus == true) {
			System.out.println("It's Player 1's turn.");
			
		}
		
		else if(moveCount &2 == 1 & this.checkGameStatus == true) {
			System.out.println("It's Player 2's turn.");
		}
		else 
			System.out.println("Game over!")
		
		
		
		
	public boolean checkGameStatus();
		
		if(this.armada_A.length() > 0 & this.armada_B.length() > 0){
			gameOver = false;
			if(this.armada_A.length() == 0)
				playerA_status = 0;
			else if(this.armada_B.length() == 0)
				playerB_status = 0;
			
				
		}
		else 
			gameOver = true;
		
	}
	
}
