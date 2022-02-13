import java.awt.event.KeyEvent;


public class MouselandGame extends Game {
	private int NUM_MICE = 4;
	public int getNUM_MICE() {
		return NUM_MICE;
	}

	public void setNUM_MICE(int nUM_MICE) {
		NUM_MICE = nUM_MICE;
	}

	private Hero hero;
	private Mouse[] mice;
	private Exit ladder;
	
	public MouselandGame() {
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
		mice = new Mouse[NUM_MICE];
		ladder = new Exit(18, 1);
		setMap(new MouselandMap("MOUSELAND/mouselandMap.txt")); //set up the PacmanMap
		getMap().addMappable(ladder);
		hero = new Hero(9,9,Direction.UP,3); //add Hero
		hero.spawn(getMap());
		addListener(hero);
		
		for (int i=0; i<NUM_MICE; i++){ //add ghosts
			mice[i] = new Mouse(i+8,5,Direction.DOWN,i+1);
			mice[i].spawn(getMap());
			addListener(mice[i]);
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
		case KeyEvent.VK_SPACE:
			hero.layTrap(this.getMap());
			return; //return made a trap second
		case KeyEvent.VK_ESCAPE:
			System.out.println("Escape Pressed. Terminate");
			System.exit(0);
			break;
		}
		hero.updateLocation(getMap());
		
		notify(new GameEvent("heroMovement", this)); //notify anything that cares if hero and the mice have moved
		if(keycode >= KeyEvent.VK_LEFT && keycode <= KeyEvent.VK_DOWN){
			for (Mouse mouse1: mice) {
				mouse1.moveMice(hero.getX(),hero.getY(),getMap());
				notify(new GameEvent("mousemovement", this)); //notify anything that cares if hero and the mice have moved
			}
		}
	}
	
	public static void main(String args[]) {
		MouselandGame tempGame = new MouselandGame(); //create the game
		new MouselandFrame(new MouselandPanel(tempGame)); //set up the frame
		tempGame.start(); //start the game
	}
	
}
