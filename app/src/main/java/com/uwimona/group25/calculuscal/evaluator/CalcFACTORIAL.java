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


public class CalcFACTORIAL extends Calc1ParamFunctionEvaluator implements CalcOperatorEvaluator {
	
	@Override
	protected CalcObject evaluateObject(CalcObject input) {
		return null;
	}
	
	@Override
	protected CalcObject evaluateDouble(CalcDouble input) {
		//use the generalized function GAMMA to evaluate doubles and fractions
		return CALC.GAMMA.createFunction(input.add(CALC.D_ONE));
	}

	@Override
	protected CalcObject evaluateFraction(CalcFraction input) {
		return CALC.GAMMA.createFunction(input.add(new CalcFraction(CALC.ONE, CALC.ONE)));
	}

	@Override
	protected CalcObject evaluateFunction(CalcFunction input) {
		return CALC.FACTORIAL.createFunction(input);
	}

	@Override
	protected CalcObject evaluateInteger(CalcInteger input) {
		return factorial(input);
	}

	@Override
	protected CalcObject evaluateSymbol(CalcSymbol input) {
		//cannot evaluate symbols, so just return the original function
		return CALC.FACTORIAL.createFunction(input);
	}


	public CalcInteger factorial(CalcInteger input) {

		CalcInteger result = CALC.ONE;

		if (input.isNegative()) {
			result = CALC.NEG_ONE;
			
			for (CalcInteger ii = CALC.NEG_TWO; ii.compareTo(input) >= 0; ii = ii.add(CALC.NEG_ONE)) {
				result = result.multiply(ii);
			}
		} 
		else {
			for (CalcInteger ii = CALC.TWO; ii.compareTo(input) <= 0; ii = ii.add(CALC.ONE)) {
				result = result.multiply(ii);
			}
		}

		return result;
	}

	@Override
	public int getPrecedence() {
		return 700;
	}

	@Override
	public String toOperatorString(CalcFunction function) {
		StringBuffer buffer = new StringBuffer();
		char operatorChar = '!';
		CalcObject temp = function.get(0);
		
    	if (temp.getPrecedence() < getPrecedence()) {
    		buffer.append('(');
    	}

    	buffer.append(temp.toString());

    	if (temp.getPrecedence() < getPrecedence()) {
    		buffer.append(')');
    	}
    	
    	buffer.append(operatorChar);
    	
    	return buffer.toString();
	}
}
