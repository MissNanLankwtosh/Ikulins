import java.awt.event.KeyEvent;


public class PacmanGame extends Game {
	private static final int NUM_GHOSTS = 4;
	
	private int pillsLeft, score;
	private Pacman pacman;
	private Ghost[] ghosts;
	
	public PacmanGame() {
		super();
		setUpGame();
		
	}
	
	/**
	 * Prints current map to console
	 */
	public void printToConsole(){
		System.out.println(this.getMap());
	}
	/**
	 *
	 */
	public void start() {
		
	}
	
	/**
	 * 
	 */
	public void end() {

	}
	
	/**
	 * Sets up the map and all the players/items for a new game
	 */
	public void setUpGame() {
		score = 0;
		pillsLeft = 0;
		ghosts = new Ghost[4];
		
		setMap(new PacmanMap("PACMAN/pacmanMap.txt")); //set up the PacmanMap
		
		for(int x=0; x<getMap().getX(); x++) { //add LittlePillItems to every Empty Space
			for(int y=0; y<getMap().getY(); y++) {
				if(getMap().addMappable(new LittlePillItem(x,y))) pillsLeft++;
			}
		}
		
		pacman = new Pacman(9,9,Direction.UP,3); //add pacman
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

	}
	
	/**
	 * Handles all the inputs from the keyboard. Inputs are taken from PacmanPanel onKeypress() method
	 * @param keycode passed from the PacmanPanel as a KeyEvent on it
	 */
	public void recieveInput(int keycode) {
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
		pacman.updateLocation(getMap());
		
		notify(new GameEvent("movement", this)); //notify anything that cares if pacman and the ghosts have moved
		for (Ghost ghost : ghosts) {
			ghost.moveGhosts(pacman.getX(),pacman.getY(),pacman.getState(),getMap());
			notify(new GameEvent("movement", this)); //notify anything that cares if pacman and the ghosts have moved
		}

}


	/**
	 * Moves pacman depending on the KeyEvent passed. Method called from recieveInput().
	 * @param keycode 4 cases: VK_LEFT,VK_RIGHT,VK_UP,VK_DOWN
	 */
	public void movement(int keycode) {
		
	}
	
	public void setScore(int score) {
		this.score = score;
	}
	public int getScore() {
		return score;
	}
	public void setPillsLeft(int pillsLeft) {
		this.pillsLeft = pillsLeft;
	}
	public int getPillsLeft() {
		return pillsLeft;
	}
	
	
	
	public static void main(String args[]) {
		PacmanGame tempGame = new PacmanGame(); //create the game
		new PacmanFrame(new PacmanPanel(tempGame)); //set up the frame
		tempGame.start(); //start the game
	}
	
}
