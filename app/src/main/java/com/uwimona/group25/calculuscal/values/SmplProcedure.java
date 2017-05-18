/*
 * Adapted from FnPlot
 */

package com.uwimona.group25.calculuscal.values;

import com.uwimona.group25.calculuscal.sys.SmplException;
import com.uwimona.group25.calculuscal.semantics.Environment;
import com.uwimona.group25.calculuscal.syntax.ExpProcedure;
import static com.uwimona.group25.calculuscal.values.SmplValue.make;
import java.util.*;

public class SmplProcedure extends SmplValue<SmplProcedure> {

	ExpProcedure procExp;
	Environment<SmplValue<?>> closingEnv;

	public SmplProcedure(ExpProcedure procExp, Environment<SmplValue<?>> closingEnv){
		this.procExp = procExp;
		this.closingEnv = closingEnv;
	}

	@Override
	public SmplType getType(){
		return SmplType.PROCEDURE;
	}

	@Override
	public SmplProcedure procValue(){
		return this;
	}

	public ExpProcedure getProcExp(){
		return procExp;
	}

	public Environment<SmplValue<?>> getClosingEnv(){
		return closingEnv;
	}

	@Override
	public String toString() {
		String params;
		ArrayList<String> paramList = procExp.getParameters();
		String listvar = procExp.getListVar();
		int n = paramList.size();
		switch (n) {
			case 0: params = ""; break;
			case 1: params = paramList.get(0); break;
			default:
				params = paramList.get(0);
				for(int i=1; i<n; i++)
					params += ", " + paramList.get(i);
		}
		if(listvar != null)
			params += " . " + listvar;

		String body;

		if(procExp.getBody() != null)
			body = procExp.getBody().toString();
		else
			body = procExp.getExpressions().toString();

		return "[Procedure: (" + params + ") -> " + body + "]";
	}
}