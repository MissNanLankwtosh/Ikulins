
public class Pacman extends Player implements GameListener{
	private PacmanState state;
	public Pacman(int x, int y) {
		super(x, y);
		// TODO Auto-generated constructor stub
	}
	
	public Pacman(int x, int y, Direction direction, int numOfLives) {
		super(x,y,direction,numOfLives);
		setImage("PACMAN/pacmanimg.png");
		state = PacmanState.NORMAL;
	}

	@Override
	public void spawn(Map map) {
		updateLocation(map);
	}

	@Override
	public void onEvent(GameEvent e) {
		// TODO Auto-generated method stub

	}
	/**
	 * Allows pacman's state to be changed.
	 * @param state Pacman's new state
	 */
	public void setState(PacmanState state) {
		this.state = state;
	}

	/**
	 * @return Returns pacmans current state
	 */
	public PacmanState getState() {
		return state;
	}

}
