import java.awt.event.KeyEvent;


/**
 * @author  imran
 */
public class PacmanGame extends Game {
	private static final int NUM_GHOSTS = 4;
	/**
	 * @uml.property  name="pillsLeft"
	 */
	private int pillsLeft;
	/**
	 * @uml.property  name="score"
	 */
	private int score;
	/**
	 * @uml.property  name="pacman"
	 * @uml.associationEnd  
	 */
	private Pacman pacman;
	/**
	 * @uml.property  name="ghosts"
	 * @uml.associationEnd  multiplicity="(0 -1)"
	 */
	private Ghost[] ghosts;
	private boolean gameON;
	
	public PacmanGame() {
		super();
		setUpGame();
		
	}
	
	/**
	 * Prints current map to console
	 */
	public void printToConsole(){
		System.out.println("Score: " + score + "      Lives: " + pacman.getNumOflives());
	}
	/**
	 *
	 */
	public void start() {
		gameON = true;
	}
	
	/**
	 * 
	 */
	public void end(String message) {
		System.out.println(message);
		gameON = false;
	}
	
	/**
	 * Sets up the map and all the players/items for a new game
	 * @author Alexander Clelland
	 */
	public void setUpGame() {
		score = 0;
		pillsLeft = 0;
		ghosts = new Ghost[4];
		gameON = false;
		
		setMap(new PacmanMap("PACMAN/pacmanMap.txt")); //set up the PacmanMap
				
		pacman = new Pacman(9,9,Direction.UP,3,this); //add pacman
		pacman.spawn(getMap());
		addListener(pacman);
		
		for (int i=0; i<NUM_GHOSTS; i++){ //add ghosts
			ghosts[i] = new Ghost(i+8,5,Direction.DOWN,i+1);
			ghosts[i].spawn(getMap());
			addListener(ghosts[i]);
		}
		
		BigPillItem bigPill = new BigPillItem(1,1); //add big pills
		getMap().addMappable(bigPill);
		bigPill = new BigPillItem(18,1);
		getMap().addMappable(bigPill);
		bigPill = new BigPillItem(1,9);
		getMap().addMappable(bigPill);
		bigPill = new BigPillItem(18,9);
		getMap().addMappable(bigPill);

		for(int x=0; x<getMap().getX(); x++) { //add LittlePillItems to every Empty Space
			for(int y=0; y<getMap().getY(); y++) {
				if (getMap().isEmpty(x, y) == true) {
					getMap().addMappable(new LittlePillItem(x,y)); 
					pillsLeft++;
				}
			}
		}
		
	}
	
	/**
	 * Handles all the inputs from the keyboard. Inputs are taken from PacmanPanel onKeypress() method
	 * @param keycode passed from the PacmanPanel as a KeyEvent on it
	 * @author Alexander Clelland
	 */
	public void recieveInput(int keycode) {
		if (gameON == false) return;
		switch (keycode) {
		case KeyEvent.VK_LEFT:
			pacman.setDirection(Direction.LEFT);
			break;
		case KeyEvent.VK_RIGHT:
			pacman.setDirection(Direction.RIGHT);
			break;
		case KeyEvent.VK_UP:
			pacman.setDirection(Direction.UP);
			break;
		case KeyEvent.VK_DOWN:
			pacman.setDirection(Direction.DOWN);
			break;
		case KeyEvent.VK_ESCAPE:
			System.out.println("Escape Pressed. Terminate");
			System.exit(0);
			break;
		}
		
		//VICTORY AND END CONDITIONS
		if(pacman.getNumOflives() < 0) end("OUT OF LIVES!");
		if(pillsLeft <= 0) end("YOU WIN! --- ALL PILLS COLLECTED");
				
		pacman.updateLocation(getMap());
		if(keycode >= KeyEvent.VK_LEFT && keycode <= KeyEvent.VK_DOWN){
			for (Ghost ghost : ghosts) {
				ghost.moveGhosts(pacman.getX(),pacman.getY(),getMap());
			}
			notify(new GameEvent("movement", this)); //notify anything that cares if pacman and the ghosts have moved
			printToConsole();
		}
		
		

}

	/**
	 * @param score
	 * @uml.property  name="score"
	 */
	public void setScore(int score) {
		this.score = score;
	}
	/**
	 * @return
	 * @uml.property  name="score"
	 */
	public int getScore() {
		return score;
	}
	/**
	 * @param pillsLeft
	 * @uml.property  name="pillsLeft"
	 */
	public void setPillsLeft(int pillsLeft) {
		this.pillsLeft = pillsLeft;
	}
	/**
	 * @return
	 * @uml.property  name="pillsLeft"
	 */
	public int getPillsLeft() {
		return pillsLeft;
	}
	
	public void yellPacmanState() {
		if (pacman.getState() == PacmanState.NORMAL){
			notify(new GameEvent("pacmanNormal", this));
		} else if (pacman.getState() == PacmanState.BEASTMODE){
			notify(new GameEvent("pacmanBeastmode", this));
		}
	}
	public void yellPacmanDied(){
		notify(new GameEvent("pacmanDied",this));
	}
	
	
	public static void main(String args[]) {
		PacmanGame tempGame = new PacmanGame(); //create the game
		new PacmanFrame(new PacmanPanel(tempGame)); //set up the frame
		tempGame.start(); //start the game
	}
	
}
