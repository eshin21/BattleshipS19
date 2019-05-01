import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.LinkedList;
import java.awt.Graphics2D;

/////////////////////////////
// Enumerates ship.
////////////////////////////

public class Ship{
    String type;
    Pair position; //locked position of anchor point from which we draw the ship's rectangle
    int xdim; 
    int ydim;
    int health;
    Color color;
    String player; //denotes which player this ship belongs to, "A" or "B"
    
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
            g.setColor(Color.YELLOW);
            Graphics2D g2d =  (Graphics2D) g;
            g2d.drawString("!!! You've already placed this Ship. Select a different one.", 300, 17);
            return;
            
        }
      
        System.out.println("Placing ship A...");

        Pair corner = m.findPoint(m.point);
        Ship add = new Ship(this.type, corner, s.xdim, s.ydim, c, "A"); //make new ship based on this corner
        
        /////////////check if this ship overlaps anything and then verify that it's in bounds
        
        
        if(!add.checkArmadaOverlap()){
            
            if( /*!add.checkArmadaOverlap() &&*/ !add.isOOB(corner)) {
                    System.out.println("no issues detected. adding to armada A...");
                    g.setColor(Color.RED);
                    Main.getMyGame().armada_A.addLast(add); //add to arraylist of ships    
                    m.currentShip = add;
                    m.currentShipA = add;
                    m.indexA++;
                    g.fillRect((int)corner.x, (int)corner.y,xdim,ydim); // necessary--otherwise it won't draw until the next click

                }
        
            else if(add.isOOB(corner) && !add.checkEdge(corner, m).checkArmadaOverlap()) { 
                System.out.println("Issue detected. Resolving and adding to armada A...");
                g.setColor(Color.RED);
                Ship adj = add.measureOver(corner);
                Main.getMyGame().armada_A.addLast(adj);
                m.currentShip = adj;
                m.currentShipA = adj;
                m.indexA++;
                g.fillRect((int)adj.position.x, (int)adj.position.y, adj.xdim, adj.ydim);

            }
        
        
        }
        
        else {
            System.out.println("Sorry, Player A, you can't place a ship here. Try again.");
        }
        
            
        for(Ship a : Main.getMyGame().armada_A) {
            
            System.out.println("***" + a);
            
        }
            
        
        
    }

    public void placeShipB (Graphics g, Ship s, Main m, Color c) {


        
        if(this.alreadyPlacedB()) {
            g.setColor(Color.YELLOW);
            Graphics2D g2d =  (Graphics2D) g;
            g2d.drawString("!!! You've already placed this Ship. Select a different one.", 300, 17);
            return;
            
        }
      
        System.out.println("Placing ship B...");

        Pair corner = m.findPoint(m.point);
        Ship add = new Ship(this.type, corner, s.xdim, s.ydim, c, "B"); //make new ship based on this corner
        
        /////////////check if this ship overlaps anything and then verify that it's in bounds
        
        
        if(!add.checkArmadaOverlap()){
            
            if(!add.isOOB(corner)) {
                    System.out.println("no issues detected. adding to armada B...");
                    g.setColor(Color.GREEN);
                    Main.getMyGame().armada_B.addLast(add); //add to arraylist of ships    
                    m.currentShip = add;
                    m.currentShipB= add;
                    m.indexB++;
                    g.fillRect((int)corner.x, (int)corner.y,xdim,ydim); // necessary--otherwise it won't draw until the next click

                }
        
            else if(add.isOOB(corner) && !add.checkEdge(corner, m).checkArmadaOverlap()) { 
                System.out.println("Issue detected. Resolving and adding to armada A...");
                g.setColor(Color.GREEN);
                Ship adj = add.measureOver(corner);
                Main.getMyGame().armada_B.addLast(adj);
                m.currentShip = adj;
                m.currentShipB = adj;
                m.indexB++;
                g.fillRect((int)adj.position.x, (int)adj.position.y, adj.xdim, adj.ydim);

            }
        
        
        }
        
        else {
            System.out.println("Sorry, Player B, you can't place a ship here. Try again.");
        }
        
            
        for(Ship b : Main.getMyGame().armada_B) {
            
            System.out.println("B***" + b);
            
        }
            
        
    }

        
    public boolean isOOB(Pair p) {
    	   
        
        if(this.player.equals("A")) {
            if(p.y + this.ydim <= 475 && p.x + this.xdim <= 475 && p.y >=25 && p.x>=25) {       
                return false; 
            }
        }
        
        else if(this.player.equals("B")) {
            
            if(p.y + this.ydim <= 475 && p.x + this.xdim <= 975 && p.y >= 25 && p.x>=520) {
                return false;
                 
            
            }
        
        }      
            
        return true;
        
    	
    }
        

    public Ship checkEdge(Pair p, Main m) { 
        
        if(p.y + this.ydim <= 475 && p.x + this.xdim <= 475 && p.x - this.xdim>=25-45 ||  p.y + this.ydim <= 475 && p.x + this.xdim <= 975 && p.x - this.xdim >=520-45) {       
           return this; 
        }
        
        
        else{
            return measureOver(p);
        }
        
        
        
    }
    
    public Ship measureOver(Pair p) { //if Ship is OOB, remake Ship

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
    
    public boolean inGridA() {
        
        if(this.player.equals("A") && this.position.x + this.xdim <= 475 && this.position.x <= 475 && this.position.x >=25)
            return true;
        else if(this.player.equals("B") && this.position.x + this.xdim <= 970 && this.position.x <= 970 && this.position.x >=520)
            return true;
        
        return false;
        
    }
    
    public boolean inGridB() {
        
        if(!this.player.equals("B")) {
            System.out.println("whoops, this isn't the right grid to check");
        }
      
        else if(this.player.equals("B") && this.position.x + this.xdim <= 970 && this.position.x <= 970 && this.position.x >=520)
            return true;
        
        return false;
        
        
    }
    
    
    public boolean equals(Ship r) {
        
        
        if(this.position.x == r.position.x && this.position.y==r.position.y && this.xdim == r.xdim 
                && this.ydim == r.ydim && this.color == r.color && this.player == r.player) {
            
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
        if(!toReturn.isOOB(adjPoint) && !toReturn.checkArmadaOverlap()) {
            System.out.println("*****I'm rotating this to a Ship at " + toReturn.position + " with dimensions " + toReturn.xdim + " by " + toReturn.ydim );
            return toReturn;
        }
        else if(!toReturn.isOOB(adjPoint) && toReturn.checkArmadaOverlap()){
            System.out.println("Sorry, you can't rotate this due to overlap. Try erasing and placing again.");
            
        }
        else if (toReturn.isOOB(adjPoint) && !toReturn.checkArmadaOverlap()) {
            toReturn = toReturn.checkEdge(adjPoint, m);
            return toReturn;
        }
        
            System.out.println("Sorry, you can't rotate this ship. Try erasing and placing again.");
            return toReturn;
            
            
    }
    
    public boolean needsAdj(Main m) {

        System.out.println("The needsAdj method ran");
        //if it's in bounds 
        if(this.position.y + this.ydim <= 475 && this.position.x + this.xdim <= 475 && this.position.x - this.xdim>=25-45 ||  this.position.y + this.ydim <= 475 && this.position.x + this.xdim <= 975 && this.position.x - this.xdim >=520-45 ) {
            return false;
                    
        }
       
        return true;
        
    }
    
    
    public boolean isHoriz() {
        
        if(this.xdim > this.ydim) {
            return true;
        }
        else
            return false;
    
    }
    
    public ArrayList<Pair> givePoints(){ //give me the key corners comprising this ship
        
       
        ArrayList<Pair> corners = new ArrayList<Pair>();

        int slength=0;

        if(this.isHoriz()) {
            
            slength = this.xdim/45;
          
            for(int i = 0; i<slength; ++i) {
                Pair toAdd = new Pair(this.position.x + 45*i, this.position.y); 
                corners.add(toAdd);
                System.out.println("Ship comprised of" + toAdd);
            }
        }

        else if(!this.isHoriz()) {
            slength = this.ydim/45;
            for(int i = 0; i<slength; ++i) {
                Pair toAdd = new Pair(this.position.x, this.position.y+ 45*i); 
                corners.add(toAdd);
                System.out.println("Ship comprised of" + toAdd);
            }

        }
            
        return corners;
        
    }
    

    public boolean isOverlap(Ship s) {
        
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
        
        if(this.player.equals("A")) {
            System.out.println("Checking overlap in Armada A...");
            for(Ship a : Main.getMyGame().armada_A) {
                if(this.isOverlap(a)) {
                    System.out.println("****A: Sorry, you can't place a ship here due to overlap. Try again");
                    return true;
                }
            }
        }
        
        else if (this.player=="B") {
                for(Ship b: Main.getMyGame().armada_B) {
                    if(this.isOverlap(b)) {
                        System.out.println("***B: Sorry, you can't place a ship here due to overlap. Try again");
                        return true;
                    }
                }
            }
        
        return false;
        
    }
    
    public boolean alreadyPlacedA() {
        
        for(Ship s:Main.getMyGame().armada_A) {
            if(this.type.equals(s.type)) {
                return true;
            }
        }
        
        return false;
        
        
    }

    public boolean alreadyPlacedB() {
        
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
        
        if(this.player.equals("A") && !shifted.checkArmadaOverlap()) {
            
            Main.getMyGame().armada_A.removeLast();
            Main.getMyGame().armada_A.addLast(shifted);
            
        }
        
        else if(this.player.equals("B") && !shifted.checkArmadaOverlap()) {
            
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
        
        if(this.player.equals("A") && !shifted.checkArmadaOverlap()) {
            
            Main.getMyGame().armada_A.removeLast();
            Main.getMyGame().armada_A.addLast(shifted);
            
        }
        
        else if(this.player.equals("B") && !shifted.checkArmadaOverlap()) {
            
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
    
    if(this.player.equals("A") && !shifted.checkArmadaOverlap()) {
        
        Main.getMyGame().armada_A.removeLast();
        Main.getMyGame().armada_A.addLast(shifted);
        
    }
    
    else if(this.player.equals("B") && !shifted.checkArmadaOverlap()) {
        
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
    
    if(this.player.equals("A") && !shifted.checkArmadaOverlap()) {
        
        Main.getMyGame().armada_A.removeLast();
        Main.getMyGame().armada_A.addLast(shifted);
        
    }
    
    else if(this.player.equals("B") && !shifted.checkArmadaOverlap()) {
        
        Main.getMyGame().armada_B.removeLast();
        Main.getMyGame().armada_B.addLast(shifted);
        
    }
    
    
    }
    
}

