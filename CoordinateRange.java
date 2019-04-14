public class CoordinateRange{
	public Pair xcoord;
	public Pair ycoord;

	public CoordinateRange(Pair xcoord,Pair ycoord){
		this.xcoord = xcoord;
		this.ycoord = ycoord;
	}

	public CoordinateRange(Pair xcoord){
		this.xcoord = xcoord;
	}

	public void addY (Pair ycoord){
		this.ycoord = ycoord;
	}

	public String toString(){
		return xcoord.x + "," + xcoord.y + " ; " + ycoord.x + "," + ycoord.y;
	}

}
