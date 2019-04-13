import javax.swing.JPanel;
import javax.swing.JFrame;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Dimension;
import java.util.Random;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;


public class Battleship extends JPanel{
	World world;
	public static final int WIDTH = 1024;
  public static final int HEIGHT = 768;
  public static final int FPS = 60;

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

		public int[] positionGrid;
		public Ship[] armada_A;
	 	public Ship[] armada_B;
		Game game;

    public World(int initWidth, int initHeight){
			width = initWidth;
			height = initHeight;
			game = new Game();
			this.setPreferredSize(new Dimension(BOX_WIDTH, BOX_HEIGHT));
    }

    public void drawGrid(int startX, int startY, Graphics g){
			int row = 10;
			int column = 10;
			int rowX = startX;
			int rowY = startY;
			int columnX = startX;
			int columnY = startY;

			for (int r=0; r<row; r++){
				//create x and y pairs to set equal to index in int array
				rowX += 45;
				rowY += 45;
				g.fillRect(rowX,rowY,450,5);
			}
			for (int c=0; c<column; c++){
				//create x and y pairs to set equal to index in int array
				columnX += 45;
				columnY += 45;
				g.fillRect(columnX,columnY,5,450);
			}
    }

    public void updateArmada(double time){
			for (int i = 0; i < numSpheres; i++)
			    spheres[i].update(this, time);
		}

		public void paintComponent(Graphics g) {
			g.setColor(Color.BLACK); //background
			g.fillRect(0, 0, BOX_WIDTH, BOX_HEIGHT);

			//draw player 1 and 2's grids
			drawGrid(g,25,25);
			drawGrid(g,520,25);
		}
}
