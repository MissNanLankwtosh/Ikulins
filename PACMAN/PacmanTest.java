import java.awt.Image;
import java.util.ArrayList;

import javax.swing.ImageIcon;

import junit.framework.*;

/**
 * 
 * @author Alexander Clelland
 */
public class PacmanTest extends TestCase {
	Pacman testNormal, testNormal2,testNormal3, testZero, testNegative, testHuge;
	Image testImage;
	PacmanMap map;
	PacmanGame game;
	
	public void setUp(){
		testNormal = new Pacman(5,10,Direction.LEFT,1);
		testNormal2 = new Pacman(15,20,Direction.LEFT,1);
		testNormal3 = new Pacman(50,50,Direction.LEFT,1);
		testZero = new Pacman(0,0,Direction.LEFT,2);
		testNegative = new Pacman(-5,-10,Direction.LEFT,3);
		testHuge = new Pacman(5000,10000,Direction.LEFT,4);
		
		testImage = new ImageIcon("PACMAN/ghost1img.png").getImage();
		
		map = new PacmanMap(50,50);
			//surround testNormal2 with walls so it can't move
			map.addWall(15,21);
			map.addWall(15,19);
			map.addWall(14,20);
			map.addWall(16,20);
			
		game = new PacmanGame();
		
	}
	public void testNewGame(){
		setUp();
		
		
	}
	
	
	//MAPPABLE TESTS
	public void testGetSetX() {
		//TEST GET INITIAL VALUES
		assertEquals(testNormal.getX(), 5);
		assertEquals(testZero.getX(), 0);
		assertEquals(testNegative.getX(), -5);
		assertEquals(testHuge.getX(), 5000);
		
		//SET NEW VALUE
		testNormal.setX(8);
		
		//TEST GET NEW VALUE
		assertEquals(testNormal.getX(), 8);
	}
	
	public void testGetSetY() {
		//TEST GET INITIAL VALUES
		assertEquals(testNormal.getY(), 10);
		assertEquals(testZero.getY(), 0);
		assertEquals(testNegative.getY(), -10);
		assertEquals(testHuge.getY(), 10000);
		
		//SET NEW VALUE
		testNormal.setY(8);
		
		//TEST GET NEW VALUE
		assertEquals(testNormal.getY(), 8);
	}
	
	public void testToString() {
		assertEquals(testNormal.toString(),"5,10");
		assertEquals(testZero.toString(),"0,0");
		assertEquals(testNegative.toString(),"-5,-10");
		assertEquals(testHuge.toString(),"5000,10000");
	}
		
	public void testGetSetImageImage() {
		//DEFAULT IMAGE IS "PACMAN/pacmanimg.png"
		assertEquals(testNormal.getImage(), new ImageIcon("PACMAN/pacmanimg.png").getImage());
		
		//SET NEW IMAGE
		testNormal.setImage(testImage);
		assertEquals(testNormal.getImage(), testImage);
	}
	
	public void testGetSetImageString() {
		//DEFAULT IMAGE IS "PACMAN/bigpill.png"
		assertEquals(testNormal.getImage(), new ImageIcon("PACMAN/pacmanimg.png").getImage());
		
		//SET NEW IMAGE
		testNormal.setImage("PACMAN/smallpill.png");
		assertEquals(testNormal.getImage(), new ImageIcon("PACMAN/smallpill.png").getImage());
	}
	
	//PLAYER TESTS
	public void testSetGetDirection() {
		//DEFAULT WAS SET TO LEFT
		assertEquals(testNormal.getDirection(), Direction.LEFT);
		
		//SET DIRECTION TO UP
		testNormal.setDirection(Direction.UP);
		assertEquals(testNormal.getDirection(), Direction.UP);
		//SET DIRECTION TO RIGHT
		testNormal.setDirection(Direction.RIGHT);
		assertEquals(testNormal.getDirection(), Direction.RIGHT);
		//SET DIRECTION TO DOWN
		testNormal.setDirection(Direction.DOWN);
		assertEquals(testNormal.getDirection(), Direction.DOWN);
	}

	public void testSetGetNumOfLives() {
		//DEFAULT WAS SET TO 1
		assertEquals(testNormal.getNumOflives(), 1);
		
		//SET TO... 4
		testNormal.setNumOflives(4);
		assertEquals(testNormal.getNumOflives(), 4);
	}

	public void testUpdateLocation() { //this is a big one...
		//ATTEMPT TO MOVE INTO AN EMPTY SPACE OR A WALL
		testNormal.setDirection(Direction.LEFT);
		testNormal2.setDirection(Direction.LEFT);
		assertEquals(testNormal.updateLocation(map),true); //attempt to move first
		assertEquals(testNormal2.updateLocation(map),false); //attempt to move second
		
		testNormal.setDirection(Direction.DOWN);
		testNormal2.setDirection(Direction.DOWN);
		assertEquals(testNormal.updateLocation(map),true); //attempt to move first
		assertEquals(testNormal2.updateLocation(map),false); //attempt to move second
		
		testNormal.setDirection(Direction.RIGHT);
		testNormal2.setDirection(Direction.RIGHT);
		assertEquals(testNormal.updateLocation(map),true); //attempt to move first
		assertEquals(testNormal2.updateLocation(map),false); //attempt to move second
		
		testNormal.setDirection(Direction.UP);
		testNormal2.setDirection(Direction.UP);
		assertEquals(testNormal.updateLocation(map),true); //attempt to move first
		assertEquals(testNormal2.updateLocation(map),false); //attempt to move second
		
		//ATTEMPT TO MOVE OUT OF BOUNDS
		testZero.setDirection(Direction.LEFT);
		assertEquals(testZero.updateLocation(map),false);//move left out of bounds
		testZero.setDirection(Direction.DOWN);
		assertEquals(testZero.updateLocation(map),false);//move down out of bounds
		testNormal3.setDirection(Direction.RIGHT);
		assertEquals(testNormal3.updateLocation(map),false);//move right out of bounds
		testNormal3.setDirection(Direction.UP);
		assertEquals(testNormal3.updateLocation(map),false);//move up out of bounds
		
	}
	
