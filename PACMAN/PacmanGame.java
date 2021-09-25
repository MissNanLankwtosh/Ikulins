
public class PacmanGame extends Game {
	private int pillsLeft, score, timer;
	private Pacman pacman;
	private Ghost[] ghosts;
	
	public PacmanGame() {
		super();
	}
	
	/**
	 * 
	 */
	public void start() {
		setUpGame();
	}
	
	/**
	 * 
	 * @return true if game set up successful. false otherwise.
	 */
	public boolean setUpGame() {
		score = 0;
		timer = 180;
		
		setMap(new PacmanMap("pacmanMap.txt")); //set up the PacmanMap
		
		for(int x=0; x<getMap().getX(); x++) { //add LittlePillItems to every Empty Space
			for(int y=0; y<getMap().getY(); y++) {
				getMap().addMappable(new LittlePillItem(x,y));
				pillsLeft++;
			}
		}
		
		pacman = new Pacman(5,5);
		pacman.spawn();
		
		for(Ghost ghost : ghosts) {
			ghost = new Ghost(5,5);
			ghost.spawn();
		}
		
		
		return true;
	}
	
	
	
	
	
	
	
	
	public static void main(String args[]) {
		new PacmanGame().start();
	}
	
}
