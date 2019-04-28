public class CoordinateRange{
	public Pair xrange;
	public Pair yrange;
	public boolean filled;

	public CoordinateRange(Pair xrange,Pair yrange){
		this.xrange = xrange;
		this.yrange = yrange;
		this.filled = false;
	}
	

    public CoordinateRange(Pair xrange,Pair yrange, boolean filled){
        this.xrange = xrange;
        this.yrange = yrange;
        this.filled = filled;
    }

	public void addX (Pair xrange){
		this.xrange= xrange;
	}

	public void addY (Pair yrange){
		this.yrange= yrange;
	}

	public String toString(){
		return xrange.x + "-" + xrange.y + " ; " + yrange.x + "-" + yrange.y;
	}

}
