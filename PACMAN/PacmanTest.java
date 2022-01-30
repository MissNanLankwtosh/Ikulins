import junit.framework.*;
public class PacmanTest extends TestCase {
	private PacmanGame testGame;
		public void setUp(){
		 testGame = new PacmanGame();
	}
	public void testNewGame(){
		setUp();
		assertEquals(0, testGame.getScore());
		assertEquals(106, testGame.getPillsLeft());
	}
		/*
		Game Startup Assertions
	     -- Make sure correct pillsLeft, score, timer

	Set state + assert it 

	Move into wall
		-- returns false
	Move into nothing
		-- returns true
	Move into pill
		-- Make sure score updates, pillsleft updates
		-- returns true
	Move into Ghost
		-- depending on state
		-- assert that lives changes or that score increases a lot
	Move into Big Pill
		-- changing state
	End Game
		-- assert no lives
		*/
	
}
