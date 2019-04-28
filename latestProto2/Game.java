import java.util.ArrayList;
import java.util.LinkedList;



///////////////////GAME CLASS: MANAGES SHIP ARRAYS, MOVE COUNT, CHECKS GAME OVER, HIT/MISS 

public class Game{
	public int moveCount;
	public int shipsPlacedA; //keeps track of whether player A has placed all ships
	public int shipsPlacedB; //keeps track of whether player B has placed all ships
	public boolean gameOver; //need to keep track of when game stops
	public int playerA_status;
	public int playerB_status;
	public LinkedList<Ship> armada_A = new LinkedList<Ship>();
	public LinkedList<Ship> armada_B = new LinkedList<Ship>();
	public boolean [][] playerA_move = new boolean [10][10];
	public boolean [][] playerB_move = new boolean [10][10];


	public Game() {
		this.moveCount = 0;
		this.shipsPlacedA = 0;
		this.shipsPlacedB = 0;
		this.gameOver = false;
		this.playerA_status = 0;
		this.playerB_status = 0;
	}

	public void play() {

		
		System.out.println("Get ready to play. . .");
		System.out.println("Player One: Place your ships by clicking one of the ship buttons below the grid.");
		System.out.println("Then, click the place on the grid where you want to place the ship.");
		
		while(moveCount <= 1) {
			placeShip();

		}
		if(moveCount % 2 == 0 && this.gameOver == false) {
			System.out.println("It's Player 1's turn.");


		}

		else if(moveCount % 2 == 1 && this.gameOver== false) {
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
			if(this.armada_A.size() == 0) {
				playerA_status = 0;
				gameOver = false;
			}
			else if(this.armada_B.size() == 0) {
				playerB_status = 0;
				gameOver=false;
			}

			else {
				gameOver = true;
			}

			return gameOver;

	}
	
	public boolean hitShip (Pair p){

	    return false;
	    
	}

	
	public void placeShip() {
				 	
		//left off here
		//spit out an x y when clicked
		//pass that x y into world update so that the update method can draw the ships
		
		
	}


}