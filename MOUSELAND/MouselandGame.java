import java.awt.event.KeyEvent;


public class MouselandGame extends Game {
	private static final int NUM_MICE = 4;
	private Hero hero;
	private Mouse[] mice;
	
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
		mice = new Mouse[4];
		
		setMap(new MouselandMap("PACMAN/mouselandMap.txt")); //set up the PacmanMap
		
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
		case KeyEvent.VK_ESCAPE:
			System.out.println("Escape Pressed. Terminate");
			System.exit(0);
			break;
		}
		hero.updateLocation(getMap());
		
		notify(new GameEvent("movement", this)); //notify anything that cares if hero and the mice have moved
		for (Mouse mouse: mice) {
			mouse.moveMice(hero.getX(),hero.getY(),getMap());
			notify(new GameEvent("movement", this)); //notify anything that cares if hero and the mice have moved
		}

	}
	
	public static void main(String args[]) {
		MouselandGame tempGame = new MouselandGame(); //create the game
		new MouselandFrame(new MouselandPanel(tempGame)); //set up the frame
		tempGame.start(); //start the game
	}
	
}
