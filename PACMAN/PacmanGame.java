import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


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

		if (!setUpNewGame(filename)) end("Errors when adding Mappables to Map");
		
	}
	
	public void start() {
		//game logic here
		System.out.println("WASD to move, Q to quit");
		
		//game start;
		while(pillsLeft != 0) {
						
			displayGame();
			turnInput();
			
			if(pacman.getLives() == 0) end("Out of Lives, Game Over");
		}
		end("You WIN");
	}
	
	public void turnInput() {
		BufferedReader inp = new BufferedReader(new InputStreamReader(System.in));
		String s;
		try { s = inp.readLine();
		} catch (IOException e) {
			s = "";
			e.printStackTrace();
		}
		
		if(s == "w") { //move UP
			
		}
		else if(s == "a") { //move LEFT
			
		}
		else if(s == "s") { //move DOWN
			
		}
		else if(s == "d") { //move RIGHT
			
		}
		else if(s == "q") end("Q Pressed. Game Over");
	}
	
	private void displayGame() {
		displayMessage(score+"");
		((World)map).display();
	}
	
	public void end(String endMessage) {
		displayMessage(endMessage);
		System.exit(0); //end program
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
		
		super.map = new World(10,10); //create the Map to place Mappables on
				
		pillsLeft = 0;
		score = 0;
		timer = 60;		
		ghosts = new Ghost[4];
		boolean errorFlag = false;
		
		pacman = new Pacman(1,1,3); //add pacman to the map
		if (!map.addMappable(pacman)) {
			displayMessage("ERROR: Invalid Pacman Position -- [" + pacman + "]");
			errorFlag = true; //Position Invalid, Flag error and display message
		}
		addListener(pacman);
		
		BigPill tempBigPill = new BigPill(1,2);
		if(!map.addMappable(tempBigPill)) {
			displayMessage("ERROR: Invalid BigPill Position -- [" + tempBigPill + "]");
			errorFlag = true; //Position Invalid, Flag error and display message
		}
		addListener(tempBigPill);
				
		for (Ghost ghost:ghosts) { //add the ghosts to the map
			ghost = new Ghost(1,3);
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

	
	
}
