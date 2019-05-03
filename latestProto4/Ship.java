import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.LinkedList;
import java.awt.Graphics2D;
import java.util.Random;

/////////////////////////////
// Enumerates ship.
////////////////////////////

public class Ship{
    public String type;
    public Pair position; //locked position of anchor point from which we draw the ship's rectangle
    public int xdim; 
    public int ydim;
    public int health;
    public Color color;
    public String player; //denotes which player this ship belongs to, "A" or "B"
    
    public Ship(String type, Pair p, int xdim, int ydim, Color color, String player){
        this.type = type;
        this.position = p;
        this.xdim= xdim;
        this.ydim= ydim;
        this.color = color;
        this.player = player;
        }
    
    
    public void placeShipA (Graphics g, Ship s, Main m, Color c) {
        
        if(this.alreadyPlacedA()) {
            System.out.println("You've already placed this ship, sorry. Pick a new one.");
            return;
            
        }
      
        System.out.println("Placing ship A...");

        Pair corner = m.findPoint(m.point);
        Ship add = new Ship(this.type, corner, s.xdim, s.ydim, c, "A"); //make new ship based on this corner
        
        /////////////check if this ship overlaps anything and then verify that it's in bounds
        
            
            if(!add.isOOB(corner)) {
                    System.out.println("no issues detected. adding to armada A...");
                    g.setColor(Color.RED);
                    Main.getMyGame().armada_A.addLast(add); //add to arraylist of ships    
                    m.currentShip = add;
                    m.currentShipA = add;
                    g.fillRect((int)corner.x, (int)corner.y,xdim,ydim); // necessary--otherwise it won't draw until the next click

                }
        
            else if(add.isOOB(corner)) { 
                System.out.println("Issue detected. Resolving and adding to armada A...");
                g.setColor(Color.RED);
                Ship adj = add.measureOver(corner);
                Main.getMyGame().armada_A.addLast(adj);
                m.currentShip = adj;
                m.currentShipA = adj;
                g.fillRect((int)adj.position.x, (int)adj.position.y, adj.xdim, adj.ydim);

            }
        

    }



    public void placeShipB (Graphics g, Ship s, Main m, Color c) {


        
        if(this.alreadyPlacedB()) {
            System.out.println("You've already placed this ship, sorry. Pick a new one.");
            return;
            
        }
      
        System.out.println("Placing ship B...");

        Pair corner = m.findPoint(m.point);
        Ship add = new Ship(this.type, corner, s.xdim, s.ydim, c, "B"); //make new ship based on this corner
        
        /////////////check if this ship overlaps anything and then verify that it's in bounds
        
        
            
            if(!add.isOOB(corner)) {
                    System.out.println("no issues detected. adding to armada B...");
                    g.setColor(Color.GREEN);
                    Main.getMyGame().armada_B.addLast(add); //add to arraylist of ships    
                    m.currentShip = add;
                    m.currentShipB= add;
                    g.fillRect((int)corner.x, (int)corner.y,xdim,ydim); // necessary--otherwise it won't draw until the next click

                }
        
            else if(add.isOOB(corner)) { 
                System.out.println("Issue detected. Resolving and adding to armada A...");
                g.setColor(Color.GREEN);
                Ship adj = add.measureOver(corner);
                Main.getMyGame().armada_B.addLast(adj);
                m.currentShip = adj;
                m.currentShipB = adj;
                g.fillRect((int)adj.position.x, (int)adj.position.y, adj.xdim, adj.ydim);

            }
        
        
    }
        
      
    public static void placeCPU(Graphics g, Main m) {
        
      
        System.out.println("Placing CPU ships...don't peek!");
        Random rand = new Random();
        
        int xrand1 = rand.nextInt(450) + 520; //min must be 520, max must be 970
        int yrand1 = rand.nextInt(450) + 25; //min must be 25, max must be 475
        Pair point1 = new Pair(xrand1, yrand1);
        Pair corner1 = m.findPoint(point1);
        
        int xrand2 = rand.nextInt(450) + 520; //min must be 520, max must be 970
        int yrand2 = rand.nextInt(450) + 25; //min must be 25, max must be 475
        Pair point2 = new Pair(xrand2, yrand2);
        Pair corner2 = m.findPoint(point2);
        
        while(corner1.equals(corner2))
            corner2 = new Pair(rand.nextInt(450) + 520, rand.nextInt(450) + 25);
        
        Ship scout = new Ship("Scout", corner1, 3*45, 45, Color.cyan, "CPU");
        Ship frigate = new Ship("Frigate", corner2, 45, 4*45, Color.cyan, "CPU");

        Main.getMyGame().armada_CPU.add(scout);
        Main.getMyGame().armada_CPU.add(frigate);

        
    }

        
    public boolean isOOB(Pair p) {
    	   
        
        if(this.player.equals("A")) {
            if(p.y + this.ydim <= 475 && p.x + this.xdim <= 475 && p.y >=25 && p.x>=25) {       
                return false; 
            }
            else if(p.y < 25 || p.x  < 25)
                return true;
        }
        
        else if(this.player.equals("B")) {
            
            if(p.y + this.ydim <= 475 && p.x + this.xdim <= 975 && p.y >= 25 && p.x>=520) {
                return false;
            
            }
            
            else if(p.y<25 || p.x < 520)
                return true;
        }      
            
        return true;
        
    	
    }
        

    
    private Ship measureOver(Pair p) { //if Ship is OOB, remake Ship

        int excess  = 0;
        Ship toReturn = new Ship(this.type, p, this.xdim, this.ydim, this.color, this.player);
        
        if(p.y + this.ydim > 475) { //goes off the bottom
            
            excess = ((int)p.y+this.ydim) - 475;
            Pair adjPair = new Pair(p.x, p.y-excess);
            
           toReturn = new Ship(this.type, adjPair, this.xdim, this.ydim, this.color, this.player);
           
           System.out.println("The ship went off the bottom. I made a new ship at " + adjPair + " and recorrected this to shift up by " + excess/45 + " squares.");
            
        }

        if(this.type.equals("A") && !inGridA()){ //goes off the right PlayerA
            
            excess = ((int) p.x + this.xdim) - 475;
            
            Pair adjPair = new Pair(p.x-excess, p.y);
            
            toReturn = new Ship(this.type, adjPair, this.xdim, this.ydim, this.color, this.player);
            
            System.out.println("The ship went off the right in Player A's Grid. I recorrected this to shift left by " + excess/45 + " squares.");
            
           
        }
        
        if(this.type.equals("B") && !inGridB() /*p.x + this.xdim*45 > 975 && p.x > 520*/) {

//            excess = 975 - this.xdim;
            excess = ((int) p.x + this.xdim) - 970;
            Pair adjPair = new Pair(excess, p.y);
            
            toReturn = new Ship(this.type, adjPair, this.xdim, this.ydim, this.color, this.player);
            System.out.println("The ship went off the right in Player B's Grid. I recorrected this to shift left by " + excess/45 + " squares.");

            
        }
        
        return toReturn;
        
    }
    
