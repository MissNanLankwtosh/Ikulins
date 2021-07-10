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
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	public World getMap() {
		return map;
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
			turn();
			
			timer--;
			if(map.getPacman().getLives() == 0) end("Out of Lives, Game Over");
			else if(timer == 0) end("Time Up, Game Over");
		}
		end("You WIN");
	}
	
	private void turn() {
		BufferedReader inp = new BufferedReader(new InputStreamReader(System.in));
		String s;
		boolean success = false;
		while(success == false) {
			try { s = inp.readLine();
			} catch (IOException e) {
				s = "--Not Proper Input--";
				e.printStackTrace();
			}
			
			if(s.equals("q")) {
				end("Force Quit");
			}
			
			//if it is a movement key press notify with the movement
			else if(s.equals("w") || s.equals("a") || s.equals("s") || s.equals("d")){
				success = true;
				notify(new GameEvent(s, this));
			}
		}
		
		
	}
	
	private void displayGame() {
		displayMessage("SCORE: " + score + "  --  TIMER: " + timer);
		map.consoleDisplay();
	}
	
	public void end(String message) {
		displayMessage(message);
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
