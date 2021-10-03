
public class PacmanGame extends Game {
	private int pillsLeft, score, timer;
	private Pacman pacman;
	private Ghost[] ghosts;
	
	public PacmanGame() {
		super();
		setUpGame();
	}
	
	/**
	 * 
	 */
	public void start() {
		System.out.println("test");
	}
	
	/**
	 * 
	 * @return true if game set up successful. false otherwise.
	 */
	public void setUpGame() {
		score = 0;
		timer = 180;
		ghosts = new Ghost[4];
		
		setMap(new PacmanMap("PACMAN/pacmanMap.txt")); //set up the PacmanMap
		
		for(int x=0; x<getMap().getX(); x++) { //add LittlePillItems to every Empty Space
			for(int y=0; y<getMap().getY(); y++) {
				getMap().addMappable(new LittlePillItem(x,y));
				pillsLeft++;
			}
		}
		
		pacman = new Pacman(9,9,Direction.UP,3); //add pacman
		getMap().addMappable(pacman);
		addListener(pacman);
		pacman.spawn(getMap());
		
		int i=1; //i is a counter for which ghost is added
		for(Ghost ghost : ghosts) { //add ghosts
			ghost = new Ghost(i+7,5,Direction.UP,i);
			getMap().addMappable(ghost);
			addListener(ghost);
			ghost.spawn(getMap());
			i++;
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
	
	
	
	
	
	
	
	
	public static void main(String args[]) {
		PacmanGame tempGame = new PacmanGame();
		new PacmanFrame(new PacmanPanel(tempGame));
		tempGame.start();
	}
	
}
