public class weaponButton extends Button{
	public Weapon weapon;

     public weaponButton(String type, int x, int y, int xdim, int ydim, Weapon weapon) {

         super(type, x, y, xdim, ydim);
				 this.weapon = weapon;

     }


    public weaponButton() {

         super("null", 1, 1, 1, 1);

    }

    public static weaponButton[][] makeWeaponButtons() {

        weaponButton[][] toReturn = new weaponButton[2][3];

            toReturn[0][0] = new weaponButton("Scout", 25, 600, 100, 30, new Scout());
            toReturn[0][1] = new weaponButton("Scattershot", 25, 635, 100, 30, new scatterShot());
            toReturn[0][2] = new weaponButton("Missile", 25, 670, 100, 30, new Missile());

						toReturn[1][0] = new weaponButton("Scout", 900, 600, 100, 30, new Scout());
            toReturn[1][1] = new weaponButton("Scattershot", 900, 635, 100, 30, new scatterShot());
            toReturn[1][2] = new weaponButton("Missile", 900, 670, 100, 30, new Missile());

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

	public String toString (){
		return this.type + "x:" + this.x + "," + this.xdim + "y:" + this.y + "," + this.ydim;
	}


}
