import java.awt.event.KeyEvent;

/**
 * Mouseland Game creates the game itself, as well as deals with collisions and runs the game rules
 * @variable NUM_MICE determines the number of mice that will be displayed on the level
 * @variable hero is the player
 * @variable mice is a list of mice. when moving mice, moveMouse must be invoked on each mouse in this list
 * @variable ladder is the exit
 *
 */
public class MouselandGame extends Game {
	private int NUM_MICE = 4;
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
		setMap(new MouselandMap("MOUSELAND/mouselandMap.txt")); //set up the mouseland map
		getMap().addMappable(ladder);
		hero = new Hero(9,9,Direction.UP,3); //add Hero
		hero.spawn(getMap());
		addListener(hero);
		
		for (int i=0; i<NUM_MICE; i++){ //add mice
			mice[i] = new Mouse(i+8,5,Direction.DOWN,i+1);
			mice[i].spawn(getMap());
			addListener(mice[i]);
		}
	}
	/**
	 * Handles all the inputs from the keyboard. Inputs are taken from MouselandPanel onKeypress() method
	 * @param keycode passed from the MouselandPanel as a KeyEvent on it
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
			for (int x = 0; x < NUM_MICE; x++){
					mice[x].moveMice(hero.getX(),hero.getY(),getMap());
					notify(new GameEvent("mouseMovement", this)); //notify anything that cares if hero and the mice have moved
			}
		}
	}
	/**
	 * Sets up the game to start and be displayed on the interface.
	 * @param args
	 */
	public static void main(String args[]) {
		MouselandGame tempGame = new MouselandGame(); //create the game
		new MouselandFrame(new MouselandPanel(tempGame)); //set up the frame
		tempGame.start(); //start the game
	}
	
}
