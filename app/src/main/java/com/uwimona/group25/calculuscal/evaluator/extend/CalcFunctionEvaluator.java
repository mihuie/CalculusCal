package com.uwimona.group25.calculuscal.evaluator.extend;

import com.uwimona.group25.calculuscal.struct.CalcFunction;
import com.uwimona.group25.calculuscal.struct.CalcObject;


public interface CalcFunctionEvaluator {
	/*
	 * Evaluate the given function if possible. Otherwise should return null.
	 */
	public CalcObject evaluate(CalcFunction input);
}
