public class Pair{
    public double x;
    public double y;
    public Pair(double initX, double initY){
    	x = initX;
    	y = initY;
        }

    public Pair add(Pair toAdd){
    	return new Pair(x + toAdd.x, y + toAdd.y);

    }

    public void flipX(){
    	x = -x;
    }

    public void flipY(){
    	y = -y;
    }
    
    public String toString() {
    	
    	String out = "(" + this.x + ", " + this.y +")";
    	return out;
    }
    
    public boolean isWithin() {
    	
    	
    	return false;
    	
    	
    	
    }
    
    
}
