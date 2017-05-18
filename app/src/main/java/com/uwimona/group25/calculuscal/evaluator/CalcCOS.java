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


public class CalcCOS extends Calc1ParamFunctionEvaluator {
	
	@Override
	protected CalcObject evaluateObject(CalcObject input) {
		CalcDouble PI = null;
		try {
			PI = (CalcDouble)CALC.PI.evaluate();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		if (input.equals(PI)) {	//COS(PI) = -1
			return CALC.NEG_ONE;
		}
		if (input instanceof CalcDouble) {
			CalcDouble param = (CalcDouble)input;
			param = param.divide(PI); //retrieve coefficient of pi
			if (param.isInteger() && param.isEven()) { //COS((2k)*PI) = 1
				return CALC.ONE;
			}
			else if (param.isInteger() && !param.isEven()) {	//COS((2k+1)*PI) = -1
				return CALC.NEG_ONE;
			}
			if (param.mod(CALC.D_HALF).equals(CALC.D_ZERO)) { //COS(c*PI/2) = 0
				return CALC.ZERO;
			}
		}
		return null;
	}
	
	@Override
	protected CalcObject evaluateDouble(CalcDouble input) {
		return new CalcDouble(Math.cos(input.doubleValue()));
	}

	@Override
	protected CalcObject evaluateFraction(CalcFraction input) {
		return null;
	}

	@Override
	protected CalcObject evaluateFunction(CalcFunction input) {
		return CALC.COS.createFunction(input);
	}

	@Override
	protected CalcObject evaluateInteger(CalcInteger input) {
		return new CalcDouble(Math.cos(input.bigIntegerValue().intValue()));
	}

	@Override
	protected CalcObject evaluateSymbol(CalcSymbol input) {
		//cannot evaluate symbols, so just return the original function
		return CALC.COS.createFunction(input);
	}

}
