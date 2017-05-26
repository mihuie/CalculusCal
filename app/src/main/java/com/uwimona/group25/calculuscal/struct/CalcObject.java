/**
 * 
 */
package com.uwimona.group25.calculuscal.struct;


public interface CalcObject extends Comparable<CalcObject> {
	/**
	 * Constants that identify a hierarchy (used as part of Comparable)
	 */
    int DOUBLE 		= 0x01;
	int INTEGER 	= 0x02;
	int FRACTION 	= 0x04;
	int SYMBOL	 	= 0x08;
	int VECTOR 		= 0x10;
	int MATRIX 		= 0x20;
	int FUNCTION 	= 0x40;

	/**
	 * Evaluate the object if possible. If not, throw general exception
	 * @return evaluation result as another CalcObject
	 * @throws Exception
	 */
    CalcObject evaluate() throws Exception;
	
	/**
	 * Convert the object into a string. Overrides the toString method in java.lang.Object
	 * @return the string
	 */
    String toString();
	
	@Override
    boolean equals(Object obj);
	
	@Override
    int compareTo(CalcObject obj);
	
	/**
	 * 
	 * @return true if the object represents a numeric. False otherwise.
	 */
    boolean isNumber();
	
	/**
	 * 
	 * @return the header symbol of this object (the object's "name")
	 */
    CalcSymbol getHeader();
	
	/**
	 * 
	 * @return the object's hierarchy constant. Used in compareTo(CalcObject obj)
	 * @see Comparable 
	 */
    int getHierarchy();
	
	/**
	 * 
	 * @return the precedence of the object (used at evaluation time)
	 * The higher the return value, the higher the precedence. There is no
	 * set range on these values. A simple [<,>,==] check will be used.
	 */
    int getPrecedence();
}
