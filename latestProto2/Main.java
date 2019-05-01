import javax.swing.JPanel;
import javax.swing.JFrame;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Dimension;
import java.util.Random;
import java.awt.event.*;
import java.util.LinkedList;
import java.awt.Font;
import java.awt.Graphics2D;
import java.util.Scanner;


////////MAKING THE "MAIN" OBJECT/////////////////////////////////
////////Purpose: open JPanel, manage the Coordinate Grids (For "locking in" user-selected points), keep track of which rectangles have been drawn
////////////////////////////////////////////////////////////////


public class Main extends JPanel implements MouseListener, KeyListener{
	public static final int BOX_WIDTH = 1024;
	public static final int BOX_HEIGHT = 768;
	public static CoordinateRange[][] GridA;
	public static CoordinateRange[][] GridB;

	public Pair point;
	public Pair targetPoint;

	public static shipButton[][] myShipButtonsArray = shipButton.makeShipButtons();
	public static Button[] gameButtons = Button.makePlayButtons();
	private static Game myGame = new Game();
	public boolean quit = false;

	public Ship currentShip;
	public int indexA = -1;
	public int indexB = -1;
	public Ship currentShipA; 
	public Ship currentShipB;
	public Graphics g;
	

	public Main(){
			this.setPreferredSize(new Dimension(BOX_WIDTH, BOX_HEIGHT));
	}


public static void main (String[] args){

		Main newMain = new Main();
		JFrame frame = new JFrame("B A T T L E S H I P");

		if(newMain.quit == false) { // WORK IN PROGRESS, QUIT FUNCTION
		    newMain.addMouseListener(newMain);
			newMain.addKeyListener(newMain);
			newMain.setEnabled(false);
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.setContentPane(newMain);
			frame.pack();
			frame.setVisible(true);
			frame.setFocusable(true);
			getMyGame().play();		}

		else {
			frame.setVisible(true);
			frame.dispose();
			getMyGame().play();
			frame.dispose();
		}

	}


/////////SET POSITION GRIDS FOR LOCKING//////////////////////

public void setPositions() {

	GridA = new CoordinateRange[10][10];
	GridB = new CoordinateRange [10][10];

	for(int i = 0; i< 10; i++) {

		for(int j = 0; j<10; ++j) {

			Pair xrange = new Pair(25+45*i, 70+45*i);

			Pair yrange = new Pair(25+45*j, 70+45*j);
			GridA[i][j] = new CoordinateRange(xrange, yrange);

		}

	}

	for(int i = 0; i< 10; i++) {

		for(int j = 0; j<10; ++j) {

			Pair xrange = new Pair(520+45*i, 565+45*i);

			Pair yrange = new Pair(25+45*j, 70+45*j);
			GridB[i][j] = new CoordinateRange(xrange, yrange);

		}

	}

}

public void drawGrid(Graphics g, int startX, int startY){
			int row = 10;
			int column = 10;
			int rowX = startX;
			int rowY = startY;
			int columnX = startX;
			int columnY = startY;

			this.setPositions(); // CALL SETPOSITIONS METHOD

			for (int r=0; r<=row; r++){ // draw grid
				g.setColor(Color.WHITE);
				g.fillRect(rowX,rowY,450,5);
				rowY += 45;
			}

			for (int c=0; c<=column; c++){
				if (c==10){ // adjust for gap at end
					g.setColor(Color.WHITE);
					g.fillRect(columnX,columnY,5,455);
				}
				g.setColor(Color.WHITE);
				g.fillRect(columnX,columnY,5,450);
				columnX += 45;
			}

			for(shipButton s: myShipButtonsArray[0]) { //draw buttons for Player A ships

				g.setColor(Color.RED);
				g.fillRect(s.x, s.y, s.xdim, s.ydim);
				g.setColor(Color.WHITE);



				Graphics2D g2d = (Graphics2D) g;
				g2d.setFont(new Font("Courier", Font.BOLD, 13));

				if (s.type != "Light Cruiser"){
					g2d.drawString(s.type, s.x, s.y-10);
				}
				else{
					g2d.drawString("Light",s.x, s.y-20);
					g2d.drawString("Cruiser",s.x, s.y-10);
				}
			}

			for(shipButton s: myShipButtonsArray[1]) { //draw buttons for Player B ships

				g.setColor(Color.GREEN);
				g.fillRect(s.x, s.y, s.xdim, s.ydim);
				g.setColor(Color.WHITE);

				Graphics2D g2d = (Graphics2D) g;
				g2d.setFont(new Font("Courier", Font.BOLD, 13));

				if (s.type != "Light Cruiser"){
					g2d.drawString(s.type, s.x, s.y-10);
				}
				else{
					g2d.drawString("Light",s.x, s.y-20);
					g2d.drawString("Cruiser",s.x, s.y-10);
				}


			}


			for(Button b : gameButtons) {


				g.setColor(Color.YELLOW);
				g.fillRect(b.x, b.y, b.xdim,  b.ydim);
				g.setColor(Color.BLACK);
				if(b.type != "F I R E !") { // SPECIAL CASE FOR "FIRE" BUTTON
					g.drawString(b.type, b.x+12, b.y+30);
				}

				else {
					g.drawString(b.type, b.x+12, b.y+30);
				}
			}



		}

