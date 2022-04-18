import java.awt.Image;

import javax.swing.ImageIcon;

import junit.framework.TestCase;

public class TrapItemTest extends TestCase {
	TrapItem testNormal, testZero, testNegative, testHuge;
	Image testImage;

	public TrapItemTest(String arg0) {
		super(arg0);
	}

	protected void setUp() throws Exception {
		testNormal = new TrapItem(5,10);
		testZero = new TrapItem(0,0);
		testNegative = new TrapItem(-5,-10);
		testHuge = new TrapItem(5000,10000);
		
		testImage = new ImageIcon("PACMAN/smallpill.png").getImage();
		
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
		assertEquals(testNormal.getImage(), new ImageIcon("MOUSELAND/mousetrap.png").getImage());
		
		//SET NEW IMAGE
		testNormal.setImage(testImage);
		assertEquals(testNormal.getImage(), testImage);
	}
	
	public void testGetSetImageString() {
		assertEquals(testNormal.getImage(), new ImageIcon("MOUSELAND/mousetrap").getImage());
		
		testNormal.setImage("MOUSELAND/ladder.png");
		assertEquals(testNormal.getImage(), new ImageIcon("MOUSELAND/ladder.png").getImage());
	}
	
	
	
	
	
	
}
