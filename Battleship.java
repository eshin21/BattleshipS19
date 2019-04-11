import javax.swing.JPanel;
import javax.swing.JFrame;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Dimension;
import java.util.Random;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;


public class Battleship{
	World world;
	public static final int WIDTH = 1024;
  public static final int HEIGHT = 768;
  public static final int FPS = 60;

	public static void main(String[] args){
		world = new World(WIDTH, HEIGHT);
	}
} //Battleship

class World{
    int height;
    int width;

		public Ship[] armada_A;
	 	public Ship[] armada_B;

    public World(int initWidth, int initHeight){
			width = initWidth;
			height = initHeight;
    }

    public void drawArmada(Graphics g){
			int row = 10;
			int column = 10;

			for (int r=0; r<row; r++){

			}
			for (int c=0; c<column; c++){

			}
			for (int i = 0; i < 5; i++){
			    armada_A[i].draw(g);
			}
    }

    public void updateArmada(double time){
			for (int i = 0; i < numSpheres; i++)
			    spheres[i].update(this, time);
		}
}
