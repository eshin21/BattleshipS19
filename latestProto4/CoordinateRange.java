public class CoordinateRange{
    public Pair xrange;
    public Pair yrange;
    public boolean filled;

    public CoordinateRange(Pair xrange,Pair yrange){
        this.xrange = xrange;
        this.yrange = yrange;
        this.filled = false;
    }
    
    public String toString(){
        return xrange.x + "-" + xrange.y + " ; " + yrange.x + "-" + yrange.y;
    }

}