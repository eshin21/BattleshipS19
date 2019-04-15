import javax.swing.JPanel;
import javax.swing.JFrame;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Dimension;
import java.util.Random;
import java.awt.event.*;
import java.util.ArrayList;


public class Test extends JPanel implements MouseListener{
	public static final int BOX_WIDTH = 1024;
	public static final int BOX_HEIGHT = 768;
	public static CoordinateRange[][] GridA;
	public static CoordinateRange[][] GridB;
	public static Pair point;
	public ArrayList<Rectangle> rects = new ArrayList<Rectangle>();
	

	public Test(){
			this.setPreferredSize(new Dimension(BOX_WIDTH, BOX_HEIGHT));
	}


public static void main (String[] args){
		JFrame frame = new JFrame("Test Window");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setContentPane(new Test());
		frame.pack();
		frame.setVisible(true);
	}


/////////SET POSITION GRIDS FOR LOCKING

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

			
			this.setPositions();
			
//			for(int i = 0; i<10; ++i) {
//				
//				for(int j = 0; j< 10; ++j)
//					System.out.println(GridB[i][j]);
//				
//			}
			

			for (int r=0; r<=row; r++){
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

		}

	public Pair findPoint (Pair point){
		
			double x = point.x;
			double y = point.y;
			double beginX;
			double endX;
			double beginY;
			double endY;
			Pair cornerPoint = new Pair(25,25);
			
			if(x <= 475 && y <=475)
				cornerPoint = findPointA(point);
			else if(x>=520 && y<=475)
				cornerPoint = findPointB(point);

			return cornerPoint;

	}
	
	
	///FIND POINT IN GRID A 
	
	public Pair findPointA (Pair point) { 
		
		
		double minX = GridA[0][0].xrange.x;
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

/////FIND POINT IN GRID B	
	
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
		 this.repaint();
		
	  }
		 
	   // JOptionPane.showMessageDialog(null,e.getX()+ "\n" + e.getY());
	  }

	 

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
	public void update(Graphics g) {
		
		paint(g);
		
	}
	 

	@Override
	public void paintComponent(Graphics g) {
		
		super.paintComponent(g);
		g.setColor(Color.BLACK); // sets background color
		g.fillRect(0, 0, BOX_WIDTH, BOX_HEIGHT);

		draw(g,25,25);
		draw(g,520,25);
		
		if(rects != null) {
			for(Rectangle r : rects) {	
				g.setColor(r.color);
				g.fillRect(r.x, r.y, 45, 45);
			}
		}

		if(this.point != null && this.point.x >= 25 && this.point.y <= 475) {


			g.setColor(Color.RED);
			Pair corner = this.findPoint(this.point);
			
			System.out.println("The key corner is " + corner);
			
			Rectangle add = new Rectangle((int)corner.x, (int)corner.y,45,45, Color.RED);
			rects.add(add);
			
			
			g.fillRect((int)corner.x, (int)corner.y, 45, 45);

		}

		if(this.point != null && this.point.x >= 520 && this.point.y <= 475) {


			g.setColor(Color.GREEN);
			
			Pair corner = this.findPoint(this.point);
			
			System.out.println("The key corner is " + corner);
			
			Rectangle add = new Rectangle((int)corner.x, (int)corner.y,45,45, Color.GREEN);
			rects.add(add);
			
			
			g.fillRect((int)corner.x, (int)corner.y, 45, 45);

		}


	}

}
