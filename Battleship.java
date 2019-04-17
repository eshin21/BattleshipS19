import javax.swing.JPanel;
import javax.swing.JFrame;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Dimension;
import java.util.Random;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;


public class Battleship extends JPanel implements MouseListener{
	World world;
	public static final int WIDTH = 1024;
  public static final int HEIGHT = 768;
  public static final int FPS = 60;
	public static CoordinateRange[][] GridA;
	public static CoordinateRange[][] GridB;
	public static Pair point;
	public ArrayList<Rectangle> rects = new ArrayList<Rectangle>();

	public static void main(String[] args){
		world = new World(WIDTH, HEIGHT);
		JFrame frame = new JFrame("B A T T L E S H I P");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setContentPane(new World());
		frame.pack();
		frame.setVisible(true);
	}

} //Battleship

class World{
    int height;
    int width;

		public Ship[] armada_A;
	 	public Ship[] armada_B;
		Game game;

    public World(int initWidth, int initHeight){
			width = initWidth;
			height = initHeight;
			game = new Game();
			this.setPreferredSize(new Dimension(BOX_WIDTH, BOX_HEIGHT));
    }

		/////////SET POSITION GRIDS FOR LOCKING//////////////////////

		public void setPositions() {

			GridA = new CoordinateRange[10][10];
			GridB = new CoordinateRange[10][10];

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
					GridB[i][j] = new CoordinateRange(xrange,yrange);

				}

			}


		}

    public void drawGrid(int startX, int startY, Graphics g){
			int row = 10;
			int column = 10;
			int rowX = startX;
			int rowY = startY;
			int columnX = startX;
			int columnY = startY;

			this.addMouseListener(this);

			this.setPositions(); /////CALL SETPOSITIONS METHOD (generates the positions of anchor points to lock in user selections to a discrete square/////

			for (int r=0; r<row; r++){
				Pair rowRange = new Pair(rowX,rowX+45);
				rowX += 45;
				rowY += 45;
				g.fillRect(rowX,rowY,450,5);
			}
			for (int c=0; c<column; c++){
				Pair columnRange = new Pair(rowX,rowY+45);
				columnX += 45;
				columnY += 45;
				g.fillRect(columnX,columnY,5,450);
			}
    }

    public void updateArmada(double time){
			for (int i = 0; i < numSpheres; i++)
			    spheres[i].update(this, time);
		}

		public Pair findPoint (Pair point){ ////takes user click and decides which square this clicked point belongs to ("locking in" user input)

				double x = point.x;
				double y = point.y;
				Pair cornerPoint = new Pair(25,25);

				if(x <= 475 && y <=475) ///if click was in Grid A, call findPointA
					cornerPoint = findPointA(point);
				else if(x>=520 && y<=475)///if click was in Grid B, call findPointB
					cornerPoint = findPointB(point);

				return cornerPoint;

		}


		///FIND ANCHOR POINT BASED ON USER CLICK IN GRID A

		public Pair findPointA (Pair point) {


			double minX = GridA[0][0].xcoord.x; ///INITIALIZATION
			double maxX = GridA[0][0].xcoord.y;
			double minY = GridA[0][0].ycoord.x;
			double maxY = GridA[0][0].ycoord.y;

			Pair corner = new Pair(0,0);

				for(int i = 0; i<10; ++i) {

					for(int j = 0; j<10; ++j) {
						minX = GridA[i][j].xcoord.x;
						maxX = GridA[i][j].xcoord.y;
						minY = GridA[i][j].ycoord.x;
						maxY = GridA[i][j].ycoord.y;
						if(point.x >= minX && point.x <= maxX && point.y >= minY && point.y <= maxY )
							corner = new Pair(minX, minY);

					}

				}

				return corner;


	}

		///FIND ANCHOR POINT BASED ON USER CLICK IN GRID B

		public Pair findPointB (Pair point) {



			double minX = GridB[0][0].xcoord.x;
			double maxX = GridB[0][0].xcoord.y;
			double minY = GridB[0][0].ycoord.x;
			double maxY = GridB[0][0].ycoord.y;


			Pair corner = new Pair(0,0);

				for(int i = 0; i<10; ++i) {

					for(int j = 0; j<10; ++j) {

						minX = GridB[i][j].xcoord.x;
						maxX = GridB[i][j].xcoord.y;
						minY = GridB[i][j].ycoord.x;
						maxY = GridB[i][j].ycoord.y;
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

		 @Override
 		public void paintComponent(Graphics g) {

 			super.paintComponent(g);
 			g.setColor(Color.BLACK); // sets background color
 			g.fillRect(0, 0, BOX_WIDTH, BOX_HEIGHT);

 			drawGrid(g,25,25);
 			drawGrid(g,520,25);

 			if(rects != null) { //NEED TO MAKE ARRAYLIST OF RECTANGLES SO REPAINT DOESN'T DELETE THEM EVERY TIME WE DRAW A NEW RECTANGLE
 				for(Rectangle r : rects) {
 					g.setColor(r.color);
 					g.fillRect(r.x, r.y, 45, 45);
 				}
 			}

 			if(this.point != null && this.point.x >= 25 && this.point.y <= 475) {


 				g.setColor(Color.RED); //set color
 				Pair corner = this.findPoint(this.point); //find corner
 				System.out.println("The key corner is " + corner);
 				Rectangle add = new Rectangle((int)corner.x, (int)corner.y,45,45, Color.RED); //make new rectangle based on this corner
 				rects.add(add); //add to arraylist of rectangles


 				g.fillRect((int)corner.x, (int)corner.y, 45, 45); //somehow necessary--otherwise it won't draw until the next click

 			}

 			if(this.point != null && this.point.x >= 520 && this.point.y <= 475) {


 				g.setColor(Color.GREEN);

 				Pair corner = this.findPoint(this.point);

 				System.out.println("The key corner is " + corner);

 				Rectangle add = new Rectangle((int)corner.x, (int)corner.y,45,45, Color.GREEN);
 				rects.add(add);


 				g.fillRect((int)corner.x, (int)corner.y, 45, 45); //necessary

 			}

 		}
}
