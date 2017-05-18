/**
 * 
 */
package com.uwimona.group25.calculuscal.evaluator;

import com.uwimona.group25.calculuscal.core.CALC;
import com.uwimona.group25.calculuscal.evaluator.extend.CalcFunctionEvaluator;
import com.uwimona.group25.calculuscal.exception.CalcWrongParametersException;
import com.uwimona.group25.calculuscal.struct.CalcFunction;
import com.uwimona.group25.calculuscal.struct.CalcObject;
import com.uwimona.group25.calculuscal.struct.CalcSymbol;


public class CalcDEFINE implements CalcFunctionEvaluator {

	/**
	 * 
	 */
	public CalcDEFINE() {}

	/* (non-Javadoc)
	 * @see javacalculus.evaluator.CalcFunctionEvaluator#evaluate(javacalculus.struct.CalcFunction)
	 */
	@Override
	public CalcObject evaluate(CalcFunction input) {
		if (input.size() == 2) {
			if (input.get(0) instanceof CalcSymbol) {
				CALC.setDefinedVariable((CalcSymbol)input.get(0), input.get(1));
				return input;
			}
			if (input.get(0) instanceof CalcFunction) {
				CalcFunction function = (CalcFunction)input.get(0);
				for (int ii = 0; ii < function.size(); ii++) {
					CalcObject currentTerm = function.get(ii);
					if (!(currentTerm instanceof CalcSymbol)) {
						throw new CalcWrongParametersException("DEFINE -> f(x,y...) must take only symbols");
					}
					else {
						function.addVariable((CalcSymbol)currentTerm);
					}
				}
				CALC.setDefinedVariable((CalcSymbol)function.getHeader(), input.get(1));
				return input;
			}
			else throw new CalcWrongParametersException("DEFINE -> first parameter must be a symbol");
		}
		else throw new CalcWrongParametersException("DEFINE -> wrong number of parameters");
	}

}
