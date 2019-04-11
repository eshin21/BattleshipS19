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
		
		this.armada_A = new Ship[];
		this.armada_B = new Ship[];
		this.moveCount = 0;
		this.shipsPlacedA = 0;
		this.shipsPlacedB = 0;
		this.gameOver = false;
		this.playerA_status = 0;
		this.playerB_status = 0;
		
		
	}
	
	public void play() {
		
	
		
		while(moveCount <= 1 & armada_A.length() != 5 & armada_B.length() != 5) {
			placeShip();
			
		}
		if(moveCount % 2 == 0 & this.gameOverStatus == false) {
			System.out.println("It's Player 1's turn.");
			
		
		}
		
		else if(moveCount % 2 == 1 & this.gameOverStatus == false) {
			System.out.println("It's Player 2's turn.");
		
		
		}
		
		else { 
			System.out.println("Game over!");
			if(playerA_status > 0)
				System.out.print(" Player one wins!");
			else if (playerB_status > 0)
				System.out.print(" Player two wins!");
		}
	
		
		
	}
		
	public boolean gameOverStatus() {
		
		
			if(this.armada_A.length() == 0) {
				playerA_status = 0;
				gameOver = false;
			}
			else if(this.armada_B.length() == 0) {
				playerB_status = 0;
				gameOver=false;
			}
				
			else {
				gameOver = true;
			}
			
			return gameOver;

	}

	
}
	

class MouseInput extends JPanel implements MouseListener{
    public static final int WIDTH = 1024;
    public static final int HEIGHT = 768;
    public static final int FPS = 60;
    World world;
    Pair newAccel = new Pair(0,0);
    
    class Runner implements Runnable{
	public void run()
	{
	    while(true){
		world.updateSpheres(1.0 / (double)FPS);
		repaint();
		try{
		    Thread.sleep(1000/FPS);
		}
		catch(InterruptedException e){}
	    }

	}
    
    }
	
