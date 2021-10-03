
public class Ghost extends Player implements GameListener {

	public Ghost(int x, int y) {
		super(x, y);
		// TODO Auto-generated constructor stub
	}
	
	public Ghost(int x, int y, Direction direction, int numOfLives) {
		super(x,y,direction,numOfLives);
		setImage("PACMAN/ghostimg" + numOfLives + ".png");
	}

	@Override
	public void spawn(Map map) {
		// TODO Auto-generated method stub
	}
	
	@Override
	public void die() {
		//don't decrease numOfLives when a ghost dies.
	}

	@Override
	public void onEvent(GameEvent e) {
		// TODO Auto-generated method stub

	}

}
