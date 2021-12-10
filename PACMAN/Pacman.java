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
	public void die(Map map) {
		
	}

	@Override
	public void onEvent(GameEvent e) {
		
		//MOVEMENT
		if(e.getSource().equals("movement") && e.getGameValue() instanceof PacmanGame) { //if movement has occured
			PacmanGame tempGame = (PacmanGame)e.getGameValue(); //create a temp variable of the game
			
			ArrayList<Mappable> mappables = tempGame.getMap().getMappable(getX(),getY());
			
			for(int i=mappables.size()-1; i>=0; i--) { //go through array to check if pacman is on a LittlePillItem
				Mappable tempMappable = mappables.get(i);
				if(tempMappable instanceof LittlePillItem) {
					tempGame.getMap().removeMappable(getX(),getY(),i);
					tempGame.setScore(tempGame.getScore()+1); //increment score by 1
					tempGame.setPillsLeft(tempGame.getPillsLeft()-1); //decrement pillsLeft by 1
				}
				else if(tempMappable instanceof Ghost) {
					if(state == PacmanState.NORMAL) { //NORMAL
						tempGame.getMap().removeMappable(getX(),getY(),i);
						tempMappable.setX(9);
						tempMappable.setY(5);
					}
					else { //BEASTMODE
						
					}
	
				}
				else if(tempMappable instanceof BigPillItem) {
					
				}
			}
		}//end of Movement
		
		
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
