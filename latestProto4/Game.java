import java.util.ArrayList;
import java.util.LinkedList;



///////////////////GAME CLASS: MANAGES SHIP ARRAYS, MOVE COUNT, CHECKS GAME OVER, HIT/MISS

public class Game{
    public int moveCount;
    public boolean gameOver; //need to keep track of when game stops
    public LinkedList<Ship> armada_A = new LinkedList<Ship>();
    public LinkedList<Ship> armada_B = new LinkedList<Ship>();
    public LinkedList<Ship> armada_CPU = new LinkedList<Ship>();

    public boolean [][] playerA_move = new boolean [10][10];
    public boolean [][] playerB_move = new boolean [10][10];

    public Game() {
        this.moveCount = 0;
        this.gameOver = false;
    }


    public void play() {
        
        if(this.armada_A.size() == 0) {
            gameOver = true;
            
        }
        else if(this.armada_B.size() == 0) {
            gameOver=true;
        }
        
        if (this.gameOver){
            System.out.println("Game over!");
            if(armada_A.size() > 0)
                System.out.print(" Player one wins!");
            else if (armada_B.size() > 0)
                System.out.print(" Player two wins!");
        }
    }



		public boolean hitShip (Pair p, boolean isPlayerA, Weapon w){
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