	//PACMAN TESTS
	public void testSpawnAndDie() {
		//CHECK SPAWN LOCATION (9,9) IS EMPTY
		ArrayList<Mappable> tempMappable = map.getMappable(9,9); //assert the space is empty before the spawn
		assertEquals(tempMappable, null); //should be null if nothing was ever there
		
		//TEST DIE ON EMPTY LOCATION
		testNormal.die(map);
		assertEquals(tempMappable, null);
		
		//SPAWN TO EMPTY LOCATION
		testNormal.spawn(map);
		tempMappable = map.getMappable(9,9); //find the mappable where pacman should have been added
		boolean isInMappable = false;
		for(int i=0; i<tempMappable.size();i++) {
			if(tempMappable.get(i).equals(testNormal)) isInMappable = true; //flag true if Pacman is there
		}
		assertEquals(isInMappable, true);
		
		//TEST DIE ON LOCATION WITH PACMAN
		testNormal.die(map); //call die to remove the pacman
		assertEquals(tempMappable.size(), 0); //there will be an array there now
		
		//SPAWN ONTO A WALL	
		map.addWall(9,9);
		testNormal.spawn(map);
		tempMappable = map.getMappable(9,9); //find the mappable where pacman should have been added
		isInMappable = false;
		for(int i=0; i<tempMappable.size();i++) {
			if(tempMappable.get(i).equals(testNormal)) isInMappable = true; //flag true if Pacman is there
		}
		assertEquals(isInMappable, false);
	}
		
	public void testSetGetState() {
		//DEFAULT IS NORMAL
		assertEquals(testNormal.getState(),PacmanState.NORMAL);
		//SET TO BEASTMODE
		testNormal.setState(PacmanState.BEASTMODE);
		assertEquals(testNormal.getState(),PacmanState.BEASTMODE);
		//SET TO NORMAL
		testNormal.setState(PacmanState.NORMAL);
		assertEquals(testNormal.getState(),PacmanState.NORMAL);
	}

	public void testOnEventEatLittlePill() {
		//Spawn a Pacman and move it to the left onto a LittlePillItem
		testNormal.spawn(game.getMap());
		testNormal.setDirection(Direction.LEFT);
		testNormal.updateLocation(game.getMap());
		
		assertEquals(game.getScore(), 0);
		
		testNormal.onEvent(new GameEvent("movement", game));
		ArrayList<Mappable> tempMappable = game.getMap().getMappable(8,9);
		
		assertEquals(tempMappable.contains(new LittlePillItem(8,9)), false);
		assertEquals(game.getScore(), 1); //score needs to increment to 1 from 0
	}
	
	public void testOnEventEatBigPIll() {
		
	}
	
	public void testOnEventDieToGhost() {
		//spawn a ghost and a pacman at (8,9)... call onEvent(). pacman should be gone. ghost should exist
		Ghost tempGhost = new Ghost(8,9,Direction.LEFT,1);
		game.getMap().addMappable(tempGhost);
		Pacman tempPacman = new Pacman(8,9,Direction.LEFT,3);
		game.getMap().addMappable(tempPacman);
		
		ArrayList<Mappable> tempMappable = game.getMap().getMappable(8,9);
		assertEquals(tempMappable.contains(tempPacman), true); //there is a pacman and ghost, just added one above;
		assertEquals(tempMappable.contains(tempGhost), true);
		
		tempPacman.onEvent(new GameEvent("movement", game)); //on event... there should be no pacman now
		
		tempMappable = game.getMap().getMappable(8,9);
		assertEquals(tempMappable.contains(tempPacman), false);
		assertEquals(tempMappable.contains(tempGhost), true);
	}
	
	public void testOnEventKillGhost() {
		//spawn a ghost and a pacman at (8,9)... call onEvent(). ghost should be gone. pacman should exist
		Ghost tempGhost = new Ghost(8,9,Direction.LEFT,1);
		game.getMap().addMappable(tempGhost);
		Pacman tempPacman = new Pacman(8,9,Direction.LEFT,3);
		game.getMap().addMappable(tempPacman);
		
		ArrayList<Mappable> tempMappable = game.getMap().getMappable(8,9);
		assertEquals(tempMappable.contains(tempPacman), true); //there is a pacman and ghost, just added one above;
		assertEquals(tempMappable.contains(tempGhost), true);
		
		tempPacman.setState(PacmanState.BEASTMODE); //turn on BEASTMODE!!!
		tempPacman.onEvent(new GameEvent("movement", game)); //on event... there should be no ghost now
		
		tempMappable = game.getMap().getMappable(8,9);
		assertEquals(tempMappable.contains(tempPacman), true);
		assertEquals(tempMappable.contains(tempGhost), false);
	}
	
	public void testOnEvent() {

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
