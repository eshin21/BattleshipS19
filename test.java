import javax.swing.JPanel;
import javax.swing.JFrame;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Dimension;
import java.util.Random;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;

public class Test extends JPanel{
	public static final int BOX_WIDTH = 1024;
	public static final int BOX_HEIGHT = 768;

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


	@Override
	public void paintComponent(Graphics g) {
		g.setColor(Color.BLACK); // sets background color
		g.fillRect(0, 0, BOX_WIDTH, BOX_HEIGHT);

		draw(g,25,25);
		draw(g,520,25);
	}

}
