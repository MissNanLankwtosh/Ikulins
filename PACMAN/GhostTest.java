

import java.awt.Image;

import javax.swing.ImageIcon;

import junit.framework.TestCase;


public class GhostTest extends TestCase {
	Ghost testNormal, testNormal2,testNormal3, testZero, testNegative, testHuge;
	Image testImage;
	PacmanMap map;

	public GhostTest(String arg0) {
		super(arg0);
	}

	protected void setUp() throws Exception {
		super.setUp();
		
		testNormal = new Ghost(5,10,Direction.LEFT,1);
		testNormal2 = new Ghost(15,20,Direction.LEFT,1);
		testNormal3 = new Ghost(50,50,Direction.LEFT,1);
		testZero = new Ghost(0,0,Direction.LEFT,2);
		testNegative = new Ghost(-5,-10,Direction.LEFT,3);
		testHuge = new Ghost(5000,10000,Direction.LEFT,4);
		
		testImage = new ImageIcon("PACMAN/pacmanimg.png").getImage();
		
		map = new PacmanMap(50,50);
			//surround testNormal2 with walls so it can't move
			map.addWall(15,21);
			map.addWall(15,19);
			map.addWall(14,20);
			map.addWall(16,20);
		
		
	}

	protected void tearDown() throws Exception {
		super.tearDown();
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
		//DEFAULT IMAGE IS "PACMAN/ghostimg[numOfLives].png"
		assertEquals(testNormal.getImage(), new ImageIcon("PACMAN/ghostimg1.png").getImage());
		assertEquals(testZero.getImage(), new ImageIcon("PACMAN/ghostimg2.png").getImage());
		assertEquals(testNegative.getImage(), new ImageIcon("PACMAN/ghostimg3.png").getImage());
		assertEquals(testHuge.getImage(), new ImageIcon("PACMAN/ghostimg4.png").getImage());
		
		//SET NEW IMAGE
		testNormal.setImage(testImage);
		assertEquals(testNormal.getImage(), testImage);
	}
	
	public void testGetSetImageString() {
		//DEFAULT IMAGE IS "PACMAN/bigpill.png"
		assertEquals(testNormal.getImage(), new ImageIcon("PACMAN/ghostimg1.png").getImage());
		assertEquals(testZero.getImage(), new ImageIcon("PACMAN/ghostimg2.png").getImage());
		assertEquals(testNegative.getImage(), new ImageIcon("PACMAN/ghostimg3.png").getImage());
		assertEquals(testHuge.getImage(), new ImageIcon("PACMAN/ghostimg4.png").getImage());
		
		//SET NEW IMAGE ("PACMAN/littlepill.png" because it exists)
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
	
	//GHOST TESTS
	
	
	
	
	
	
	
	
	
	
}
