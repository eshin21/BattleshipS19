import java.util.Random;

public class scatterShot extends Weapon{
	public int shots;
	Random randm = new Random();

	public scatterShot(){
		this.type = "scatterShot";
		this.damage = 10;
		this.cooldown = 3;
		this.shots = randm.nextInt(6);
	}
}
