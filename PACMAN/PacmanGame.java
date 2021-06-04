//change Map to allow multiple Mappables in same spot
//add a delimiter to Map so we can add text after the map to allow storage of variables

public class PacmanGame extends Game {
	private int pillsLeft, score, timer;
	private Pacman pacman;
	private Ghost ghosts[];
	
	/**
	 * 
	 * @param numGhosts
	 * Code By Alexander Clelland
	 */
	PacmanGame(String filename) {
		super();

		if (!setUpNewGame(filename)) end("Errors when adding Mappables to Map",-1);
		
	}
	
	public void start() {
		//game logic here
		displayMessage(this.toString());
	}
	
	public void end(String endMessage, int status) {
		displayMessage(endMessage);
		System.exit(status); //end program
	}
	
	public void moveGhosts() {
		
	}

	public static void main(String args[]) {
		new PacmanGame("filename").start(); //4 ghosts and start the game
	}
	
	
	
	/**
	 * Sets up the map that will be used for the game.
	 * @return true if all Mappables added with no errors, false otherwise
	 * Code By Alexander Clelland
	 */
	//might change this later so it goes into the World Class... but needs to allow addition to the GameListeners
	private boolean setUpNewGame(String filename) {
		
		super.map = new World(filename); //create the Map to place Mappables on
				
		pillsLeft = 0;
		score = 0;
		timer = 60;		
		ghosts = new Ghost[4];
		boolean errorFlag = false;
		
		pacman = new Pacman(5,5,3); //add pacman to the map
		if (!map.addMappable(pacman)) {
			displayMessage("ERROR: Invalid Pacman Position -- [" + pacman + "]");
			errorFlag = true; //Position Invalid, Flag error and display message
		}
		addListener(pacman);
		
		BigPill tempBigPill = new BigPill(5,5);
		if(!map.addMappable(tempBigPill)) {
			displayMessage("ERROR: Invalid BigPill Position -- [" + tempBigPill + "]");
			errorFlag = true; //Position Invalid, Flag error and display message
		}
		addListener(tempBigPill);
				
		for (Ghost ghost:ghosts) { //add the ghosts to the map
			ghost = new Ghost(5,5);
			if (!map.addMappable(ghost)) {
				displayMessage("ERROR: Invalid Ghost Position -- [" + ghost + "]");
				errorFlag = true; //Position Invalid, Flag error and display message
			}
			addListener(ghost);
		}
		
		for (int x=0; x<map.getX(); x++) { //fill all empty spaces with Little Pills
			for (int y=0; y<map.getY(); y++) {
				if(map.isEmpty(x, y)) { //add a pill if the spot is empty
					LittlePill tempPill = new LittlePill(x,y);
					map.addMappable(tempPill);
					pillsLeft++;
				}
			}
		}
		if(errorFlag) return false;
		//map created successfully
		return true;
	}
	
	/**
	 * Display a String to the terminal
	 * @param message the String to be displayed
	 */
	private void displayMessage(String message) {
		//easy to change this later for when we need GUI
		System.out.println(message);
	}
	
	@Override
	public String toString() {
		String s = "";
		for (int x=0; x<map.getX(); x++) {
			for (int y=0; y<map.getY(); y++) {
				//add the things to s so you can see the map
			}
		}
		return s;
	}
	
	
	
}
