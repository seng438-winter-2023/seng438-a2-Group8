package org.jfree.data.test;
import org.junit.*;

import static org.junit.Assert.*;
import junit.framework.TestCase;

import org.jfree.data.DataUtilities;
import org.junit.Test;

import org.jmock.Mockery;
import org.jmock.Expectations;

import org.jfree.data.Values2D;
import org.jfree.data.KeyedValues;
import org.jfree.data.DefaultKeyedValues;

import java.util.Arrays;
import java.util.List;

public class DataUtilitiesTest{
	Mockery mockingContext;
	Values2D values;

	//set up mockcontext
	@Before
	public void setUp() {
		mockingContext = new Mockery();
		values = mockingContext.mock(Values2D.class);
		
	}
	
	//calculateColumnTotalForTwoValues: test case of valid input. Value2d column 0 contains 7.5 and 2.5 
	//so should return 10.0.
	@Test
	public void calculateColumnTotalForTwoValues() {
		mockingContext.checking(new Expectations() {
	        {
	            one(values).getRowCount();
	            will(returnValue(2));
	            one(values).getValue(0, 0);
	            will(returnValue(7.5));
	            one(values).getValue(1, 0);
	            will(returnValue(2.5));
	        }
	    });
	    double result =  DataUtilities.calculateColumnTotal(values, 0);
	    assertEquals(result, 10.0, .000000001d);    
	}
	
	//calculateColumnTotalForMultiplePosNegValues: test case of valid input. Value2d column contains -1, -2, -3.2, 4.55, -4.3 
	//so should return 5.95.
	@Test
	public void calculateColumnTotalForMultiplePosNegValues() {
		mockingContext.checking(new Expectations() {
	        {
	            one(values).getRowCount();
	            will(returnValue(5));
	            one(values).getValue(0, 0);
	            will(returnValue(-1.0));
	            one(values).getValue(1, 0);
	            will(returnValue(-2.0));
	            one(values).getValue(2, 0);
	            will(returnValue(-3.2));
	            one(values).getValue(3, 0);
	            will(returnValue(4.55));
	            one(values).getValue(4, 0);
	            will(returnValue(-4.3));
	        }
	    });
		
	    double result =  DataUtilities.calculateColumnTotal(values, 0);
	    assertEquals(result, -5.95, .000000001d);
	}
	
	//calculateColumnTotalForEmpty: test invalid input. There are no columns so should return 0.0
	@Test
	public void calculateColumnTotalForEmpty() {
		mockingContext.checking(new Expectations() {
	        {
	            one(values).getRowCount();
	            will(returnValue(0));
	        }
	    });
		 double result =  DataUtilities.calculateColumnTotal(values, 0);
		   assertEquals(result, 0.0, .000000001d);
	}
	
	//calculateRowTotalForTwoValues: test case of valid input. Value2d row 0 contains 7.5 and 2.5 
	//so should return 10.0
		@Test
		public void calculateRowTotalForTwoValues() {
			mockingContext.checking(new Expectations() {
		        {
		            one(values).getColumnCount();
		            will(returnValue(2));
		            one(values).getValue(0, 0);
		            will(returnValue(7.5));
		            one(values).getValue(0, 1);
		            will(returnValue(2.5));
		        }
		    });
		    double result =  DataUtilities.calculateRowTotal(values, 0);
		    assertEquals(result, 10.0, .000000001d);    
		}
		
		//calculateRowTotalForMultiplePosNegValues: test case of valid input. Value2d row contains -1, -2, -3.2, 4.55, -4.3 
		//so should return -5.95
		@Test
		public void calculateRowTotalForMultiplePosNegValues() {
			mockingContext.checking(new Expectations() {
		        {
		            one(values).getColumnCount();
		            will(returnValue(5));
		            one(values).getValue(0, 0);
		            will(returnValue(-1.0));
		            one(values).getValue(0, 1);
		            will(returnValue(-2.0));
		            one(values).getValue(0, 2);
		            will(returnValue(-3.2));
		            one(values).getValue(0, 3);
		            will(returnValue(4.55));
		            one(values).getValue(0, 4);
		            will(returnValue(-4.3));
		        }
		    });
			
		    double result =  DataUtilities.calculateRowTotal(values, 0);
		    assertEquals(result, -5.95, .000000001d);
		}
		
