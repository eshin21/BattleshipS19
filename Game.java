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
	public int moveCount;
	public int shipsPlacedA; //keeps track of whether player A has placed all ships
	public int shipsPlacedB; //keeps track of whether player B has placed all ships
	public boolean gameOver; //need to keep track of when game stops
	public int playerA_status;
	public int playerB_status;

	public Game() {
		this.moveCount = 0;
		this.shipsPlacedA = 0;
		this.shipsPlacedB = 0;
		this.gameOver = false;
		this.playerA_status = 0;
		this.playerB_status = 0;
	}

	public void play() {


//		Battleship.world.armada_A = new Ship[5];
//		Battleship.world.armada_B = new Ship[5];
//		
//		
//		Pair p = new Pair(0,0);
//		Color color;
		
		
//		for(int i = 0; i<=4; ++i) {
//			armada_A[i] = new Ship(p, i+2, i+2, color.BLUE, 0);
//			armada_B[i] = new Ship(p, i+2, i+2, color.RED, 1);
//		}
		
		while(moveCount <= 1) {
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
	
	
	public void placeShip() {
				
		//left off here
		
		//spit out an x y when clicked
		//pass that x y into world update so that the update method can draw the ships
	
		
		
	}


class MouseShips extends JPanel implements MouseListener{
    	public static final int WIDTH = 1024;
    	public static final int HEIGHT = 768;
    	public static final int FPS = 60;
    	public Pair p;
    	World world;
    	
    	public MouseShips(){
    	    world = new World(WIDTH, HEIGHT, 5);
    	    addMouseListener(this);
    	    this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
    	    Thread mainThread = new Thread(new Runner());
    	    mainThread.start();
    	}

    	class Runner implements Runnable{
    		public void run(){
    			while(true){
    				Battleship.world.updateShips(1.0 / (double)FPS);
    					repaint();
    					try{	
    						Thread.sleep(1000/FPS);
    					}	
    					catch(InterruptedException e){}
    				}

    			}

    	}

    public void mouseClicked(MouseEvent e) {
    	int x = e.getX();
    	int y = e.getY();
    	p = new Pair(x,y); 

    }
    
    public void addNotify() {
        super.addNotify();
        requestFocus();
    }
    
  
}


/* class World{
	
    int height;
    int width;
    
    int numShips_A;
    int numShips_B;
    Ship armada_A[];
    Ship armada_B[];
    
    public World(int height, int width, int armadaSize) {
    	this.height = height;
    	this.width = width;
    	this.numShips_A = armadaSize;
    	this.numShips_B = armadaSize;
    	Pair p = new Pair(0,0);
    	Color color = null;
    	
    	for(int i = 0; i<=armadaSize-1; ++i) {
			armada_A[i] = new Ship(p, i+2, i+2, color.BLUE, 0);
			armada_B[i] = new Ship(p, i+2, i+2, color.RED, 1);
		}
    	
    	
    }
    
    public Ship[] getArmada_A() {
    	return this.armada_A;
    	
    }
    
    public Ship[] getArmada_B() {
    	return this.armada_B;
    	
    }
    
    public void drawShips(Graphics g){
    	for (int i = 0; i < this.numShips_A - 1; i++){
    	    this.armada_A[i].draw(g);
    	}
        }

        public void updateSpheres(double time){
    	for (int i = 0; i < numSpheres; i ++)
    	    spheres[i].update(this, time);
        }

    
	} */
}