	public Pair findPoint (Pair point){ ////takes user click and decides what to do with this clicked point belongs to (e.g. "locking in" user input to make it correspond to its proper square, or making the point do something if it's located on a button)

			double x = point.x;
			double y = point.y;

			Pair cornerPoint = new Pair(25,25);

			if(x <= 475 && y <=475) { ///if click was in Grid A, call findPointA
				cornerPoint = point.findPointA(GridA);
			}
			else if(x>=520 && y<=475) { ///if click was in Grid B, call findPointB
				cornerPoint = point.findPointB(GridB);
			}

			if(x >= 25 && y<=580 && y>=550) { //check whether point falls in row of ship buttons
			    String type = null;
				for (shipButton s: myShipButtonsArray[0]) { // buttons for Player A ships
				    
					if (s.inButton(point)) {
						
					    
					    type = s.type;
						Ship shipAdd = new Ship(type, point, 45, s.length*45, Color.RED, "A");
						this.currentShip = shipAdd;
						this.currentShipA = shipAdd;
					}
				}

				for (shipButton s : myShipButtonsArray[1]) { // buttons for Player B ships
				    if (s.inButton(point)) {
						type = s.type;
						Ship shipAdd = new Ship(type, point, 45, s.length*45, Color.GREEN, "B");
						this.currentShip = shipAdd;
						this.currentShipB = shipAdd;		
				}
			}
				
                System.out.print("You've selected a " + type + " ship");


		}



			else if(x >= 200 && x<= 635 && y>=600 && y<=710) { // GENERAL REGION FOR GAMEPLAY BUTTONS

				String type = "NA";
				
				for(Button b : gameButtons) {
					if(b.inButton(point)) {
						type = b.type;
						
						if(b.type.equals("Reset A")) {

							System.out.print("Resetting Player A's board . . .");
							getMyGame().armada_A = new LinkedList<Ship>();

						}

						else if(b.type.equals("Reset B")) {
                            System.out.print("Resetting Player B's board . . .");
                            getMyGame().armada_B = new LinkedList<Ship>();

                        }

						else if (b.type.equals("Next Turn")){
							getMyGame().moveCount++;
							System.out.println(getMyGame().moveCount);
						}

						else if(b.type.equals("Move")) {
						        
						    System.out.println("You've selected the Move button. Press 'r' to rotate your last placed ship, 's' to shift it down, and 'q' to exit rotate mode.");
						    setEnabled(true);
						    requestFocusInWindow();

						}

						else if (b.type.equals("Erase")) {
						    System.out.println("Erasing your last placed ship.");
						    
						    if(this.currentShip.type.equals("A") && myGame.armada_A.size() == 0)
						        return cornerPoint;
						        
						    else if(myGame.armada_A.size() >0 && this.currentShipA != null && this.currentShip.player.equals("A")) {
						           	myGame.armada_A.removeLast();
						           	repaint();
						        }
						    
						    else if(this.currentShip.type.equals("B") && myGame.armada_B.size() == 0)
                                return cornerPoint;
						        else if(this.currentShipB != null && this.currentShip.player.equals("B")) {
						            myGame.armada_B.removeLast();
						            repaint();
						        }
						  }
					}
				}
			}


		return cornerPoint;

	}


	
@Override
	 public void mouseClicked(MouseEvent e) {

	  if(e.getButton() == 1){
		 this.point = new Pair(e.getX(), e.getY());
		 System.out.println("You clicked "  + this.point);
		 findPoint(this.point); //now take this point and go do stuff with it
		 this.repaint();

	  }

	 }

///////////IGNORE THESE : NECESSARY FOR COMPILING

	@Override
	 	public void mouseEntered(MouseEvent e){
	  // TODO Auto-generated method stub


	 }

	 @Override
	 public void mouseExited(MouseEvent e) {
	  // TODO Auto-generated method stub

	 }

	 @Override
	 public void mousePressed(MouseEvent e) {
	  // TODO Auto-generated method stub

	 }

