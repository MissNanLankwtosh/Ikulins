import java.util.ArrayList;


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
		if(e.getSource().equals("movement") && e.getGameValue() instanceof PacmanGame) { //if movement has occured
			PacmanGame tempGame = (PacmanGame)e.getGameValue(); //create a temp variable of the game
			
			ArrayList<Mappable> array = tempGame.getMap().getMappable(getX(),getY());
			for(int i=0; i<array.size(); i++) { //go through array to check if pacman is on something
				if(array.get(i) instanceof LittlePillItem) { //if a LittlePillItem
					tempGame.getMap().removeMappable(getX(),getY(),i);
					tempGame.setScore(tempGame.getScore()+1); //increment score by 1
					tempGame.setPillsLeft(tempGame.getPillsLeft()-1); //decrement pillsLeft by 1
				}
			}
		}

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