    private boolean inGridA() {
        
        if(this.player.equals("A") && this.position.x + this.xdim <= 475 && this.position.x <= 475 && this.position.x >=25)
            return true;
        else if(this.player.equals("B") && this.position.x + this.xdim <= 970 && this.position.x <= 970 && this.position.x >=520)
            return true;
        
        return false;
        
    }
    
    private boolean inGridB() {
        
        if(!this.player.equals("B")) {
            System.out.println("whoops, this isn't the right grid to check");
        }
      
        else if(this.player.equals("B") && this.position.x + this.xdim <= 970 && this.position.x <= 970 && this.position.x >=520)
            return true;
        
        return false;
        
        
    }
    
    
    private boolean equals(Ship r) {
        
        
        if(this.position.x == r.position.x && this.position.y==r.position.y && this.xdim == r.xdim 
                && this.ydim == r.ydim && this.color == r.color && this.player == r.player && this.type == r.type) {
            
            return true;
        }
            
        return false;
        
        
    }
    
    public String toString() {
        
        String myPrint = ( this.type + " ship at " + this.position );
        return myPrint;
        
        
    }
    
   public Ship rotate(Main m) { //rotate clockwise 90 deg
        
        Pair adjPoint = new Pair(this.position.x - (this.ydim - 45) , this.position.y);
        
        Ship toReturn = new Ship(this.type, adjPoint, this.ydim,this.xdim, this.color, this.player);
            return toReturn;
            
            
    }
    
    
    private boolean isHoriz() {
        
        if(this.xdim > this.ydim) {
            return true;
        }
        else
            return false;
    
    }
    
    private ArrayList<Pair> givePoints(){ //give me the key corners comprising this ship
        
        ArrayList<Pair> corners = new ArrayList<Pair>();
        int slength=0;

        if(this.isHoriz()) {
            slength = this.xdim/45;
            for(int i = 0; i<slength; ++i) {
                Pair toAdd = new Pair(this.position.x + 45*i, this.position.y); 
                corners.add(toAdd);
                System.out.println(toAdd);
            }
        }

        else if(!this.isHoriz()) {
            slength = this.ydim/45;
            System.out.println("Vertical length " + slength + " ####### "  + this.type + ":");
            for(int i = 0; i<slength; ++i) {
                Pair toAdd = new Pair(this.position.x, this.position.y+ 45*i); 
                corners.add(toAdd);
                System.out.println(toAdd);
            }

        }
            
        return corners;
        
    }
    

