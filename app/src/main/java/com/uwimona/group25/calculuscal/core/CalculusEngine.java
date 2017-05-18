package com.uwimona.group25.calculuscal.core;

import com.uwimona.group25.calculuscal.core.CalcParser;
import com.uwimona.group25.calculuscal.core.CALC;
import com.uwimona.group25.calculuscal.exception.CalcSyntaxException;
import com.uwimona.group25.calculuscal.struct.*;


public final class CalculusEngine {

	private String result = new String("No commands executed.");
	private long currentTime, deltaTime;
	
	/**
	 * Constructor
	 */
	public CalculusEngine() {
		
	}
	
	/**
	 * This is the most important function in CalculusEngine. The user
	 * specifies an input that is sent through the algorithm, producing a
	 * mathematical output that satisfies the grammar used in the command.
	 * @param command
	 * @return The result obtained by parsing and evaluating <b>command</b>.
	 */
	public String execute(String command) {
		currentTime = System.nanoTime();
		CALC.operator_notation = false;
		CalcObject parsed = null;
		CalcObject processed = null;
		CalcParser parser = new CalcParser();
		
		try {
			parsed = parser.parse(command);
		} catch (CalcSyntaxException e) {
			e.printStackTrace();
		}
		
		result = "Input: " + command + "\n";
		result += "Evaluation Queue: " + parsed + "\n";
		result += "Processed Queue: " + (processed=CALC.SYM_EVAL(parsed)) + "\n";
		CALC.toggleOperatorNotation();
		result += "Output: " + processed + "\n";
		

		return result;
	}
	
	/**
	 * 
	 * @return the previous result obtained by <b>execute</b>
	 */
	public String getResult() {
		return result;
	}
	
	/**
	 * Set the floating point precision to <b>precision</b> digits
	 * @param precision
	 */
	public void setPrecision(int precision) {
		CALC.setMathContext(precision);
	}
}
