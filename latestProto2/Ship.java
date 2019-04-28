import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.LinkedList;

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
    
        
    public void placeShip(Main m, Graphics g, Color c) {
        Pair corner = m.findPoint(m.point);
        System.out.println("The key corner is " + corner);
        Ship shipA = m.currentShipA;
        Ship shipB = m.currentShipB;
        if(corner.x <= 475 && corner.y <=475) { ///if click was in Grid A, call placeShipA
            placeShipA(g, shipA, m, c);
        }
        else if(corner.x>=520 && corner.y<=475) { ///if click was in Grid B, call placeShipB
            placeShipB(g, shipB, m, c);
        }
        
    }
        

    public void placeShipA (Graphics g, Ship s, Main m, Color c) {
        
        Pair corner = m.findPoint(m.point);
        Ship add = new Ship(this.type, corner, s.xdim, s.ydim, c, s.player); //make new ship based on this corner
        
        /////////////check if this ship overlaps anything and then verify that it's in bounds
        
        if(!s.checkArmadaOverlap() && add.checkEdge(corner, m).equals(add)) {
                g.setColor(c);
                Main.getMyGame().armada_A.add(add); //add to arraylist of ships    
                m.indexA++;
                g.fillRect((int)corner.x, (int)corner.y,xdim,ydim); // necessary--otherwise it won't draw until the next click

            }
        
        else if(!add.checkEdge(corner, m).equals(add) && !add.checkEdge(corner, m).checkArmadaOverlap()) { 
            
                g.setColor(c);
                Ship adj = add.checkEdge(corner, m);
                Main.getMyGame().armada_A.add(adj);
                m.indexA++;
                g.fillRect((int)adj.position.x, (int)adj.position.y, adj.xdim, adj.ydim);

       }
            
        else {
            System.out.println("******Sorry, Player A, you can't place a ship here. Try again.");
        }
            
        
        
    }

        
    
    
    public void placeShipB (Graphics g, Ship s, Main m, Color c) {

        Pair corner = m.findPoint(m.point);
        Ship add = new Ship(this.type, corner, s.xdim, s.ydim, c, s.player); //make new ship based on this corner
        
        /////////////check if this ship overlaps anything and then verify that it's in bounds
        
        if(!s.checkArmadaOverlap() && add.checkEdge(corner, m).equals(add)) {
                g.setColor(Color.GREEN);
                Main.getMyGame().armada_B.add(add); //add to arraylist of ships    
                m.indexB++;
                g.fillRect((int)corner.x, (int)corner.y,xdim,ydim); // necessary--otherwise it won't draw until the next click

            }
        
        else if(!add.checkEdge(corner, m).equals(add) && !add.checkEdge(corner, m).checkArmadaOverlap()) { 
            
                g.setColor(Color.GREEN);
                Ship adj = add.checkEdge(corner, m);
                Main.getMyGame().armada_B.add(adj);
                m.indexB++;
                g.fillRect((int)adj.position.x, (int)adj.position.y, adj.xdim, adj.ydim);

       }
            
        else {
            System.out.println("Sorry, Player B, you can't place a ship here. Try again.");
        }
            
        
        
    }

        

        

    public Ship checkEdge(Pair p, Main m) { //check if OOB
        
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
           
           System.out.println("The ship went off the bottom. I recorrected this to shift up by " + excess/45 + " squares.");
            
        }

        if(p.x + this.xdim > 475 && p.x < 475){ //goes off the right PlayerA
            
            excess = ((int) p.x + this.xdim) - 475;
            
            Pair adjPair = new Pair(p.x-excess, p.y);
            
            toReturn = new Ship(this.type, adjPair, this.xdim, this.ydim, this.color, this.player);
            
            System.out.println("The ship went off the right in Player A's Grid. I recorrected this to shift left by " + excess/45 + " squares.");
            
           
        }
        
        if(p.x + this.xdim*45 > 975 && p.x > 520) {
            

//            excess = 975 - this.xdim;
            excess = ((int) p.x + this.xdim) - 975;
            Pair adjPair = new Pair(excess, p.y);
            
            toReturn = new Ship(this.type, adjPair, this.xdim, this.ydim, this.color, this.player);
            System.out.println("The ship went off the right in Player B's Grid. I recorrected this to shift left by " + excess/45 + " squares.");

            
        }
        
        return toReturn;
        
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
        
        System.out.println("*****I'm rotating this to a Ship at " + toReturn.position);
        
        Pair anchor = new Pair(this.position.x, this.position.y);
        
        if(!this.needsAdj(m))
            return toReturn;
        else {
            
            toReturn = this.checkEdge(anchor, m);
            return toReturn;
        }
          
        
        
    }
    
    public boolean needsAdj(Main m) {

        
        //if it's in bounds and also if there's no overlap
        if(this.position.y + this.ydim <= 475 && this.position.x + this.xdim <= 475 && this.position.x - this.xdim>=25-45 && !this.checkArmadaOverlap() ||  this.position.y + this.ydim <= 475 && this.position.x + this.xdim <= 975 && this.position.x - this.xdim >=520-45 && !this.checkArmadaOverlap()) {
            return false;
                    
        }
       
        return true;
        
    }
    
    public boolean isOverlap(Ship s) {
        
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
            }
        }

        else if(!this.isHoriz()) {
            slength = this.ydim/45;
            for(int i = 0; i<slength; ++i) {
                Pair toAdd = new Pair(this.position.x, this.position.y+ 45*i); 
                corners.add(toAdd);
            }

        }
            
        return corners;
        
    }
    
    
    public boolean checkArmadaOverlap() {
        
        
        if(this.player=="A") {
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

}
