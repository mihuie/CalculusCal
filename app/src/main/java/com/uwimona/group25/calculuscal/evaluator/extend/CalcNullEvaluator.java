package com.uwimona.group25.calculuscal.evaluator.extend;

import com.uwimona.group25.calculuscal.struct.CalcFunction;
import com.uwimona.group25.calculuscal.struct.CalcObject;


public class CalcNullEvaluator implements CalcFunctionEvaluator {

	@Override
	public CalcObject evaluate(CalcFunction input) {
		return null;
	}

}