    private boolean isOverlap(Ship s) {
        
        System.out.println("ISOVERLAP: I'm checking overlaps");
        
        ArrayList<Pair> s_coord = s.givePoints();
        ArrayList<Pair> this_coord = this.givePoints();

            
        //compare each Pair in one ship to all the other Pairs of the other ship.
            for(int i = 0; i< this_coord.size(); ++i) {
                Pair compare = this_coord.get(i);
                for(int j= 0; j<s_coord.size(); ++j) {
                    if(compare.equals(s_coord.get(j)))
                        return true;
                }
                    
            }
            
            return false;
            
    }
    
    public boolean checkArmadaOverlap() {
       
        System.out.println("OVERLAP METHOD RUNNING...");
        
        if(this.player.equals("A")) {
            System.out.println("Checking overlap in Armada A...");
            for(Ship a : Main.getMyGame().armada_A) {
                if(!this.type.equals(a.type) && this.isOverlap(a)) {
                    System.out.println("****A: Sorry, you can't place a ship here due to overlap. Try again");
                    return true;
                }
            }
        }
        
        else if (this.player=="B") {
                for(Ship b: Main.getMyGame().armada_B) {
                    if(!this.type.equals(b.type) && this.isOverlap(b)) {
                        System.out.println("***B: Sorry, you can't place a ship here due to overlap. Try again");
                        return true;
                    }
                }
            }
        
        return false;
        
    }
    
    private boolean alreadyPlacedA() {
        
        for(Ship s:Main.getMyGame().armada_A) {
            if(this.type.equals(s.type)) {
                return true;
            }
        }
        
        return false;
        
        
    }

    private boolean alreadyPlacedB() {
        
        for(Ship s:Main.getMyGame().armada_B) {
            if(this.type.equals(s.type)) {
                return true;
            }
        }
        
        return false;
        
        
    }
    
    public void shiftDown() {
        
        Pair shift = new Pair(this.position.x, this.position.y + 45);
        Ship shifted = new Ship(this.type, shift, this.xdim,this.ydim, this.color, this.player);

        if(shifted.isOOB(shifted.position)) {
            System.out.println("You can't shift down anymore");
            return;
            
        }
        
        if(this.player.equals("A") ) {
            
            Main.getMyGame().armada_A.removeLast();
            Main.getMyGame().armada_A.addLast(shifted);
            
        }
        
        else if(this.player.equals("B")) {
            
            Main.getMyGame().armada_B.removeLast();
            Main.getMyGame().armada_B.addLast(shifted);
            
        }
        
        
    }

public void shiftUp() {
        
        Pair shift = new Pair(this.position.x, this.position.y - 45);
        Ship shifted = new Ship(this.type, shift, this.xdim,this.ydim, this.color, this.player);

        if(shifted.isOOB(shifted.position)) {
            System.out.println("You can't shift up anymore");
            return;
            
        }
        
        
        if(this.player.equals("A") ) {
            
            Main.getMyGame().armada_A.removeLast();
            Main.getMyGame().armada_A.addLast(shifted);
            
        }
        
        else if(this.player.equals("B") ) {
            
            Main.getMyGame().armada_B.removeLast();
            Main.getMyGame().armada_B.addLast(shifted);
            
        }
        
        
    }

public void shiftLeft() {
    
    Pair shift = new Pair(this.position.x-45, this.position.y);
    Ship shifted = new Ship(this.type, shift, this.xdim,this.ydim, this.color, this.player);

    if(shifted.isOOB(shifted.position)) {
        System.out.println("You can't shift left anymore");
        return;
        
    }
    
    if(this.player.equals("A")) {
        
        Main.getMyGame().armada_A.removeLast();
        Main.getMyGame().armada_A.addLast(shifted);
        
    }
    
    else if(this.player.equals("B")) {
        
        Main.getMyGame().armada_B.removeLast();
        Main.getMyGame().armada_B.addLast(shifted);
        
    }
    
    
    }

public void shiftRight() {
    
    Pair shift = new Pair(this.position.x+45, this.position.y);
    Ship shifted = new Ship(this.type, shift, this.xdim,this.ydim, this.color, this.player);

    if(shifted.isOOB(shifted.position)) {
        System.out.println("You can't shift right anymore");
        return;
        
    }
    
    if(this.player.equals("A")) {
        
        Main.getMyGame().armada_A.removeLast();
        Main.getMyGame().armada_A.addLast(shifted);
        
    }
    
    else if(this.player.equals("B")) {
        
        Main.getMyGame().armada_B.removeLast();
        Main.getMyGame().armada_B.addLast(shifted);
        
    }
    
    
    
    }

    
    
}

