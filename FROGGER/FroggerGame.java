import java.awt.event.KeyEvent;


/**
 * @author Colin MacDougall
 */
public class FroggerGame extends Game {

	private Frogger frogger;

	private Car[] cars;
	private Flower flowerpatch;
	private Home home;
	
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
		
		//add the lillypad for froggers home
		home = new Home(9,9);
		getMap().addMappable(home);
				
		frogger = new Frogger(9,9,Direction.UP,3); //add frogger
		frogger.spawn(getMap());
		addListener(frogger);
		
		//adding lots of cars in specific spots
		// - car 0 to 3 -
		for (int i = 0; i < 4; i++){
			cars[i] = new Car(1+(i*3),3,Direction.LEFT,2); //make a couple cars along the 3rd row that will move Right 										  
			cars[i].spawn(getMap());						//the last parameter is the car's "lives" and will dictate
			addListener(cars[i]);							//which image the car will be assigned (2 for left, 1 for right)
			getMap().addMappable(cars[i]);}
		// - car 4 to 7 - 
		for (int j = 4; j < 8; j++){
			cars[j] = new Car(1+((j-4)*3),7,Direction.RIGHT,1);
			cars[j].spawn(getMap());
			addListener(cars[j]);
			getMap().addMappable(cars[j]);}
		// - car 8 to 11 -
		for (int k = 11; k >= 8; k--){
			cars[k] = new Car(k,4,Direction.RIGHT,k-5); //the lives for the next two sets of cars are set up
			cars[k].spawn(getMap());				    //in such a way so that when the images are assigned
			addListener(cars[k]);					    //based on lives, they will form the trucks
			getMap().addMappable(cars[k]);}
		// - car 12 to 15 - 						    //cars with lives 3/4/5/6 will be right moving trucks
		for (int m = 12; m < 16; m++){					//cars with lives 7/8/9/10 will be left moving trucks
			cars[m] = new Car(m,6,Direction.LEFT,m-5);
			cars[m].spawn(getMap());
			addListener(cars[m]);
			getMap().addMappable(cars[m]);}
		// - Done adding Cars -
		//-------------------------------------
	
		//add the patch of flowers on the other side (this is what frogger wants)
		flowerpatch = new Flower(9,1);
		getMap().addMappable(flowerpatch);
		

		
	
	} //done setup
	
	/**
	 * Handles all the inputs from the keyboard. Inputs are taken from FroggerPanel onKeypress() method
	 * @param keycode passed from the FroggerPanel as a KeyEvent on it
	 * 
	 * Instead of the code that used to be below the switch statement, it is now encapsuled in movementMacro()
	 * so that no cheating can occure. The game will only progress on valid key presses.
	 * Also, a second notify of "collisionCheck" especially for frogger is to check for collisions after all
	 * movement has occured.  This is to avoid errors with moving into cells previously occupied, etc.
	 * 
	 */
	public void recieveInput(int keycode) {
		if (gameON == false) return;
		switch (keycode) {
		case KeyEvent.VK_LEFT:
			frogger.setDirection(Direction.LEFT);
			movementMacro();
			break;
		case KeyEvent.VK_RIGHT:
			frogger.setDirection(Direction.RIGHT);
			movementMacro();
			break;
		case KeyEvent.VK_UP:
			frogger.setDirection(Direction.UP);
			movementMacro();
			break;
		case KeyEvent.VK_DOWN:
			frogger.setDirection(Direction.DOWN);
			movementMacro();
			break;
		case KeyEvent.VK_ESCAPE:
			System.out.println("Escape Pressed. Terminate");
			System.exit(0);
			break;
		}
				
		//loss condition
		if(frogger.getNumOflives() < 0) end("OUT OF LIVES!");
		//update frogger location - because of how our movement and collision detection is implemented,
		// ALWAYS DO THIS LAST or else you will not be able to move to predict where cars will be.

	}

	public void winner(){
		end("You Win! Congratulations!");
	}

	/**
	 * This method is simply to encapsulate code that would otherwise be repeated in the switch statement
	 * in the receiveInput method above.
	 * 
	 * The code notifies all cars to move, moves frogger, and then notifies frogger to check to see if he
	 * has collided with anything
	 */
	public void movementMacro(){
		notify(new GameEvent("movement", this));
		frogger.updateLocation(getMap());
		notify(new GameEvent("collisionCheck", this));
	}
	
	public static void main(String args[]) {
		FroggerGame tempGame = new FroggerGame(); //create the game
		new FroggerFrame(new FroggerPanel(tempGame)); //set up the frame
		tempGame.start(); //start the game
	}
	
}
