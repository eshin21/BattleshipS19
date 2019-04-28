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

		Button[] toReturn = new Button[6];

		toReturn[0] = new Button("Erase",  240, 600, 95, 50);
		toReturn[1] = new Button("Next Turn", 340, 600, 95, 50);
		toReturn[2] = new Button("Reset A", 440, 600, 95, 50);
		toReturn[3] = new Button("Reset B", 540, 600, 95, 50);
		toReturn[4] = new Button( "Rotate", 360, 660, 95, 50);
        toReturn[5] = new Button("F I R E !",540, 660, 95, 50);

		return toReturn;


	}

	public boolean inButton(Pair point) {

		int x = (int) point.x;
		int y = (int) point.y;

		if(x >= this.x && x <= this.x+this.xdim && y>=this.y && y <= this.y+this.ydim)
			return true;

		else
			return false;
	}

	
	
}
