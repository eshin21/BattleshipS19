import javax.swing.JPanel;
import javax.swing.JFrame;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Dimension;
import java.util.Random;
import java.awt.event.*;


public class Test extends JPanel implements MouseListener{
	public static final int BOX_WIDTH = 1024;
	public static final int BOX_HEIGHT = 768;
	public Pair[][] positionGrid;
	public static Pair point;

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


public void draw(Graphics g, int startX, int startY){
			int row = 10;
			int column = 10;
			int rowX = startX;
			int rowY = startY;
			int columnX = startX;
			int columnY = startY;

			this.addMouseListener(this);
			for (int r=0; r<=row; r++){
				g.setColor(Color.WHITE);
				g.fillRect(rowX,rowY,450,5);
				rowY += 45;
				Pair columnRange = new Pair(rowX-45,rowY);
			}

			for (int c=0; c<=column; c++){
				if (c==10){ //adjust for gap at end
					g.setColor(Color.WHITE);
					g.fillRect(columnX,columnY,5,455);
				}
				g.setColor(Color.WHITE);
				g.fillRect(columnX,columnY,5,450);
				columnX += 45;
				Pair columnRange = new Pair(rowX-45,rowY);
			}
			
		}
	
	@Override
	 public void mouseClicked(MouseEvent e) {
		
	  if(e.getButton() == 1){
		
		 this.point = new Pair(e.getX(), e.getY());
		  System.out.println("You clicked "  + this.point);
		  
	   this.repaint();
	   // JOptionPane.showMessageDialog(null,e.getX()+ "\n" + e.getY());
	  }

	 }

	 @Override
	 public void mouseEntered(MouseEvent e) {
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
		g.setColor(Color.BLACK); // sets background color
		g.fillRect(0, 0, BOX_WIDTH, BOX_HEIGHT);

		draw(g,25,25);
		draw(g,520,25);
		
		if(this.point.x >= 25 && this.point.y <= 475) {
			
			int adj = (int) this.point.x % 25;
			
			
			
			Pair sq = new Pair(this.point.x, this.point.y);
			
			Pair a = this.point;
			g.setColor(Color.RED);
			g.fillRect((int)a.x, (int)a.y, 45, 45);
			
		}
		
		if(this.point.x >= 520 && this.point.y <= 475) {
			
			int adj = (int) this.point.x % 25;
			
			
			
			Pair sq = new Pair(this.point.x, this.point.y);
			
			Pair a = this.point;
			g.setColor(Color.BLUE);
			g.fillRect((int)a.x, (int)a.y, 45, 45);
			
		}

		
	}

}
