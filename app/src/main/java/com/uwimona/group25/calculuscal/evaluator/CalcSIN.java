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


public class CalcSIN extends Calc1ParamFunctionEvaluator {
	
	@Override
	protected CalcObject evaluateObject(CalcObject input) {
		CalcDouble PI = null;
		try {
			PI = (CalcDouble)CALC.PI.evaluate();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		if (input.equals(PI)) {
			return CALC.ZERO;
		}
		if (input instanceof CalcDouble) {
			CalcDouble param = (CalcDouble)input;
			param = param.divide(PI); //retrieve coefficient of pi
			if (param.isInteger()) { //SIN(c*PI) = 0
				return CALC.ZERO;
			}
			//System.out.println(param.mod(CALC.D_HALF));
			//System.out.println(param.mod(CALC.D_HALF).isInteger());
			if (param.mod(CALC.D_HALF).equals(CALC.D_ZERO)) { //SIN(c*PI/2)
				if (param.mod(CALC.D_THREE_HALF).equals(CALC.D_ZERO)) {
					return CALC.NEG_ONE;
				} //SIN(c*3*PI/2) = -1, c does not divide 3 or 2
				else return CALC.ONE; //SIN(c*PI/2) = 1, c does not divide 2
			}
		}
		return null;
	}
	
	@Override
	protected CalcObject evaluateDouble(CalcDouble input) {
		return new CalcDouble(Math.sin(input.doubleValue()));
	}

	@Override
	protected CalcObject evaluateFraction(CalcFraction input) {
		return null;
	}

	@Override
	protected CalcObject evaluateFunction(CalcFunction input) {
		return CALC.SIN.createFunction(input);
	}

	@Override
	protected CalcObject evaluateInteger(CalcInteger input) {
		return new CalcDouble(Math.sin(input.bigIntegerValue().intValue()));
	}

	@Override
	protected CalcObject evaluateSymbol(CalcSymbol input) {
		//cannot evaluate symbols, so just return the original function
		return CALC.SIN.createFunction(input);
	}

}
