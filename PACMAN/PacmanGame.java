

public class PacmanGame extends Game {
	private int pillsLeft, score, timer;
	private Pacman pacman;
	private Ghost ghosts[];
	
	PacmanGame(int numGhosts) {
		super();
		super.map = new World(null); //could be a problem here... talk about later how we want to do
		//check how many pills are on the map and store in pillsLeft
		score = 0;
		timer = 60;
		
		//How are we going to do this? do we need to create the map by hand in code or using a string?
		//How are we going to make sure its the pacman and ghost and dots being added in the right spots?
		ghosts = new Ghost[numGhosts];
		for (Ghost ghost:ghosts) {
			ghost = new Ghost(5,5); //need to figure out where we want to put the hosts, from World
			addListener(ghost);
		}
		pacman = new Pacman(5,5,3); //need to figure out where we want to put the Pacman, from World
		addListener(pacman);
	}
	
	public void start() {

	}
	
	public void end() {
		
	}
	
	public void moveGhosts() {
		
	}

	public static void main(String args[]) {
		new PacmanGame(4).start(); //4 ghosts and start the game
	}
	
	
	
	
	
	
	
	
	
	
	
}