		//calculateColumnTotalForEmpty: test invalid input. There are no columns so should return 0.0
		@Test
		public void calculateRowTotalForEmpty() {
			mockingContext.checking(new Expectations() {
		        {
		            one(values).getColumnCount();
		            will(returnValue(0));
		        }
		    });
			 double result =  DataUtilities.calculateRowTotal(values, 0);
			 
			 assertEquals(result, 0.0, .000000001d);
		}
		
		//createNumberArrayWithInvalidData: invalid (null) data should throw an InvalidParameterException.
		@Test (expected = java.security.InvalidParameterException.class)
		  public void createNumberArrayWithInvalidData() throws java.security.InvalidParameterException {
		      DataUtilities.createNumberArray(null);
		  }
		  
			//createNumberArrayWithEmptyData: Valid and empty data should return an empty Number[].
		  @Test
		  public void createNumberArrayWithEmptyData() {
			  double[] data = {};
		      Number[] actual = DataUtilities.createNumberArray(data);
		      Number[] expected = {};
		      assertArrayEquals("Number array from empty data should be empty", expected, actual);
		  }

		  //createNumberArrayWithPositveData: Input data contains {1.1, 2.2, 3.3, 4.4, 5.5}
		  //so it should return the same array as a Number[].
		  @Test
		  public void createNumberArrayWithPositiveData() {
			  double[] data = {1.1, 2.2, 3.3, 4.4, 5.5};
		      Number[] actual = DataUtilities.createNumberArray(data);
		      Number[] expected = {1.1, 2.2, 3.3, 4.4, 5.5};
		      assertArrayEquals(expected, actual);
		  }
		  
		  //createNumberArrayWithMixedData: Input data contains {1.1, -2.2, 3.3, 4.4, -5.5} 
		  //so it should return the same array as a Number[].
		  @Test
		  public void createNumberArrayWithMixedData() {
			  double[] data = {1.1, -2.2, 3.3, 4.4, -5.5};
		      Number[] actual = DataUtilities.createNumberArray(data);
		      Number[] expected = {1.1, -2.2, 3.3, 4.4, -5.5};
		      assertArrayEquals(expected, actual);
		  }
		  
		  //createNumberArray2DWithInvalidData: invalid (null) data should throw an InvalidParameterException.
		  @Test (expected = java.security.InvalidParameterException.class)
		  public void createNumberArray2DWithInvalidData() throws java.security.InvalidParameterException {
		      DataUtilities.createNumberArray2D(null);
		  }
		  
		  //createNumberArray2DWithEmptyData: valid and empty data should return an empty Number[][].
		  @Test
		  public void createNumberArray2DWithEmptyData() {
			  double[] data = {};
		      Number[] actual = DataUtilities.createNumberArray(data);
		      Number[] expected = {};
		      assertArrayEquals("2D Number array from empty data should be empty",expected, actual);
		  }
		 
		  //createNumberArray2DWithPositiveData: Input data contains {
		  //{1.1, 2.2, 3.3},
		  //{4.4, 5.5, 6.6},
		   //{7.7, 8.8, 9.9}
		  //}
		  //so it should return the same array as a Number[][].

