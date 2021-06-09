
public class Pacman extends Player {
	private int numLives, mode; //wtf is mode?
	
	public Pacman(int x, int y, int lives) {
		super(x, y);
		numLives = lives;
		//need to set mode... when i know what it is...
	}

	public int getLives() {
		return numLives;
	}
	
	public void die() {
		numLives--;
	}
	
	public void spawn(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	

	@Override
	public void moveDown() {
		setY(getY()-1);		
	}

	@Override
	public void moveLeft() {
		setX(getX()-1);
	}

	@Override
	public void moveRight() {
		setX(getX()+1);
	}

	@Override
	public void moveUp() {
		setY(getY()+1);
	}
	
	@Override
	public void onEvent(GameEvent e) {
		// TODO Auto-generated method stub
	}

}
