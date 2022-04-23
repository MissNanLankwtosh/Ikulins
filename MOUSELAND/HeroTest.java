import java.awt.Image;
import java.util.ArrayList;

import javax.swing.ImageIcon;

import junit.framework.TestCase;

public class HeroTest extends TestCase {
	Hero testNormal, testNormal2,testNormal3, testZero, testNegative, testHuge;
	Image testImage;
	MouselandMap map;
	
	public void setUp(){
		testNormal = new Hero(5,10,Direction.LEFT,1);
		testNormal2 = new Hero(15,20,Direction.LEFT,1);
		testNormal3 = new Hero(50,50,Direction.LEFT,1);
		testZero = new Hero(0,0,Direction.LEFT,2);
		testNegative = new Hero(-5,-10,Direction.LEFT,3);
		testHuge = new Hero(5000,10000,Direction.LEFT,4);
		
		testImage = new ImageIcon("MOUSELAND/mouse.png").getImage();
		
		map = new MouselandMap(50,50);
			//surround testNormal2 with walls so it can't move
			map.addWall(15,21);
			map.addWall(15,19);
			map.addWall(14,20);
			map.addWall(16,20);
		
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
		//DEFAULT IMAGE IS "MOUSELAND/person.png"
		assertEquals(testNormal.getImage(), new ImageIcon("MOUSELAND/person.png").getImage());
		
		//SET NEW IMAGE
		testNormal.setImage(testImage);
		assertEquals(testNormal.getImage(), testImage);
	}
	
	public void testGetSetImageString() {
		assertEquals(testNormal.getImage(), new ImageIcon("MOUSELAND/person.png").getImage());
		
		//SET NEW IMAGE
		testNormal.setImage("MOUSELAND/mouse.png");
		assertEquals(testNormal.getImage(), new ImageIcon("MOUSELAND/mouse.png").getImage());
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
	
	//HERO TESTS
	public void testSpawnAndDie() {
		//CHECK SPAWN LOCATION (9,9) IS EMPTY
		ArrayList<Mappable> tempMappable = map.getMappable(9,9); //assert the space is empty before the spawn
		assertEquals(tempMappable, null); //should be null if nothing was ever there
		
		//TEST DIE ON EMPTY LOCATION
		testNormal.die(map);
		assertEquals(tempMappable, null);
		
		//SPAWN TO EMPTY LOCATION
		testNormal.spawn(map);
		tempMappable = map.getMappable(9,9); //find the mappable where hero should have been added
		boolean isInMappable = false;
		for(int i=0; i<tempMappable.size();i++) {
			if(tempMappable.get(i).equals(testNormal)) isInMappable = true; //flag true if hero is there
		}
		assertEquals(isInMappable, true);
		
		//SPAWN ONTO A WALL	
		map.addWall(9,9);
		testNormal.spawn(map);
		tempMappable = map.getMappable(9,9); //find the mappable where hero should have been added
		isInMappable = false;
		for(int i=0; i<tempMappable.size();i++) {
			if(tempMappable.get(i).equals(testNormal)) isInMappable = true; //flag true if hero is there
		}
		assertEquals(isInMappable, false);
	}
	
	//TOADD: add collision tests
}