	 @Override
	 public void mouseReleased(MouseEvent e) {
	  // TODO Auto-generated method stub

	 }

/////////////////////////////END IGNORE



	@Override
	public void update(Graphics g) { // currently not used

		paintComponent(g);

	}


	@Override //default method that will run as part of graphics

	public void paintComponent(Graphics g) {

		super.paintComponent(g);
		g.setColor(Color.BLACK); // sets background color
		g.fillRect(0, 0, BOX_WIDTH, BOX_HEIGHT);

		g.setColor(Color.DARK_GRAY);
		g.fillRect(0,0,BOX_WIDTH,25);

		// draw grids
		drawGrid(g,25,25);
		drawGrid(g,520,25);

		Graphics2D g2d = (Graphics2D) g;
		g2d.setFont(new Font("Courier", Font.BOLD, 13));

		// OPENING

		if (myGame.moveCount==0){
			g.setColor(Color.WHITE);
			g2d.drawString("WELCOME TO BATTLESHIP. You've started a new game. To START, PLAYER 1, press the NEXT TURN button.", 30, 17);
		}

		

        // MAKE SHIPS and ADD TO ARMADAS
        
      if(this.currentShip != null && myGame.armada_A != null) { //NEED TO MAKE ARRAYLIST OF RECTANGLES SO REPAINT DOESN'T DELETE THEM EVERY TIME WE DRAW A NEW RECTANGLE
                for(Ship s : myGame.armada_A){
                    System.out.println("Armada A has a " + s);
                    g.setColor(s.color);
                    g.fillRect((int)s.position.x, (int)s.position.y, s.xdim, s.ydim);
          }
      }

      if(this.currentShip != null && myGame.armada_B != null) { //NEED TO MAKE ARRAYLIST OF RECTANGLES SO REPAINT DOESN'T DELETE THEM EVERY TIME WE DRAW A NEW RECTANGLE
             for(Ship s: getMyGame().armada_B) {
                 g.setColor(s.color);
                 g.fillRect((int)s.position.x, (int)s.position.y, s.xdim, s.ydim);

             }
    }


		
		if(this.currentShip != null){
            g.setColor(Color.WHITE);
            g2d.drawString("You've selected a " + currentShip.type + " ship", 30, 17);

            if(this.point.x >= 25 && this.point.y <= 475 && this.point.x <=475) { // in grid A
                g.setColor(Color.RED);
                currentShipA.placeShipA(g, this.currentShipA, this, Color.RED);

            }

            if(this.point != null && this.point.x >= 520 && this.point.y <= 475 && this.point.x <= 970 /*&& this.currentShipB != null*/) { // in grid B
                g.setColor(Color.GREEN);
                 this.currentShipB.placeShipB(g, this.currentShipB, this, Color.GREEN);
//                 g.fillRect((int)currentShipB.position.x, (int) currentShipB.position.y, currentShipB.xdim, currentShipB.ydim);
            }

    }
		
		
		

		// DRAWS BLACK BOX OVER NON-PLAYER'S BOARD

		if (myGame.moveCount==1){ // first player places ships
			g.setColor(Color.BLACK);
			g.fillRect(520,25,470,470);
			g.setColor(Color.RED);
			g2d.drawString("PLAYER 1, select a ship below. Then, click where you would like to place the ship on the grid.", 30, 500);
			g2d.drawString("After placing, you can move the ship (click 'Move' button and press 'r' to rotate, 'WASD' to shift down, 'q' to cancel.", 30, 515);
	
			
		}
		else if (myGame.moveCount==2){ // 2nd player places ships
			g.setColor(Color.BLACK);
			g.fillRect(25,25,470,470);
			g.setColor(Color.GREEN);
			g2d.drawString("PLAYER 2, select a ship below. Then, click where you would like to place the ship on the grid.", 30, 500);
			g2d.drawString("After placing, you can rotate the ship (click 'rotate' button and press 'r'), or press 's' to shift the ship down", 30, 515);
			g2d.drawString("After placing, you can move the ship (click 'Move' button and press 'r' to rotate, 'WASD' to shift ship, 'q' to cancel.", 30, 515);

		}
		else if (myGame.moveCount%2==0 && myGame.moveCount>2){ //if move is even(2nd player)
			g.setColor(Color.BLACK);
			g.fillRect(25,25,470,470);
			drawGrid(g,25,25);
			g.setColor(Color.RED);
			g2d.drawString("PLAYER 2, select a target square on PLAYER 2's board.", 540, 495);

			targetPoint = findPoint(this.point);

		}
		else if (myGame.moveCount%2!=0 && myGame.moveCount>2){ // 1st player
			g.setColor(Color.BLACK);
			g.fillRect(520,25,470,470);
			drawGrid(g,520,25);
			g.setColor(Color.GREEN);
			g2d.drawString("PLAYER 1, select a target square on PLAYER 2's board.", 30, 495);

			//get target point
		}

	}

