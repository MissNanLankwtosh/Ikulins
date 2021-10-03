
public class Pacman extends Player implements GameListener{

	public Pacman(int x, int y) {
		super(x, y);
		// TODO Auto-generated constructor stub
	}
	
	public Pacman(int x, int y, Direction direction, int numOfLives) {
		super(x,y,direction,numOfLives);
		setImage("PACMAN/pacmanimg.png");		
	}

	@Override
	public void spawn(Map map) {
		updateLocation(map);
	}

	@Override
	public void onEvent(GameEvent e) {
		// TODO Auto-generated method stub

	}

}
