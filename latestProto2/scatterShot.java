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

	public void adjustRange(xRange,yRange){
		this.xRange = xRange;
		this.yRange = yRange;

		this.randomizeShots();
	}

	private void randomizeShots(){
		for (int i=0; i<this.shots;i++){
			int randX = this.xRange.x + randm.nextInt(this.xRange.y - this.xRange.x + 1);
			int randY = this.yRange.x + randm.nextInt(this.yRange.y - this.yRange.x + 1);
			this.shotGrid[i] = new Pair(randX,randY);
		}
	}

}
