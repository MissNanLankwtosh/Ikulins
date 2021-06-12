
public class World extends Map implements GameListener {
	private PacmanGame game;
	private Pacman pacman;
	private Ghost ghosts[];
	private int totalPills;

	public World(int Xsize, int Ysize, PacmanGame game) { //used for testing
		super(Xsize, Ysize, game);
		this.game = game;
		
		if (!initMap()) System.out.println("Errors when adding Mappables to Map");
	}

	public World(String fileName, PacmanGame game) {
		super(fileName, game);
		this.game = game;
		
		if (!initMap()) System.out.println("Errors when adding Mappables to Map");
	}
	
	public boolean initMap() {			
		ghosts = new Ghost[1];
		boolean errorFlag = false;
		
		pacman = new Pacman(1,1,3); //add pacman to the map
		if (!addMappable(pacman)) {
			displayMessage("ERROR: Invalid Pacman Position -- [" + pacman + "]");
			errorFlag = true; //Position Invalid, Flag error and display message
		}
		game.addListener(pacman);
		
		BigPill tempBigPill = new BigPill(1,2);
		if(!addMappable(tempBigPill)) {
			displayMessage("ERROR: Invalid BigPill Position -- [" + tempBigPill + "]");
			errorFlag = true; //Position Invalid, Flag error and display message
		}
		game.addListener(tempBigPill);
				
		for (Ghost ghost:ghosts) { //add the ghosts to the map
			ghost = new Ghost(1,3);
			if (!addMappable(ghost)) {
				displayMessage("ERROR: Invalid Ghost Position -- [" + ghost + "]");
				errorFlag = true; //Position Invalid, Flag error and display message
			}
			game.addListener(ghost);
		}
		
		for (int x=0; x<getX(); x++) { //fill all empty spaces with Little Pills
			for (int y=0; y<getY(); y++) {
				if(isEmpty(x, y)) { //add a pill if the spot is empty
					LittlePill tempPill = new LittlePill(x,y);
					addMappable(tempPill);
					totalPills++;
				}
			}
		}
		if(errorFlag) return false;
		//map created successfully
		return true;
	}
	
	public void display() {
		String s;
		for (int x=0; x<xSize; x++) {
			s = "";
			for (int y=0; y<ySize; y++) {
				if(isEmpty(x, y)) s = s + " ";
				else if(isWall(x, y)) s = s + "X";
				else if(getMappable(x,y) == null) return;
				else if(getMappable(x,y) instanceof Pacman) s = s + "P";
				else if(getMappable(x,y) instanceof Ghost) s = s + "G";
				else if(getMappable(x,y) instanceof BigPill) s = s + "o";
				else if(getMappable(x,y) instanceof LittlePill) s = s + ".";
			}
			System.out.println(s);
		}
	}
	
	/**
	 * Display a String to the terminal
	 * @param message the String to be displayed
	 */
	private void displayMessage(String message) {
		//easy to change this later for when we need GUI
		System.out.println(message);
	}
	
	public Pacman getPacman() {
		return pacman;
	}
	
	public int getPills() {
		return totalPills;
	}

	@Override
	public void onEvent(GameEvent e) {
		if(e.getMappable() instanceof Pacman) { //PACMAN MOVEMENT
			if(e.getSource().equals("pUP")) {
				
			}
			if(e.getSource().equals("pLEFT")) {
				
			}
			if(e.getSource().equals("pDOWN")) {
				
			}
			if(e.getSource().equals("pRIGHT")) {
				
			}
		}
	}
	
	
}
