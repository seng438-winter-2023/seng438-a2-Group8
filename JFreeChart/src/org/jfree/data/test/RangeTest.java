package org.jfree.data.test;

import static org.junit.Assert.*; import org.jfree.data.Range; import org.junit.*;

public class RangeTest {

    private Range exampleRange;
    @BeforeClass public static void setUpBeforeClass() throws Exception {
    }


    @Before
    public void setUp() throws Exception { exampleRange = new Range(-1, 1);
    }
    

    @Test
    public void centralValueShouldBeZero() {
        assertEquals("The central value of -1 and 1 should be 0",
        0, exampleRange.getCentralValue(), .000000001d);
    }
    
    
    /** CONSTRAIN TESTS **/
    /* ---------------------------------------------------------------------------------------------------------- */
    
    // Input 1.5 should return 1.5
    @Test
    public void testConstrain_lowerEqualToBoundTest() {
    	Range a1 = new Range(1.5, 3.8);
    	
    	assertEquals("The constrain value should return 1.5", 1.5, a1.constrain(1.5), .000000001d);
    }
    
    // Input 2.0 should return 2.0
    @Test
    public void testConstrain_withinBoundaryTest() {
    	Range a1 = new Range(1.5, 3.8);
    	
    	assertEquals("The constrain value should return 2.0", 2.0, a1.constrain(2.0), .000000001d);
    	assertEquals("The constrain value should return 2.66", 2.66, a1.constrain(2.66), .000000001d);
    }
    
    // Input 7.0 should return 3.8
    @Test
    public void testConstrain_upperBoundaryTest() {
    	Range a1 = new Range(1.5, 3.8);
    	
    	assertEquals("The constrain value should return 3.8", 3.8, a1.constrain(7), .000000001d);
    	assertEquals("The constrain value should return 3.8", 3.8, a1.constrain(999999), .000000001d);
    }
    
    // Input of 0 should return 1.5
    @Test
    public void testConstrain_belowBoundaryTest() {
    	Range a1 = new Range(1.5, 3.8);
    	
    	assertEquals("The constrain value should return 1.5", 1.5, a1.constrain(0), .000000001d);
    	assertEquals("The constrain value should return 1.5", 1.5, a1.constrain(-100), .000000001d);
    }
    
    // Input 3.8 should return 3.8
    @Test
    public void testConstrain_upperEqualToBoundTest() {
    	Range a1 = new Range(1.5, 3.8);
    	
    	assertEquals("The constrain value should return 3.8", 3.8, a1.constrain(3.8), .000000001d);
    }
    
    /** CONTAIN TESTS **/
    /* ---------------------------------------------------------------------------------------------------------- */
    // Input of 0 should return false
    @Test
    public void testContains_belowBoundaryTest() {
    	Range a1 = new Range(1.5, 3.8);
    	
    	assertEquals("The contained value should return false, 0 is not within (1.5, 3.8)", false, a1.contains(0));
    }
    
    // Input 3.79999 should return true
    @Test
    public void testContains_withinBoundaryTest() {
    	Range a1 = new Range(1.5, 3.8);
    	
    	assertEquals("The contained value should return true, 3.79999 is within (1.5, 3.8)", true, a1.contains(3.79999));
    }

    // Input 10 should return false
    @Test
    public void testContains_upperBoundaryTest() {
    	Range a1 = new Range(1.5, 3.8);
    	
    	assertEquals("The contained value should return true, 10 is within (1.5, 3.8)", false, a1.contains(10));
    }

    // Input 1.5 should return true
    @Test
    public void testContains_lowerEqualToBoundTest() {
    	Range a1 = new Range(1.5, 3.8);
    	
    	assertEquals("The contained value should return true, 1.5 is within (1.5, 3.8)", true, a1.contains(1.5));

    }

    // Input 3.8 should return true
    @Test
    public void testContains_upperEqualToBoundTest() {
    	Range a1 = new Range(1.5, 3.8);
    	
    	assertEquals("The contained value should return true, 3.8 is within (1.5, 3.8)", true, a1.contains(3.8));
    }

    // Input -999999 should return false
    @Test
    public void testContains_extremeBoundaryLowerTest() {
    	Range a1 = new Range(1.5, 3.8);
    	
    	assertEquals("The contained value should return false, -999999 is not within (1.5, 3.8)", false, a1.contains(-999999));
    }

    // Input 999999 should return false
    @Test
    public void testContains_extremeBoundaryUpperTest() {
    	Range a1 = new Range(1.5, 3.8);
    	
    	assertEquals("The contained value should return false, 999999 is not within (1.5, 3.8)", false, a1.contains(999999));
    }


    /** GET LENGTH TESTS **/
    /* ---------------------------------------------------------------------------------------------------------- */
    
