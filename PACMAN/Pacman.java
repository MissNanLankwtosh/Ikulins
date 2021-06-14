
public class Pacman extends Player {
	private int numLives, mode;
	
	public Pacman(int x, int y, int lives) {
		super(x, y);
		numLives = lives;
		mode = 0;
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
		if(e.getGameValue() instanceof Pacman) { //PACMAN MOVEMENT
			if(e.getSource().equals("pUP")) moveUp();
			if(e.getSource().equals("pLEFT")) moveLeft();
			if(e.getSource().equals("pDOWN")) moveDown();
			if(e.getSource().equals("pRIGHT")) moveRight(); 
		}
	}

}
