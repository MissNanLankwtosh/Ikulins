import java.awt.event.KeyEvent;


/**
 * @author Colin MacDougall
 */
public class FroggerGame extends Game {

	private Frogger frogger;

	private Car[] cars;
	private boolean gameON;
	private int NumCars = 16; //the number of cars you'll be facing
	
	public FroggerGame() {
		super();
		setUpGame();	}
	/**
	 * Prints stuff
	 */
	public void printToConsole(){
		//update score or something
	}
	/**
	 *
	 */
	public void start() { //start game
		gameON = true;}
	/**
	 * 
	 */
	public void end(String message) {
		System.out.println(message);
		gameON = false;}
	/**
	 * Sets up the map and frogger with cars to dodge
	 * @author Colin MacDougall
	 */
	public void setUpGame() {

		cars = new Car[NumCars];
		gameON = false;
		
		setMap(new FroggerMap("FROGGER/FrogMap.txt")); //set up the Map
				
		frogger = new Frogger(9,9,Direction.UP,3); //add frogger
		frogger.spawn(getMap());
		addListener(frogger);
		
		//adding lots of cars in specific spots
		// - car 0 to 3 -
		for (int i = 0; i < 4; i++){
			cars[i] = new Car(1+(i*2),3,Direction.RIGHT,1); //make a couple cars along the 3rd row that will move Right 										  
			cars[i].spawn(getMap());						//(last parameter is the car's "lives" which may be
			addListener(cars[i]);}							//used to change the car's image or something later
		// - car 4 to 7 - 
		for (int j = 4; j < 8; j++){
			cars[j] = new Car(1+((j-4)*2),7,Direction.RIGHT,1);
			cars[j].spawn(getMap());
			addListener(cars[j]);}
		// - car 8 to 11 -
		for (int k = 8; k < 12; k++){
			cars[k] = new Car(k,4,Direction.LEFT,1);
			cars[k].spawn(getMap());
			addListener(cars[k]);}
		// - car 12 to 15 - 
		for (int m = 12; m < 16; m++){
			cars[m] = new Car(m,6,Direction.LEFT,1);
			cars[m].spawn(getMap());
			addListener(cars[m]);
		}
		// - Done adding Cars -
		//-------------------------------------
	
		/*
		BigPillItem bigPill = new BigPillItem(1,1); //change this to be the prize on the other side
		getMap().addMappable(bigPill);
		*/
	
	} //done setup
	
	/**
	 * Handles all the inputs from the keyboard. Inputs are taken from FroggerPanel onKeypress() method
	 * @param keycode passed from the FroggerPanel as a KeyEvent on it
	 * @author Alexander Clelland
	 */
	public void recieveInput(int keycode) {
		if (gameON == false) return;
		switch (keycode) {
		case KeyEvent.VK_LEFT:
			frogger.setDirection(Direction.LEFT);
			break;
		case KeyEvent.VK_RIGHT:
			frogger.setDirection(Direction.RIGHT);
			break;
		case KeyEvent.VK_UP:
			frogger.setDirection(Direction.UP);
			break;
		case KeyEvent.VK_DOWN:
			frogger.setDirection(Direction.DOWN);
			break;
		case KeyEvent.VK_ESCAPE:
			System.out.println("Escape Pressed. Terminate");
			System.exit(0);
			break;
		}
				
		frogger.updateLocation(getMap());
		if(keycode >= KeyEvent.VK_LEFT && keycode <= KeyEvent.VK_DOWN){
			notify(new GameEvent("MoveCars", this)); //yell at the cars to move
			printToConsole(); //whatever you put in the method gets printed, a score maybe?
			
			//VICTORY AND END CONDITIONS
			if(frogger.getNumOflives() < 0) end("OUT OF LIVES!");
			//Put the win condition here, did you make it back with the flower?
		}
		
		

}


	
	public static void main(String args[]) {
		FroggerGame tempGame = new FroggerGame(); //create the game
		new FroggerFrame(new FroggerPanel(tempGame)); //set up the frame
		tempGame.start(); //start the game
	}
	
}
