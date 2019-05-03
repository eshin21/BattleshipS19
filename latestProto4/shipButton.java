public class shipButton extends Button{

     public int length;  //length of SHIP corresponding to button

     public shipButton(String type, int x, int y, int xdim, int ydim, int length) {

         super(type, x, y, xdim, ydim);
         this.length = length;


     }


     public shipButton() {

         super("null", 1, 1, 1, 1);
         this.length = 2;


     }

     public static shipButton[][] makeShipButtons() {

            shipButton[][] toReturn = new shipButton[2][5];

                toReturn[0][0] = new shipButton("Light Cruiser", 25, 550, 60, 30, 2);
                toReturn[0][1] = new shipButton("Scout", 115, 550, 60, 30, 3);
                toReturn[0][2] = new shipButton("Frigate", 200, 550, 60, 30, 4);
                toReturn[0][3] = new shipButton("Destroyer", 300, 550, 60, 30, 5);

                toReturn[0][4] = new shipButton("Carrier", 400, 550, 60, 30, 6);


                toReturn[1][0] = new shipButton("Light Cruiser", 25+495, 550, 60, 30, 2); //for x-pos, just add 495 to the A buttons
                toReturn[1][1] = new shipButton("Scout", 115+495, 550, 60, 30, 3);
                toReturn[1][2] = new shipButton("Frigate", 200+495, 550, 60, 30, 4);
                toReturn[1][3] = new shipButton("Destroyer", 300+495, 550, 60, 30,5);

                toReturn[1][4] = new shipButton("Carrier", 400+495, 550, 60, 30,6);


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
