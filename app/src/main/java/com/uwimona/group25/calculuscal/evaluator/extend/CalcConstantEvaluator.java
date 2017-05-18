/**
 * 
 */
package com.uwimona.group25.calculuscal.evaluator.extend;

import com.uwimona.group25.calculuscal.struct.CalcFunction;
import com.uwimona.group25.calculuscal.struct.CalcObject;

public class CalcConstantEvaluator implements CalcFunctionEvaluator {
	
	CalcObject constant;
	/**
	 * 
	 */
	public CalcConstantEvaluator(CalcObject obj) {
		constant = obj;
	}

	/* (non-Javadoc)
	 * @see javacalculus.evaluator.CalcFunctionEvaluator#evaluate(javacalculus.struct.CalcFunction)
	 */
	@Override
	public CalcObject evaluate(CalcFunction input) {
		return null;
	}
	
	public CalcObject getValue() {
		return constant;
	}

}
