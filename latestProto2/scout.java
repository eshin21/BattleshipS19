import java.util.Random;

public class scout extends Weapon{
	public boolean foundShip;

	public scout(){
		this.type = "Scout";
		this.damage = 0;
		foundShip = false;
	}

	public void foundShip(boolean flyover){
		if (flyover){
			foundShip = true;
		}
	}
	
}
