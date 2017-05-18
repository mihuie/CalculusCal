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


public class CalcGAMMA extends Calc1ParamFunctionEvaluator {
	
	private static final double[] LanczoCoefficients = {	75122.6331530,
															80916.6278952,
															36308.2951477,
															8687.24529705,
															1168.92649479,
															83.8676043424,
															2.50662827511};
	
	@Override
	protected CalcObject evaluateObject(CalcObject input) {
		return null;
	}
	
	@Override
	protected CalcObject evaluateDouble(CalcDouble input) {
		return gamma(input);
	}

	@Override
	protected CalcObject evaluateFraction(CalcFraction input) {
		try {
			return gamma((CalcDouble)input.evaluate());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	protected CalcObject evaluateFunction(CalcFunction input) {
		return CALC.GAMMA.createFunction(input);
	}

	@Override
	protected CalcObject evaluateInteger(CalcInteger input) {
		//special case GAMMA(x) = (x-1)! for x in Z
		return CALC.FACTORIAL.createFunction(input.add(CALC.NEG_ONE));
	}

	@Override
	protected CalcObject evaluateSymbol(CalcSymbol input) {
		//cannot evaluate symbols, so just return the original function
		return CALC.GAMMA.createFunction(input);
	}
	
	public static CalcObject gamma(CalcDouble input) {
		double numerator = 0, denominator = 1, z = input.doubleValue(), result;
		for (int ii = 0; ii < LanczoCoefficients.length; ii++) {
			numerator += Math.pow(z, ii)*LanczoCoefficients[ii];
			denominator *= (z + ii);
		}
		result = (numerator/denominator) * Math.pow(z + 5.5, z + 0.5) * Math.pow(Math.E, -(z + 5.5));
		return new CalcDouble(result);
	}

}
