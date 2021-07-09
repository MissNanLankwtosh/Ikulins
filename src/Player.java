
public abstract class Player extends Mappable {
	public Player(int x, int y) {
		super(x, y);
		// TODO Auto-generated constructor stub
	}
	public abstract void moveUp(Map map);
	public abstract void moveDown(Map map);
	public abstract void moveLeft(Map map);
	public abstract void moveRight(Map map);

}
