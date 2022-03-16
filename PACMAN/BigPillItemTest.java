import junit.framework.TestCase;


public class BigPillItemTest extends TestCase {
	BigPillItem testBigPillNorm, testBigPillZero, testBigPillNeg, testBigPillHuge;

	public BigPillItemTest(String arg0) {
		super(arg0);
	}

	protected void setUp() throws Exception {
		testBigPillNorm = new BigPillItem(5,10);
		testBigPillZero = new BigPillItem(0,0);
		testBigPillNeg = new BigPillItem(-5,-10);
		testBigPillHuge = new BigPillItem(5000,10000);
		
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	
	
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
	
	
	
	
	
	
	
	
	
	
	
	
}
