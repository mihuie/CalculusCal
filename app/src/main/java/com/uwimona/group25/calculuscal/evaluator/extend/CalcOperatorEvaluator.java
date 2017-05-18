package com.uwimona.group25.calculuscal.evaluator.extend;

import com.uwimona.group25.calculuscal.struct.CalcFunction;


public interface CalcOperatorEvaluator extends CalcFunctionEvaluator {
	
	/**
	 * Converts a function into a special operator notation
	 * @param function input function
	 * @return operator notation String
	 */
	public String toOperatorString(CalcFunction function);
	
	/**
	 * 
	 * @return the precedence of the operator
	 */
	public int getPrecedence();
}
