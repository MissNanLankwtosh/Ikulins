import java.awt.event.KeyEvent;


public class SurvivalGame extends Game {
	private int NUM_HEADHUNTERS = 4;
	private Survivor hero;
	private HeadHunter[] hunters;
	
	public SurvivalGame() {
		super();
		setUpGame();
		
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
		hunters = new HeadHunter[NUM_HEADHUNTERS];
		setMap(new SurvivalMap("SURVIVAL/survivalMap.txt")); //set up the Map
		hero = new Survivor(9,9,Direction.UP,3); //add Hero
		hero.spawn(getMap());
		addListener(hero);
		
		for (int i=0; i<NUM_HEADHUNTERS; i++){ //add hunters
			hunters[i] = new HeadHunter(i+8,5,Direction.DOWN,i+1);
			hunters[i].spawn(getMap());
			addListener(hunters[i]);
		}
	}
	/**
	 * Handles all the inputs from the keyboard. Inputs are taken from PacmanPanel onKeypress() method
	 * @param keycode passed from the PacmanPanel as a KeyEvent on it
	 */
	public void recieveInput(int keycode) {
		switch (keycode) {
		case KeyEvent.VK_LEFT:
			hero.setDirection(Direction.LEFT);
			break;
		case KeyEvent.VK_RIGHT:
			hero.setDirection(Direction.RIGHT);
			break;
		case KeyEvent.VK_UP:
			hero.setDirection(Direction.UP);
			break;
		case KeyEvent.VK_DOWN:
			hero.setDirection(Direction.DOWN);
			break;
		case KeyEvent.VK_ESCAPE:
			System.out.println("Escape Pressed. Terminate");
			System.exit(0);
			break;
		}
		hero.updateLocation(getMap());
		
		notify(new GameEvent("heroMovement", this)); //notify anything that cares if hero and the hunters have moved
		if(keycode >= KeyEvent.VK_LEFT && keycode <= KeyEvent.VK_DOWN){
			for (int x = 0; x < NUM_HEADHUNTERS; x++){
					hunters[x].moveHeadHunter(hero.getX(),hero.getY(),getMap());
					notify(new GameEvent("HeadHunterMovement", this)); //notify anything that cares if hero and the hunters have moved
			}
		}
	}
	
	/**
	 * Creates a new game of SURVIVAL! and starts the game.
	 * @param args
	 */
	public static void main(String args[]) {
		SurvivalGame tempGame = new SurvivalGame(); //create the game
		new SurvivalFrame(new SurvivalPanel(tempGame)); //set up the frame
		tempGame.start(); //start the game
	}
	
}
