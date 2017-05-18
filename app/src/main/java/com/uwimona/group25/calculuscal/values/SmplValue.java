/*
 * Adapted from FnPlot
 */

package com.uwimona.group25.calculuscal.values;

import com.uwimona.group25.calculuscal.sys.SmplException;
import java.util.*;
import java.io.*;

public abstract class SmplValue<T extends SmplValue<T>> {

	private static final long serialVersionID = 1L;

	// integers
	public static SmplInt make(Integer v) {
		return new SmplInt(v);
	}

	// real numbers
	public static SmplReal make(Double v) {
		return new SmplReal(v);
	}

	// booleans
	public static SmplBool make(boolean v) {
		return new SmplBool(v);
	}

	public static SmplChar make(char v){
		return new SmplChar(v);
	}

	

	public static SmplChar makeChar(String v){
		if(v.length()==4){
			return new SmplChar(Integer.parseInt(v, 16));
		} else if(v.equals("\\n")){
			return new SmplChar('\n');
		} else if(v.equals("\\t")){
			return new SmplChar('\t');
		} else if(v.equals("\\\\")){
			return new SmplChar('\\');
		} else if(v.equals("\\\"")){
			return new SmplChar('\"');
		} else {
			return new SmplChar(v.charAt(0));
		}
	}

	public static SmplString makeStr(String v){
		return new SmplString(v);
	}

	public static SmplString makeStrEscaped(String v){
		return new SmplString(SmplString.escape(v));
	}
/*
	public static SmplPair makePair(SmplValue<?> val1, SmplList val2){
			return new SmplList(val1, (SmplList)val2);

	}

	public static SmplPair makePair(SmplValue<?> val1, SmplPair val2){
			return new SmplList(val1, (SmplList)val2);
	}
*/
	public static SmplPair makePair(SmplValue<?> val1, SmplValue<?> val2){
			return new SmplPair(val1, val2);
	}

	

	public static SmplList makeList(SmplValue<?> val1, SmplList val2){
		return new SmplList(val1, val2);
	}

	public static SmplList makeList(ArrayList<SmplValue<?>> v){

		if(v.size() > 0){
			return new SmplList(v.remove(0), makeList(v));
		} else {
			return new SmplEmptyList();
		}

	}

	public static SmplVector makeVector(ArrayList<SmplValue<?>> v){
		return new SmplVector(v);
	}

	public static SmplSubVector makeSubVector(SmplInt size, SmplProcedure proc){
		return new SmplSubVector(size,proc);
	}

	/*
	*	 Tokenizes a string to form a quadratic equation
	*/
	public static SmplQuadratic makeQuadratic(String q){
		return new SmplQuadratic(q);
	}



	// return the type of a value
	public abstract SmplType getType();



	public boolean isInteger() {
        return getType() == SmplType.INTEGER;
    }


	// arithmetic operations

	public SmplValue<?> add(SmplValue<?> arg) throws SmplException {
		throw new TypeSmplException("Cannot add non-numeric values");
	}

	public SmplValue<?> sub(SmplValue<?> arg) throws SmplException {
		throw new TypeSmplException("Cannot subtract non-numeric values");
	}

	public SmplValue<?> mul(SmplValue<?> arg) throws SmplException {
		throw new TypeSmplException("Cannot multiply non-numeric values");
	}

	public SmplValue<?> div(SmplValue<?> arg) throws SmplException {
		throw new TypeSmplException("Cannot divide non-numeric values");
	}

	public SmplValue<?> mod(SmplValue<?> arg) throws SmplException {
		throw new TypeSmplException("Cannot perform modulo on non-numeric values");
	}

	public SmplValue<?> pow(SmplValue<?> arg) throws SmplException {
		throw new TypeSmplException("Cannot rise powers of non-numeric values");
	}

	public SmplValue<?> eq(SmplValue<?> arg) throws SmplException {
		throw new TypeSmplException("Operator not applicable: =");
	}

	public SmplValue<?> gt(SmplValue<?> arg) throws SmplException {
		throw new TypeSmplException("Operator not applicable: >");
	}

	public SmplValue<?> lt(SmplValue<?> arg) throws SmplException {
		throw new TypeSmplException("Operator not applicable: <");
	}

	public SmplValue<?> le(SmplValue<?> arg) throws SmplException {
		throw new TypeSmplException("Operator not applicable: <=");
	}

	public SmplValue<?> ge(SmplValue<?> arg) throws SmplException {
		throw new TypeSmplException("Operator not applicable: >=");
	}

	public SmplValue<?> neq(SmplValue<?> arg) throws SmplException {
		throw new TypeSmplException("Operator not applicable: !=");
	}

	public SmplValue<?> or(SmplValue<?> arg) throws SmplException {
		throw new TypeSmplException("Operator not applicable: or");
	}

	public SmplValue<?> and(SmplValue<?> arg) throws SmplException {
		throw new TypeSmplException("Operator not applicable: and");
	}

	public SmplValue<?> not() throws SmplException {
		throw new TypeSmplException("Operator not applicable: not");
	}

	public SmplValue<?> bitnot() throws SmplException {
		throw new TypeSmplException("Operator not applicable: ~");
	}

	public SmplValue<?> bitor(SmplValue<?> arg) throws SmplException {
		throw new TypeSmplException("Operator not applicable: |");
	}

	public SmplValue<?> bitand(SmplValue<?> arg) throws SmplException {
		throw new TypeSmplException("Operator not applicable: &");
	}

	public SmplValue<?> quadratic(SmplValue<?> arg) throws SmplException {
		throw new TypeSmplException("Function is not a quadratic");
	}

	// return literal values

	public int intValue() throws TypeSmplException {
		throw new TypeSmplException(SmplType.INTEGER, getType());
	}

	public double doubleValue() throws TypeSmplException {
		throw new TypeSmplException(SmplType.REAL, getType());
	}

	public boolean boolValue() throws TypeSmplException {
		throw new TypeSmplException(SmplType.BOOLEAN, getType());
	}

	public char charValue() throws TypeSmplException {
		throw new TypeSmplException(SmplType.CHARACTER, getType());
	}

	public String stringValue() {
		return toString();
	}

	public SmplProcedure procValue() throws TypeSmplException {
		throw new TypeSmplException(SmplType.PROCEDURE, getType());
	}

	public SmplVector vectorValue() throws TypeSmplException {
		throw new TypeSmplException(SmplType.VECTOR, getType());
	}

	public SmplList listValue() throws TypeSmplException {
		throw new TypeSmplException(SmplType.LIST, getType());
	} 

	public SmplQuadratic quadraticValue() throws TypeSmplException {
		throw new TypeSmplException(SmplType.QUADRATIC, getType());
	}
	
	
}