    // Input (1, 10) should return 9
    @Test
    public void testGetLength_bothValuesPositiveInt() {
    	Range a1 = new Range(1, 10);
    	
    	assertEquals("The length of (1, 10) should be 9", 9, a1.getLength(), .000000001d);
    }
    
    // Input (-10, 10) should return 20
    @Test
    public void testGetLength_oneValueNegativeInt() {
    	Range a2 = new Range(-10, 10);
    	
    	assertEquals("The length of (-10, 10) should be 20", 20, a2.getLength(), .000000001d);
    }
    
    // Input (1.5, 3.8) should return 2.3
    @Test
    public void testGetLength_bothValuesPositiveDouble() {
    	Range a3 = new Range(1.5, 3.8);
    	
    	assertEquals("The length of (1.5, 3.8) should be 2.3", 2.3, a3.getLength(), .000000001d);
    }
    
    // Input (-1.5,4.5) should return 6
    @Test
    public void testGetLength_oneValueNegativeDouble() {
    	Range a4 = new Range(-1.5, 4.5);
    	
    	assertEquals("The length of (-1.5, 4.5) should be 6", 6, a4.getLength(), .000000001d);
    }
    
    
    // Input (0, 0) should return 0
    @Test
    public void testGetLength_bothValueSame() {
    	Range a5 = new Range(0, 0);

    	assertEquals("The length of (0, 0) should be 0", 0, a5.getLength(), .000000001d);
    }
   
    
    /** GET UPPER BOUND TEST **/
    /* ---------------------------------------------------------------------------------------------------------- */
    
    // Input (1, 3) should return 3
    @Test
    public void testGetUpperBound_positiveInt() {
    	Range a1 = new Range(1, 3);
    	
    	assertEquals("The upper bound of (1, 3) should be 3", 3, a1.getUpperBound(), .000000001d);
    }
    
    // Input (-100, -10) should return -10
    @Test
    public void testGetUpperBound_negativeInt() {
    	Range a2 = new Range(-100, -10);
    	
    	assertEquals("The upper bound of (-100, -10) should be -10", -10, a2.getUpperBound(), .000000001d);
    }
    
    // Input (1.5, 3.8) should return 3.8
    @Test
    public void testGetUpperBound_positiveDouble() {
    	Range a3 = new Range(1.5, 3.8);
    	
    	assertEquals("The upper bound of (1.5, 3.8) should be 3.8", 3.8, a3.getUpperBound(), .000000001d);
    }
    
    // Input (-1.5, -0.3) should return -0.3
    @Test
    public void testGetUpperBound_negativeDouble() {
    	Range a4 = new Range(-1.5, -0.3);
    	
    	assertEquals("The upper bound of (-1.5, -0.3) should be -0.3", -0.3, a4.getUpperBound(), .000000001d);
    }
    
    // Input (0, 0) should return 0
    @Test
    public void testGetUpperBound_zero() {
    	Range a5 = new Range(0, 0);
    	
    	assertEquals("The upper bound of (0, 0) should be 0", 0, a5.getUpperBound(), .000000001d);
    }
    
    /** GET LOWER BOUND TESTS **/
    /* ---------------------------------------------------------------------------------------------------------- */
    
    // Input (1, 3) should return 1
    @Test
    public void testGetLowerBound_positiveInt() {
    	Range a1 = new Range(1, 3);
    	
    	assertEquals("The lower bound of (1, 3) should be 1", 1, a1.getLowerBound(), .000000001d);
    }

    // Input (-100, -10) should return -100
    @Test
    public void testGetLowerBound_negativeInt() {
    	Range a2 = new Range(-100, -10);
    	
    	assertEquals("The lower bound of (-100, -10) should be -100", -100, a2.getLowerBound(), .000000001d);
    }
    
    
    // Input (1.5, 3.8) should return 1.5
    @Test
    public void testGetLowerBound_positiveDouble() {
    	Range a3 = new Range(1.5, 3.8);

    	assertEquals("The lower bound of (1.5, 3.8) should be 1.5", 1.5, a3.getLowerBound(), .000000001d);
    }
    
    // Input (-1.5, -0.3) should return -1.5
    @Test
    public void testGetLowerBound_negativeDouble() {
    	Range a4 = new Range(-1.5, -0.3);
    	
    	assertEquals("The lower bound of (-1.5, -0.3) should be -1.5", -1.5, a4.getLowerBound(), .000000001d);
    }
    
    // Input (0, 0) should return 0
    @Test
    public void testGetLowerBound_zero() {
    	Range a5 = new Range(0, 0);

    	assertEquals("The lower bound of (0, 0) should be 0", 0, a5.getLowerBound(), .000000001d);
    }


    @After
    public void tearDown() throws Exception {
    }

    @AfterClass
    public static void tearDownAfterClass() throws Exception {
    }

}
