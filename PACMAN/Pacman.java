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
			
			ArrayList<Mappable> mappables = tempGame.getMap().getMappable(getX(),getY());
			
			for(int i=0; i<mappables.size(); i++) { //go through array to check if pacman is on a LittlePillItem
				if(mappables.get(i) instanceof LittlePillItem) {
					tempGame.getMap().removeMappable(getX(),getY(),i);
					tempGame.setScore(tempGame.getScore()+1); //increment score by 1
					tempGame.setPillsLeft(tempGame.getPillsLeft()-1); //decrement pillsLeft by 1
				}
			}
			for(int i=0; i<mappables.size(); i++) { //go through array to check if pacman is on a Ghost
				if(mappables.get(i) instanceof Ghost) {
					if(state == PacmanState.NORMAL) {
						if(getNumOflives() == 0) {
							tempGame.end();
						}
						else { //has lives left
							
						}
						die(); //decrement lives left
						tempGame.getMap().removeMappable(getX(),getY(),i); //remove pacman
						setX(9); 
						setY(9);
						spawn(tempGame.getMap()); //respawn pacman
						break; //break in case you were on 2 ghosts... dont want to die 2 times (errors)
					}
					else { //BEASTMODE
						
					}
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
