/**
 * 
 */
package com.uwimona.group25.calculuscal.evaluator;

import com.uwimona.group25.calculuscal.core.CALC;
import com.uwimona.group25.calculuscal.evaluator.extend.Calc1ParamFunctionEvaluator;
import com.uwimona.group25.calculuscal.struct.CalcDouble;
import com.uwimona.group25.calculuscal.struct.CalcFraction;
import com.uwimona.group25.calculuscal.struct.CalcFunction;
import com.uwimona.group25.calculuscal.struct.CalcInteger;
import com.uwimona.group25.calculuscal.struct.CalcObject;
import com.uwimona.group25.calculuscal.struct.CalcSymbol;


public class CalcLN extends Calc1ParamFunctionEvaluator {
	
	@Override
	protected CalcObject evaluateObject(CalcObject input) {
		CalcDouble E = null;
		try {
			E = (CalcDouble)CALC.E.evaluate();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		if (input.equals(E)) {
			return CALC.ONE;
		}
		if (input.equals(CALC.ONE)) {
			return CALC.ZERO;
		}
		if (input.equals(CALC.ZERO)) {
			return CALC.NEG_INFINITY;
		}
		return null;
	}
	
	@Override
	protected CalcObject evaluateDouble(CalcDouble input) {
		return new CalcDouble(Math.log(input.doubleValue()));
	}

	@Override
	protected CalcObject evaluateFraction(CalcFraction input) {
		//TODO decide whether ln(x/y) should evaluate to ln(x)-ln(y)
		return CALC.LN.createFunction(input);
	}

	@Override
	protected CalcObject evaluateFunction(CalcFunction input) {
		//TODO make this more flexible?
		return CALC.LN.createFunction(input);
	}

	@Override
	protected CalcObject evaluateInteger(CalcInteger input) {
		return new CalcDouble(Math.log(input.bigIntegerValue().intValue()));
	}

	
	@Override
	protected CalcObject evaluateSymbol(CalcSymbol input) {
		//cannot evaluate symbols, so just return the original function
		return CALC.LN.createFunction(input);
	}

}