		  @Test
		  public void createNumberArray2DWithPositiveData() {
			  double[][] data = {
					  {1.1, 2.2, 3.3},
					  {4.4, 5.5, 6.6},
					  {7.7, 8.8, 9.9},
					  };
		      Number[][] actual = DataUtilities.createNumberArray2D(data);
		      Number[][] expected = {
					  {1.1, 2.2, 3.3},
					  {4.4, 5.5, 6.6},
					  {7.7, 8.8, 9.9},
					  };
		      assertArrayEquals("Valid double[][] data should be converted to Number[][]",expected, actual);
		  }
		  
//		  createNumberArray2DWithMixedData: Input data contains {
//			  {-1.1, 2.2, 3.3},
//			  {4.4, -5.5, 6.6},
//			   {7.7, 8.8, -9.9}
//			  }
//			   so it should return the same array as a Number[][].

		  @Test
		  public void createNumberArray2DWithMixedData() {
			  double[][] data = {
					  {-1.1, 2.2, 3.3},
					  {4.4, -5.5, 6.6},
					  {7.7, 8.8, -9.9},
					  };
		      Number[][] actual = DataUtilities.createNumberArray2D(data);
		      Number[][] expected = {
					  {-1.1, 2.2, 3.3},
					  {4.4, -5.5, 6.6},
					  {7.7, 8.8, -9.9},
					  };
		      assertArrayEquals("Valid double[][] data should be converted to Number[][]",expected, actual);
		  }
		  
		  //getCumulativePercentagesForInvalidData: invalid (null) data should throw an InvalidParameterException.
		  @Test (expected = java.security.InvalidParameterException.class)
		  public void getCumulativePercentagesForInvalidData() throws java.security.InvalidParameterException {
		      DataUtilities.getCumulativePercentages(null);
		  }
		  
		  //getCumulativePercentagesForEmptyData: valid and empty data should return an empty KeyedValues.
			@Test
			public void getCumulativePercentagesForEmptyData() {
			    Mockery mockingContext = new Mockery();
			    final KeyedValues values = mockingContext.mock(KeyedValues.class);
			    mockingContext.checking(new Expectations() {{
		            atLeast(1).of(values).getItemCount();
		            will(returnValue(0));
			    }});
			    
			    // exercise
			    KeyedValues result = DataUtilities.getCumulativePercentages(values);
			    
			    // verify
			    assertEquals("Empty KeyedValues should produce empty cumulative percentages", 0, result.getItemCount());
			}
		  
			//getCumulativePercentagesForNonEmptyData: input KeyedValues contains key-value pairs [0: 5, 1: 9, 2: 2] 
			//so the returned KeyedValues should have pairs [0: 0.3125, 1: 0.875, 2: 1.0].
			@Test
			public void getCumulativePercentagesForNonEmptyData() {
			    Mockery mockingContext = new Mockery();
			    final KeyedValues values = mockingContext.mock(KeyedValues.class);
			    mockingContext.checking(new Expectations() {
			        {
		              atLeast(1).of(values).getItemCount();
		              will(returnValue(3));
		              
		              atLeast(1).of(values).getKey(0);
		              will(returnValue(0));
		              atLeast(1).of(values).getValue(0);
		              will(returnValue(5));
		              
		              atLeast(1).of(values).getKey(1);
		              will(returnValue(1));
		              atLeast(1).of(values).getValue(1);
		              will(returnValue(9));
		              
		              atLeast(1).of(values).getKey(2);
		              will(returnValue(2));
		              atLeast(1).of(values).getValue(2);
		              will(returnValue(2));
			        }
			    });
			    
			    // exercise
			    KeyedValues result = DataUtilities.getCumulativePercentages(values);
			    
			    // verify
			    assertEquals("Input with 3 values should return result with 3 values", 3, result.getItemCount());
			    assertEquals("Input with values [0: 5, 1: 9, 2: 2],  should return result with 0: 0.3125", 0.3125, result.getValue(0));
			    assertEquals("Input with values [0: 5, 1: 9, 2: 2],  should return result with 1: 0.875",0.875, result.getValue(1));
			    assertEquals("Input with values [0: 5, 1: 9, 2: 2],  should return result with 2: 1.0", 1.0, result.getValue(2));
			}

}