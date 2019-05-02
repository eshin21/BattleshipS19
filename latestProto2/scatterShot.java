import java.util.Random;

public class scatterShot extends Weapon{
	public int shots;
	public Pair xRange;
	public Pair yRange;
	public Pair[] shotGrid;
	Random randm = new Random();

	public scatterShot(){
		this.type = "scatterShot";
		this.damage = 10;
		this.shots = randm.nextInt(6);
		shotGrid[] = new Pair[this.shots];
	}

	public void adjustRange(xRange,yRange){
		this.xRange = xRange;
		this.yRange = yRange;
	}

	private void randomizeShots(){
		for (int i=0; i<this.shots;i++){
			int randX = this.xRange.x + randm.nextInt(this.xRange.y - this.xRange.x + 1);
			int randY = this.yRange.x + randm.nextInt(this.yRange.y - this.yRange.x + 1);
			this.shotGrid[i] = new Pair(randX,randY);
		}
	}

}
