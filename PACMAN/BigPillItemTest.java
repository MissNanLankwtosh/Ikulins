import java.awt.Image;

import javax.swing.ImageIcon;


import junit.framework.TestCase;

/**
 * 
 * @author Alexander Clelland
 *
 */
public class BigPillItemTest extends TestCase {
	BigPillItem testNormal, testZero, testNegative, testHuge;
	Image testImage;

	public BigPillItemTest(String arg0) {
		super(arg0);
	}

	protected void setUp() throws Exception {
		testNormal = new BigPillItem(5,10);
		testZero = new BigPillItem(0,0);
		testNegative = new BigPillItem(-5,-10);
		testHuge = new BigPillItem(5000,10000);
		
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
		//DEFAULT IMAGE IS "PACMAN/bigpill.png"
		assertEquals(testNormal.getImage(), new ImageIcon("PACMAN/bigpill.png").getImage());
		
		//SET NEW IMAGE
		testNormal.setImage(testImage);
		assertEquals(testNormal.getImage(), testImage);
	}
	
	public void testGetSetImageString() {
		//DEFAULT IMAGE IS "PACMAN/bigpill.png"
		assertEquals(testNormal.getImage(), new ImageIcon("PACMAN/bigpill.png").getImage());
		
		//SET NEW IMAGE ("PACMAN/littlepill.png" because it exists)
		testNormal.setImage("PACMAN/smallpill.png");
		assertEquals(testNormal.getImage(), new ImageIcon("PACMAN/smallpill.png").getImage());
	}
	
	//ITEM TESTS
	
	//BIGPILLITEM TESTS
	
	
	
	
	
	
}
