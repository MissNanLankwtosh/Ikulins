
public abstract class Player extends Mappable {
	public Player(int x, int y) {
		super(x, y);
		// TODO Auto-generated constructor stub
	}
	public abstract void moveUp();
	public abstract void moveDown();
	public abstract void moveLeft();
	public abstract void moveRight();
}
