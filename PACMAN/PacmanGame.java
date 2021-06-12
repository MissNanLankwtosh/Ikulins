import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


//change Map to allow multiple Mappables in same spot
//change GameEvent to pass something that allows passing the Game/Map/Mappable not just Mappable

public class PacmanGame extends Game {
	private int pillsLeft, score, timer;
	private World map;
	
	PacmanGame(String filename) {
		super();
		map = new World(10,10,this); //change the 10,10 to filename eventually
	}
	
	public int getPillsLeft() {
		return pillsLeft;
	}
	public void setPillsLeft(int pillsLeft) {
		this.pillsLeft = pillsLeft;
	}
	
	public void start() {
		//game logic here
		System.out.println("WASD to move, Q to quit");
		
		pillsLeft = map.getPills();
		score = 0;
		timer = 60;
		
		//game start;
		while(pillsLeft != 0) {

			displayGame();
			turnInput(); //do a '-' on timer after the input is found to be good
			
			if(map.getPacman().getLives() == 0) end("Out of Lives, Game Over");
			
		}
		end("You WIN");
	}
	
	public void turnInput() {
		BufferedReader inp = new BufferedReader(new InputStreamReader(System.in));
		String s;
		try { s = inp.readLine();
		} catch (IOException e) {
			s = "";
			e.printStackTrace();
		}
		System.out.println(s);
		
		if(s.equals("w")) { //move UP
			notify(new GameEvent("pUP",map.getPacman()));
		}
		else if(s.equals("a")) { //move LEFT
			notify(new GameEvent("pLEFT",map.getPacman()));
		}
		else if(s.equals("s")) { //move DOWN
			notify(new GameEvent("pDOWN",map.getPacman()));
		}
		else if(s.equals("d")) { //move RIGHT
			notify(new GameEvent("pRIGHT",map.getPacman()));
		}
		else if(s.equals("q")) end("Q Pressed. Game Over");
	}
	
	private void displayGame() {
		displayMessage("SCORE: " + score + "  --  TIMER: " + timer);
		map.display();
	}
	
	public void end(String endMessage) {
		displayMessage(endMessage);
		System.exit(0); //end program
	}
	
	private void displayMessage(String message) {
		//easy to change this later for when we need GUI
		System.out.println(message);
	}

	
	
	public static void main(String args[]) {
		new PacmanGame("filename").start(); //start the game
	}
}
