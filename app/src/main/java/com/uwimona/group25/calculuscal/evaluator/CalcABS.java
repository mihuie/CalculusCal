/**
 * 
 */
package com.uwimona.group25.calculuscal.evaluator;

import com.uwimona.group25.calculuscal.core.CALC;
import com.uwimona.group25.calculuscal.evaluator.extend.Calc1ParamFunctionEvaluator;
import com.uwimona.group25.calculuscal.evaluator.extend.CalcOperatorEvaluator;
import com.uwimona.group25.calculuscal.struct.CalcDouble;
import com.uwimona.group25.calculuscal.struct.CalcFraction;
import com.uwimona.group25.calculuscal.struct.CalcFunction;
import com.uwimona.group25.calculuscal.struct.CalcInteger;
import com.uwimona.group25.calculuscal.struct.CalcObject;
import com.uwimona.group25.calculuscal.struct.CalcSymbol;


public class CalcABS extends Calc1ParamFunctionEvaluator implements CalcOperatorEvaluator {

	/**
	 * 
	 */
	public CalcABS() {}
	
	@Override
	public CalcObject evaluateObject(CalcObject obj) {
		return null;
	}

	@Override
	protected CalcObject evaluateDouble(CalcDouble input) {
		if (input.isNegative()) return input.negate();
		return input;
	}

	@Override
	protected CalcObject evaluateFraction(CalcFraction input) {
		if (input.isNegative()) input.negate();
		return input;
	}

	@Override
	protected CalcObject evaluateFunction(CalcFunction input) {
		return CALC.ABS.createFunction(input);
	}

	@Override
	protected CalcObject evaluateInteger(CalcInteger input) {
		if (input.isNegative()) return input.negate();
		else return input;
	}

	@Override
	protected CalcObject evaluateSymbol(CalcSymbol input) {
		return CALC.ABS.createFunction(input);
	}

	@Override
	public int getPrecedence() {
		return 700;
	}

	@Override
	public String toOperatorString(CalcFunction function) {
		StringBuffer buffer = new StringBuffer();
		char operatorChar = '|';
		CalcObject temp = function.get(0);
		
//    	if (temp.getPrecedence() < getPrecedence()) {
//    		buffer.append('(');
//    	}

    	buffer.append(operatorChar + temp.toString() + operatorChar);

//    	if (temp.getPrecedence() < getPrecedence()) {
//    		buffer.append(')');
//    	}
//    	
    	return buffer.toString();		
	}
}
