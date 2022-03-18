import java.awt.Image;

import javax.swing.ImageIcon;


import junit.framework.TestCase;


public class BigPillItemTest extends TestCase {
	BigPillItem testBigPillNorm, testBigPillZero, testBigPillNeg, testBigPillHuge;
	Image testImage;

	public BigPillItemTest(String arg0) {
		super(arg0);
	}

	protected void setUp() throws Exception {
		testBigPillNorm = new BigPillItem(5,10);
		testBigPillZero = new BigPillItem(0,0);
		testBigPillNeg = new BigPillItem(-5,-10);
		testBigPillHuge = new BigPillItem(5000,10000);
		
		Image testImage = new ImageIcon("PACMAN/smallpill.png").getImage();
		
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	
	//MAPPABLE TESTS
	public void testGetSetX() {
		//TEST GET INITIAL VALUES
		assertEquals(testBigPillNorm.getX(), 5);
		assertEquals(testBigPillZero.getX(), 0);
		assertEquals(testBigPillNeg.getX(), -5);
		assertEquals(testBigPillHuge.getX(), 5000);
		
		//SET NEW VALUE
		testBigPillNorm.setX(8);
		
		//TEST GET NEW VALUE
		assertEquals(testBigPillNorm.getX(), 8);
	}
	
	public void testGetSetY() {
		//TEST GET INITIAL VALUES
		assertEquals(testBigPillNorm.getY(), 10);
		assertEquals(testBigPillZero.getY(), 0);
		assertEquals(testBigPillNeg.getY(), -10);
		assertEquals(testBigPillHuge.getY(), 10000);
		
		//SET NEW VALUE
		testBigPillNorm.setY(8);
		
		//TEST GET NEW VALUE
		assertEquals(testBigPillNorm.getY(), 8);
	}
	
	public void testToString() {
		assertEquals(testBigPillNorm.toString(),"5,10");
		assertEquals(testBigPillZero.toString(),"0,0");
		assertEquals(testBigPillNeg.toString(),"-5,-10");
		assertEquals(testBigPillHuge.toString(),"5000,10000");
	}
		
	public void testGetSetImageImage() {
		//DEFAULT IMAGE IS "PACMAN/bigpill.png"
		assertEquals(testBigPillNorm.getImage(), new ImageIcon("PACMAN/bigpill.png").getImage());
		
		//SET NEW IMAGE
		testBigPillNorm.setImage(testImage);
		assertEquals(testBigPillNorm.getImage(), testImage);
	}
	public void testGetSetImageString() {
		//DEFAULT IMAGE IS "PACMAN/bigpill.png"
		assertEquals(testBigPillNorm.getImage(), new ImageIcon("PACMAN/bigpill.png").getImage());
		
		//SET NEW IMAGE ("PACMAN/littlepill.png" because it exists)
		testBigPillNorm.setImage("PACMAN/smallpill.png");
		assertEquals(testBigPillNorm.getImage(), testImage);
	}
	
	
	
	
	
	
	
	
}
