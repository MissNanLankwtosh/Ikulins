

public class PacmanGame extends Game {
	private int pillsLeft, score, timer;
	private Pacman pacman;
	private Ghost ghosts[];
	
	/**
	 * NOTE: How are we going to decide where to place PacMan, BigPills, and Ghosts?
	 * should we place coordinates for them after the map file?
	 * @param numGhosts
	 * Code By Alexander Clelland
	 */
	PacmanGame() {
		super();
			
		if (!setUpNewGame("filename")) end("Errors when adding Mappables to Map");
		
	}
	
	public void start() {
		//game logic here
	}
	
	public void end(String endMessage) {
		displayMessage(endMessage);
		System.exit(0); //end program
	}
	
	public void moveGhosts() {
		
	}

	public static void main(String args[]) {
		new PacmanGame().start(); //4 ghosts and start the game
	}
	
	
	
	/**
	 * Sets up the map that will be used for the game.
	 * @return true if all Mappables added with no errors, false otherwise
	 * Code By Alexander Clelland
	 */
	private boolean setUpNewGame(String filename) {
		//NOTE, this will have to be changed when we decide to use a file to get the coordinates of mappables
		
		super.map = new World(filename); //create the Map to place Mappables on
		
		pillsLeft = 0;
		score = 0;
		timer = 60;		
		ghosts = new Ghost[4]; //this will need to be changed when we use file for numghosts
		boolean errorFlag = false;
		
		pacman = new Pacman(5,5,3); //add pacman to the map
		if (!map.addMappable(pacman)) {
			displayMessage("ERROR: Invalid Pacman Position -- [" + pacman + "]");
			errorFlag = true; //Position Invalid, Flag error and display message
		}
		addListener(pacman);
		
		for (int i=0; i<10; i++) { //add BigPills to the Map, NOTE the 10 will be how many pills
			BigPill tempBigPill = new BigPill(5,5);
			if(!map.addMappable(tempBigPill)) {
				displayMessage("ERROR: Invalid BigPill Position -- [" + tempBigPill + "]");
				errorFlag = true; //Position Invalid, Flag error and display message
			}
			addListener(tempBigPill);
		}
		
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
				LittlePill tempPill = new LittlePill(x,y);
				if(map.addMappable(tempPill)) {
					pillsLeft++; //if LittlePill added increment pillsLeft	
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
	
	
	
	
	
}
