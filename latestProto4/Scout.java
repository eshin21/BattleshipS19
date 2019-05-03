import java.util.Random;

public class Scout extends Weapon{
	public boolean foundShip;

	public Scout(){
		this.type = "Scout";
		this.damage = 0;
		this.cooldown = 4;
		foundShip = false;
	}

	public void foundShip(boolean flyover){
		if (flyover){
			foundShip = true;
		}
	}

}