	public void addNotify() {
	        super.addNotify();
	        requestFocus();
	    }
	

	@Override
	public void keyPressed(KeyEvent e) {

        char c=e.getKeyChar();
        System.out.println("You pressed down: " + c);

        if(c=='q') {
            System.out.println("Exiting rotate mode");
            setEnabled(false);
        }
        
        if(c=='w') {
            System.out.println("Shifting up...");
            
            if(this.currentShipA != null && this.currentShip.player.equals("A")) {
                System.out.println("Shifting up this " + this.currentShip.type );                
                getMyGame().armada_A.getLast().shiftUp();
                
            }
            else if(this.currentShipB!= null && this.currentShip.player.equals("B")) {
                System.out.println("Shifting up this " + this.currentShip.type );                
                getMyGame().armada_B.getLast().shiftUp();
            }
            
            repaint();
        }
        
        
        if(c=='a') {
            System.out.println("Shifting left...");
            
            if(this.currentShipA != null && this.currentShip.player.equals("A")) {
                System.out.println("Shifting left this " + this.currentShip.type );                
                getMyGame().armada_A.getLast().shiftLeft();
                
            }
            else if(this.currentShipB!= null && this.currentShip.player.equals("B")) {
                System.out.println("Shifting left this " + this.currentShip.type );                
                getMyGame().armada_B.getLast().shiftLeft();
            }
            
            repaint();
        }
        
        
        if(c=='s') {
            System.out.println("Shifting down...");
            
            if(this.currentShipA != null && this.currentShip.player.equals("A")) {
                System.out.println("Shifting down this " + this.currentShip.type );                
                getMyGame().armada_A.getLast().shiftDown();
                
            }
            else if(this.currentShipB!= null && this.currentShip.player.equals("B")) {
                System.out.println("Shifting down this " + this.currentShip.type );                
                getMyGame().armada_B.getLast().shiftDown();
            }
            
            repaint();
        }
        
        if(c=='d') {
            System.out.println("Shifting right...");
            
            if(this.currentShipA != null && this.currentShip.player.equals("A")) {
                System.out.println("Shifting right this " + this.currentShip.type );                
                getMyGame().armada_A.getLast().shiftRight();
                
            }
            else if(this.currentShipB!= null && this.currentShip.player.equals("B")) {
                System.out.println("Shifting right this " + this.currentShip.type );                
                getMyGame().armada_B.getLast().shiftRight();
            }
            
            repaint();
        }
            
            
        if(c=='r') {


            if(currentShip.player.equals("A") && currentShipA != null) {

                System.out.println("///////////////////****TEST");
                Ship toRotate = myGame.armada_A.getLast();
                Ship rotated = toRotate.rotate(this);
                if(!rotated.checkArmadaOverlap()) {
                    System.out.println("No issues here.");
                    getMyGame().armada_A.removeLast();
                	getMyGame().armada_A.addLast(rotated);
                   this.repaint();
                   setEnabled(false);

                }
                
                else if(rotated.checkArmadaOverlap()){
                	System.out.println("MAIN: Can't rotate this ship due to overlap.");
                    setEnabled(false);
                	this.repaint();
                }
                
                setEnabled(false);
                this.repaint();
            }
            
            if(currentShip.player.equals("B") && currentShipB != null) {

                System.out.println("///////////////////****TEST");
                Ship toRotate = myGame.armada_B.getLast();
                Ship rotated = toRotate.rotate(this);
                if(!rotated.checkArmadaOverlap()) {
                    System.out.println("No issues here.");
                    getMyGame().armada_B.removeLast();
                    getMyGame().armada_B.addLast(rotated);
                   this.repaint();
                   setEnabled(false);

                }
                
                else if(rotated.checkArmadaOverlap()){
                    System.out.println("MAIN: Can't rotate this ship due to overlap.");
                    setEnabled(false);
                    this.repaint();
                }
                
                setEnabled(false);
                this.repaint();
            }
            
        }
    }


    @Override
    public void keyReleased(KeyEvent e) {
        // TODO Auto-generated method stub

    }


    @Override
    public void keyTyped(KeyEvent e) {
        // TODO Auto-generated method stub

    }

      public static Game getMyGame() {		
        return myGame;		
    }		
    public static void setMyGame(Game myGame) {		
        Main.myGame = myGame;		
    }

}