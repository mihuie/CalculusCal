/*
 * Adapted from FnPlot
 */

package com.uwimona.group25.calculuscal.sys;

import com.uwimona.group25.calculuscal.semantics.Environment;
import com.uwimona.group25.calculuscal.semantics.Evaluator;
import com.uwimona.group25.calculuscal.syntax.SmplProgram;
import com.uwimona.group25.calculuscal.syntax.SmplLexer;
import com.uwimona.group25.calculuscal.syntax.SmplParser;

import java.io.*;

public class Repl {

	public static final String PROMPT = "SMPL>";

	public static void main(String args[]) {
		if (args.length == 0){
			repl(new Environment());
		} else {
			repl(new Environment(), args[0]);
		}
	}

	public static void repl(Environment env) {
		InputStreamReader reader = new InputStreamReader(System.in);
		while (true) {
			parseEvalShow(reader, env, false);
		}
	}

	public static void repl(Environment env, String filename){
		try {
			File file = new File(filename);
			FileReader reader = new FileReader(file);
			parseEvalShow(reader, env, true);
		} catch(FileNotFoundException e) {
			System.out.println("Could not find " + filename);
		}
	}

	public static void parseEvalShow(Reader reader, Environment env, boolean usingFile) {
		SmplParser parser;
		SmplProgram program = null;
		Evaluator interp = new Evaluator();
		if(!usingFile)
			System.out.print(PROMPT);
		try {
			parser = new SmplParser(new SmplLexer(reader));
			program = (SmplProgram) parser.parse().value;
		} catch (Exception e) {
			System.out.println("Parse Error: " + e.getMessage());
			// e.printStackTrace();
		}

		if (program != null)
			try {
				Object result;
				result = program.visit(interp, env);
				// uncomment the following line to automatically print the result of the last expression
				System.out.println("\nResult: " + result);
				System.out.println("");		// new line at end of output
			} catch (SmplException e) {
				System.out.println(e.getMessage());
			}
	}

}
