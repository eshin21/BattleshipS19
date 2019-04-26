import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

/////////////////////////////
// Enumerates ship.
////////////////////////////

public class Ship{
	Pair position; //locked position of anchor rectangle
	int length;
	int health;
	Color color;
	public ArrayList<Rectangle> body = new ArrayList<Rectangle>();
	int player; //denotes which player this ship belongs to
	int index; //this ship's location in the armada ArrayList

	public Ship(Pair p, int length, int health, Color color, int player){

		this.position = p;
		this.length = length;
		this.health = health;
		this.color = color;
		this.player = player;
		
		for(int i = 0; i<length; ++i) {
			
			this.body.add(new Rectangle((int)p.x, (int)p.y+45*i, 45, 45, color));
			
		}
	}

	public void changePosition(Pair p) {
		this.position.x = p.x;
		this.position.y = p.y;

	}

	
    public static void buildArmada(Main m, int size, Color c){
    	
    	

    }
    	
    public void placeShip(Main m, Graphics g, Color c) {
        
        g.setColor(c);
        Pair corner = m.findPoint(m.point);
        System.out.println("The key corner is " + corner);
        Ship ship = m.currentShip;

        if(corner.x <= 475 && corner.y <=475) { ///if click was in Grid A, call findPointA
            placeShipA(g, ship, m, c);
        }
        else if(corner.x>=520 && corner.y<=475) { ///if click was in Grid B, call findPointB
            placeShipB(g, ship, m, c);
        }
        
        
       
        
           }
    	
    public boolean checkEdge (Ship s) { //ships keep track of whether they're out of bounds
        
        
        
        
        return true;
    }
    

    public void placeShipA (Graphics g, Ship s, Main m, Color c) {
        
        Pair corner = m.findPoint(m.point);
Rectangle add = new Rectangle((int)corner.x, (int)corner.y,45, m.currentShip.length*45, c); //make new rectangle based on this corner
        
        if(add.checkEdge(corner).equals(add)) {                 ///EDGE AWARENESS: ADD IT ONLY IF THE COORDINATE & LENGTH ARE COMPATIBLE
            g.setColor(c);
            m.rects.add(add); //add to arraylist of rectangles    
            m.index++;
            g.fillRect((int)corner.x, (int)corner.y,45,m.currentShip.length*45); // necessary--otherwise it won't draw until the next click

        }
        else { 
            g.setColor(c);
            Rectangle adj = add.checkEdge(corner);
            m.rects.add(adj);
            m.index++;
            g.fillRect(adj.x, adj.y, adj.width, adj.height);
            
        }
            
        
        
        
        
 
    
    }
    
    public void placeShipB (Graphics g, Ship s, Main m, Color c) {

        Pair corner = m.findPoint(m.point);
Rectangle add = new Rectangle((int)corner.x, (int)corner.y,45, m.currentShip.length*45, c); //make new rectangle based on this corner
Rectangle adj = add.checkEdge(corner);
        
        if(add.equals(adj)) {                 ///EDGE AWARENESS: ADD IT ONLY IF THE COORDINATE & LENGTH ARE COMPATIBLE
            g.setColor(c);
            m.rects.add(add); //add to arraylist of rectangles   
            m.index++;
            g.fillRect((int)corner.x, (int)corner.y,45,m.currentShip.length*45); // necessary--otherwise it won't draw until the next click

        }
        
        else {
            g.setColor(c);
            m.rects.add(adj);
            m.index++;
            g.fillRect(adj.x, adj.y, adj.width, adj.height);
            
        }
       
        
        
    }


}
