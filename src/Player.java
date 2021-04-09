
public abstract class Player extends Mappable {
	public Player(int x, int y) {
		super(x, y);
		// TODO Auto-generated constructor stub
	}
	public void moveUp(){
		this.setY(this.getY()+1);
	}
	public void moveDown(){
		this.setY(this.getY()-1);
	}
	public void moveLeft(){
		this.setX(this.getX()-1);
	}
	public void moveRight(){
		this.setX(this.getX()+1);
	}
}
