public class Button{
	
	public String type;
	public int x; //x-pos of button
	public int y; //y-pos of button
	public int xdim; //size of button
	public int ydim; //size of button
	
	public Button(String type, int x, int y, int xdim, int ydim) {
		
		this.type = type;
		this.x = x;
		this.y = y;
		this.xdim = xdim;
		this.ydim= ydim;
		
	}
	
	
	public static Button[] makePlayButtons() {
		
		Button[] toReturn = new Button[2];
		
		toReturn[0] = new Button("F I R E !", 320, 650, 80, 50);
		toReturn[1] = new Button("Next turn", 545, 650, 80, 50);
		
		
		return toReturn;
		
		
	}
	
	public boolean inButton(Pair point) {
		
		 int x = (int) point.x;
		int y = (int) point.y;
			
		if(x >= this.x && x <= this.x+this.xdim && y>=this.y && y <= this.y+this.ydim)
				return true;
			
			else return false;
			
		
	}
	
	
}


