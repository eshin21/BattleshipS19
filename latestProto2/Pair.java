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


		///FIND ANCHOR POINT BASED ON USER CLICK IN GRID A

		public Pair findPointA (CoordinateRange[][]GridA) {


			double minX = GridA[0][0].xrange.x; ///INITIALIZATION
			double maxX = GridA[0][0].xrange.y;
			double minY = GridA[0][0].yrange.x;
			double maxY = GridA[0][0].yrange.y;

			Pair corner = new Pair(0,0);

				for(int i = 0; i<10; ++i) {

					for(int j = 0; j<10; ++j) {
						minX = GridA[i][j].xrange.x;
						maxX = GridA[i][j].xrange.y;
						minY = GridA[i][j].yrange.x;
						maxY = GridA[i][j].yrange.y;
						if(this.x >= minX && this.x <= maxX && this.y >= minY && this.y <= maxY )
							corner = new Pair(minX, minY);

					}
				}

				return corner;

		}

		///FIND ANCHOR POINT BASED ON USER CLICK IN GRID B

		public Pair findPointB (CoordinateRange[][]GridB) {

			double minX = GridB[0][0].xrange.x;
			double maxX = GridB[0][0].xrange.y;
			double minY = GridB[0][0].yrange.x;
			double maxY = GridB[0][0].yrange.y;

			Pair corner = new Pair(0,0);

				for(int i = 0; i<10; ++i) {

					for(int j = 0; j<10; ++j) {

						minX = GridB[i][j].xrange.x;
						maxX = GridB[i][j].xrange.y;
						minY = GridB[i][j].yrange.x;
						maxY = GridB[i][j].yrange.y;
						if(this.x >= minX && this.x <= maxX && this.y >= minY && this.y <= maxY )
							corner = new Pair(minX, minY);

					}

				}

				return corner;

		}

		public String inGrid(Pair p) {
		    
		    return "A";
		    
		}

}