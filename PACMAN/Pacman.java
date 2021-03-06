import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.Timer;

/**
 * This is the class representing the Pacman player.
 * @variable  state - represents the state (Normal or BeastMode) of Pacman
 * @variable  timer - counts down the time left when Pacman is in BeastMode
 */
public class Pacman extends Player implements GameListener, ActionListener{
	/**
	 * @uml.property  name="state"
	 * @uml.associationEnd  
	 */
	private PacmanState state;
	private Timer beastTimer;
	private PacmanGame game;
	private int animationstate=1; // set to 1 so that you waka waka properly at the start
	
	public Pacman(int x, int y) {
		super(x, y);
		beastTimer = new Timer(5000, this);
		beastTimer.stop();
		// TODO Auto-generated constructor stub
	}
	/**
	 * Initializes Pacman and associates the pacman image to the player
	 * @param x
	 * @param y
	 * @param direction
	 * @param numOfLives
	 * @param game 
	 */
	public Pacman(int x, int y, Direction direction, int numOfLives, PacmanGame g) {
		super(x,y,direction,numOfLives);
		game = g; //pacman retains some pointer to the game
		setImage("PACMAN/pacmanimg3.png");
		state = PacmanState.NORMAL;
		beastTimer = new Timer(5000, this);
	}

	/**
	 * This method spawns Pacman on the Map. Pacman will always spawn at location (9,9) on the Map.
	 * @author Alexander Clelland
	 */
	public void spawn(Map map) {
		setX(9);
		setY(9);
		map.addMappable(this);
	}
	/**
	 * Kills Pacman. Removes  existance on the map.
	 * @author Alexander Clelland
	 */
	public void die(Map map) {
		game.yellPacmanDied(); //tell the game to notify that pacman died (for the ghosts)
		setNumOflives(getNumOflives()-1); //decrement pacman lives
		//the following code finds the cell on the map and accesses the list at that cell to see if there
		//are multiple objects in the same spot on the map.  This searches through those multiple objects
		//for the pacman object to remove it.
        ArrayList<Mappable> mappableArray = map.getMappable(getX(),getY());
        if (mappableArray == null) return;
        for (int i=mappableArray.size()-1; i >= 0; i--) {
                if(mappableArray.get(i) == this) {
                        map.removeMappable(getX(),getY(),i);
                        break;
                }
        }
		
	}

	@Override
	/**
	 * This deals with movement affecting Pacman in some way.
	 * If Pacman moves, he can either collide with a little pill, a big pill, or a ghost
	 * @case little pill, score must be increased, the little pill must be removed 
	 * @case big pill, Pacman's state must be changed
	 * @case ghost, must kill or be killed depending on the state of Pacman
	 * @author Alexander Clelland
	 */
	public void onEvent(GameEvent e) {
		
		//MOVEMENT
		if(e.getSource().equals("movement") && e.getGameValue() instanceof PacmanGame) { //if movement has occured
			
			//The following block of code is just simple animation for the waka waka motion pacman makes
			//
			// -Colin
			if (animationstate == 0){
				if (getState() == PacmanState.NORMAL){
					setImage("PACMAN/pacmanimg3.png");
				}else{
					setImage("PACMAN/pacmanimg.png");
				}
				animationstate = 1;
			}else if(animationstate == 1){
				if (getState() == PacmanState.NORMAL){
					setImage("PACMAN/pacmanimg4.png");
				}else{
					setImage("PACMAN/pacmanimg2.png");
				}
				animationstate = 0;
			}
			//----------------------------------------
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
						die(tempGame.getMap());
						spawn(tempGame.getMap());
					}
					else { //BEASTMODE
						((Ghost)tempMappable).die(tempGame.getMap()); //spawn the ghost at start
						setImage("PACMAN/pacmaneatrabbit.png");
					}
				}
				else if(tempMappable instanceof BigPillItem) {
					tempGame.getMap().removeMappable(getX(),getY(),i);
					setState(PacmanState.BEASTMODE); //turn on BEASTMODE!
					//change images to beastmode
				}
			}

		}//end of Movement
	}
	
	
	/**
	 * Allows pacman's state to be changed.
	 * @param state  Pacman's new state
	 * @uml.property  name="state"
	 * 
	 * @author Colin MacDougall
	 * credits to Imran for the timer business (but isn't a timer a little imbalanced? should really be move based =P )
	 */
	public void setState(PacmanState state) {
		if(state == PacmanState.BEASTMODE){
			beastTimer.stop();				  //stop any previous timer that may have been running
			beastTimer.setInitialDelay(5000); //top up the timer to 5000 before setting beast mode
			beastTimer.start();				  //start the count down
			setImage("PACMAN/pacmanimg.png"); //BEASTMODE image (aka pacman hopped up on drugs)
		} else {
			setImage("PACMAN/pacmanimg3.png"); //back to normal pacman
		}
		this.state = state;
		game.yellPacmanState(); //notify the ghosts of pacman's state, so they can either start running or return to chasing
	}
	/**
	 * @return  Returns pacmans current state
	 * @uml.property  name="state
	 */
	public PacmanState getState() {
		return state;
	}

	@Override
	//timer is done, so stop the timer? seems awkward, but it works? also, set packman state back to normal
	public void actionPerformed(ActionEvent e) {
		beastTimer.stop();
		this.setState(PacmanState.NORMAL);
	}

}
