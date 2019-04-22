import javax.swing.JPanel;
import javax.swing.JFrame;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Dimension;
import java.util.Random;
import java.awt.event.*;
import java.util.ArrayList;
import java.awt.Font;
import java.awt.Graphics2D;


////////MAKING THE "MAIN" OBJECT/////////////////////////////////
////////Purpose: open JPanel, manage the Coordinate Grids (For "locking in" user-selected points), keep track of which rectangles have been drawn
////////////////////////////////////////////////////////////////


public class Main extends JPanel implements MouseListener{
	public static final int BOX_WIDTH = 1024;
	public static final int BOX_HEIGHT = 768;
	public static CoordinateRange[][] GridA;
	public static CoordinateRange[][] GridB;
	public  Pair point;
	public ArrayList<Rectangle> rects = new ArrayList<Rectangle>();
	ArrayList<Ship> armadaA = new ArrayList<Ship>();
	ArrayList<Ship> armadaB = new ArrayList<Ship>();
	public static shipButton[][] myShipButtonsArray = shipButton.makeShipButtons();
	public static Button[] gameButtons = Button.makePlayButtons();
	public static Game myGame = new Game();
	public boolean quit = false;
	public Ship currentShip;


	public Main(){
			this.setPreferredSize(new Dimension(BOX_WIDTH, BOX_HEIGHT));


	}


public static void main (String[] args){

		Main newMain = new Main();

		if(newMain.quit == false) {
		JFrame frame = new JFrame("B A T T L E S H I P");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setContentPane(newMain);
		frame.pack();
		frame.setVisible(true);
		myGame.play();

		}

		else {
			newMain = new Main();
			System.out.println("You've started a new game.");
			newMain.quit = false;
			JFrame frame = new JFrame("B A T T L E S H I P");
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.setContentPane(newMain);
			frame.pack();
			frame.setVisible(true);
			myGame.play();

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

public void draw(Graphics g, int startX, int startY){
			int row = 10;
			int column = 10;
			int rowX = startX;
			int rowY = startY;
			int columnX = startX;
			int columnY = startY;

			this.addMouseListener(this);

			this.setPositions(); /////CALL SETPOSITIONS METHOD

			for (int r=0; r<=row; r++){ ////draw grid
				g.setColor(Color.WHITE);
				g.fillRect(rowX,rowY,450,5);
				rowY += 45;
			}

			for (int c=0; c<=column; c++){
				if (c==10){ //adjust for gap at end
					g.setColor(Color.WHITE);
					g.fillRect(columnX,columnY,5,455);
				}
				g.setColor(Color.WHITE);
				g.fillRect(columnX,columnY,5,450);
				columnX += 45;
			}

			for(ship Button s: myShipButtonsArray[0]) { //draw buttons for Player A ships

				g.setColor(Color.RED);
				g.fillRect(s.x, s.y, s.xdim, s.ydim);
				g.setColor(Color.WHITE);



				Graphics2D g2d = (Graphics2D) g;
				g2d.setFont(new Font("SansSerif", Font.BOLD, 13));


				g2d.drawString(s.type, s.x, s.y-10);
			}

			for(ship Button s: myShipButtonsArray[1]) { //draw buttons for Player B ships

				g.setColor(Color.GREEN);
				g.fillRect(s.x, s.y, s.xdim, s.ydim);
				g.setColor(Color.WHITE);
				g.drawString(s.type, s.x, s.y-10);
			}


			for(Button b : gameButtons) {


				g.setColor(Color.YELLOW);
				g.fillRect(b.x, b.y, b.xdim,  b.ydim);
				g.setColor(Color.BLACK);
				if(b.type != "F I R E !") {
					g.drawString(b.type, b.x+12, b.y+30);
				}

				else {
					g.drawString(b.type, b.x+15, b.y+30);
				}
			}



		}

	public Pair findPoint (Pair point){ ////takes user click and decides what to do with this clicked point belongs to (e.g. "locking in" user input to make it correspond to its proper square, or making the point do something if it's located on a button)

			double x = point.x;
			double y = point.y;
			Pair cornerPoint = new Pair(25,25);

			if(x <= 475 && y <=475) { ///if click was in Grid A, call findPointA
				cornerPoint = findPointA(point);
			}
			else if(x>=520 && y<=475) {///if click was in Grid B, call findPointB
				cornerPoint = findPointB(point);
			}



			if(x >= 25 && y<=580 && y>=550) { //check whether point falls in ship buttons

			String type = null;

				for(ship Button s: myShipButtonsArray[0]) { // buttons for Player A ships

					if(s.inButton(point)) {
						type = s.type;
						Ship shipAdd = new Ship(point, s.length, s.length, Color.RED, 0);
						myGame.armada_A.add(shipAdd);
						currentShip = shipAdd;


					}
					cornerPoint = new Pair(s.x, s.y);

				}


				for(ship Button s: myShipButtonsArray[1]) { //buttons for Player B ships
					if(s.inButton(point)) {

						type = s.type;
						Ship shipAdd = new Ship(point, s.length, s.length, Color.GREEN, 0);
						myGame.armada_B.add(shipAdd);
						currentShip = shipAdd;
						//go do stuff: add this ship into the armada and set the length of the drawRect to 25*shiplength

				}
					cornerPoint = new Pair(s.x, s.y);
			}
				System.out.print("You've selected a " + type + " ship");
		}



			else if(x >= 320 && x<= 625 && y>=600 && y<=650) { //GENERAL REGION FOR GAMEPLAY BUTTONS

				String type = null;


				for(Button b : gameButtons) {
					if(b.inButton(point)) {
						type = b.type;
					//go do stuff: make the privacy shades for "next", make a battle method for when FIRE is clicked (checkHit)

					if(b.type == "Reset") {

						System.out.print("Resetting the board . . .");

						rects = new ArrayList<Rectangle>();


					}

					}


				}

				System.out.print("You've selected the " + type + " button.");

			}




			return cornerPoint;

	}


	///FIND ANCHOR POINT BASED ON USER CLICK IN GRID A

	public Pair findPointA (Pair point) {


		double minX = GridA[0][0].xrange.x; ///INITIALIZATION
		double maxX = GridA[0][0].xrange.y;
		double minY = GridA[0][0].yrange.x;
		double maxY = GridA[0][0].yrange.y;

		Pair corner = new Pair(0,0);

			for(int i = 0; i<10; ++i) {

				for(int j = 0; j<10; ++j) {
					minX = GridA[i][j].xrange.x;
					maxX = GridA[i][j].xrange.y;
					minY = GridA[i][j].yrange.x;
					maxY = GridA[i][j].yrange.y;
					if(point.x >= minX && point.x <= maxX && point.y >= minY && point.y <= maxY )
						corner = new Pair(minX, minY);

				}

			}


			return corner;


}

	///FIND ANCHOR POINT BASED ON USER CLICK IN GRID B

	public Pair findPointB (Pair point) {



		double minX = GridB[0][0].xrange.x;
		double maxX = GridB[0][0].xrange.y;
		double minY = GridB[0][0].yrange.x;
		double maxY = GridB[0][0].yrange.y;


		Pair corner = new Pair(0,0);

			for(int i = 0; i<10; ++i) {

				for(int j = 0; j<10; ++j) {

					minX = GridB[i][j].xrange.x;
					maxX = GridB[i][j].xrange.y;
					minY = GridB[i][j].yrange.x;
					maxY = GridB[i][j].yrange.y;
					if(point.x >= minX && point.x <= maxX && point.y >= minY && point.y <= maxY )
						corner = new Pair(minX, minY);

				}


			}


			return corner;


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
	public void update(Graphics g) {

		paintComponent(g);


	}


	@Override //default method that will run as part of graphics

	public void paintComponent(Graphics g) {

		super.paintComponent(g);
		g.setColor(Color.BLACK); // sets background color
		g.fillRect(0, 0, BOX_WIDTH, BOX_HEIGHT);

		draw(g,25,25);
		draw(g,520,25);

		if(rects != null) { //NEED TO MAKE ARRAYLIST OF RECTANGLES SO REPAINT DOESN'T DELETE THEM EVERY TIME WE DRAW A NEW RECTANGLE

			for(Rectangle r : rects) {
				g.setColor(r.color);
				g.fillRect(r.x, r.y, 45, r.height);

				}

			}


		if(this.currentShip != null){


			if(this.point != null && this.point.x >= 25 && this.point.y <= 475) {


				g.setColor(Color.RED); //set color
				Pair corner = this.findPoint(this.point); //find corner
				System.out.println("The key corner is " + corner);
				Rectangle add = new Rectangle((int)corner.x, (int)corner.y,45,currentShip.length*45, Color.RED); //make new rectangle based on this corner


				///EDGE AWARENESS: ADD IT ONLY IF THE COORDINATE & LENGTH ARE COMPATIBLE
				rects.add(add); //add to arraylist of rectangles
				g.fillRect((int)corner.x, (int)corner.y,45,currentShip.length*45); // necessary--otherwise it won't draw until the next click

			}

			if(this.point != null && this.point.x >= 520 && this.point.y <= 475 ) {

				g.setColor(Color.GREEN);
				Pair corner = this.findPoint(this.point);
				System.out.println("The key corner is " + corner);
				Rectangle add = new Rectangle((int)corner.x, (int)corner.y,45,currentShip.length*45, Color.GREEN); //make new rectangle based on this corner
				rects.add(add); //add to arraylist of rectangles
				g.fillRect((int)corner.x, (int)corner.y,45,currentShip.length*45); // necessary--otherwise it won't draw until the next click

			}

		}


	}

}
