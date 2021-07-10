//note... no checks for out of bounds movement yet...

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
	public void moveDown(Map map) {
		map.removeMappable(x,y);
		setY(getY()-1);		
		map.addMappable(this);
		
	}

	@Override
	public void moveLeft(Map map) {
		map.removeMappable(x,y);
		setX(getX()-1);
		map.addMappable(this);
	}

	@Override
	public void moveRight(Map map) {
		map.removeMappable(x,y);
		setX(getX()+1);
		map.addMappable(this);
	}

	@Override
	public void moveUp(Map map) {
		map.removeMappable(x,y);
		setY(getY()+1);
		map.addMappable(this);
	}
	
	@Override
	public void onEvent(GameEvent e) {
		if(e.getGameValue() instanceof PacmanGame) { //PACMAN MOVEMENT
			PacmanGame game = (PacmanGame)e.getGameValue();
			World world = (World)game.getMap();
			if(e.getSource().equals("w")) {
				moveUp(world);
			}
			if(e.getSource().equals("a")) {
				moveLeft(world);
			}
			if(e.getSource().equals("s")) {
				moveDown(world);
			}
			if(e.getSource().equals("d")) {
				moveRight(world);
			}
		}
	}

}
