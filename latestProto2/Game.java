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
        if (this.gameOver){
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

		public boolean hitShip (Pair p, boolean isPlayerA){
			// if else to seperate armada a and break
			System.out.println(p.x + "," + p.y);
			if (isPlayerA){ // 1st player
				for (Ship s : armada_B){
					System.out.println(s.type + ";" + s.position.x + "," + (s.position.x+s.xdim) + ";" + s.position.y + "," + (s.position.y+s.ydim));
	   			if (p.x >= s.position.x && p.x <= (s.position.x+s.xdim)){ // within x bounds
						if (p.y >= s.position.y && p.y <= (s.position.y+s.ydim)){ // within y
							System.out.println("You hit player A's" + s.type);
							return true;
						}
					}
				}
			}
			else{ // 2nd player
				for (Ship s : armada_A){
					System.out.println(s.position.x + "," + (s.position.x+s.xdim) + ";" + s.position.y + "," + (s.position.y+s.ydim));
					if (p.x >= s.position.x && p.x <= (s.position.x+s.xdim)){
						if (p.y >= s.position.y && p.y <= (s.position.y+s.ydim)){
							System.out.println("You hit player B's" + s.type);
							return true;
						}
					}
				}
			}
			System.out.print("Miss!");
		  return false;
		}


}
