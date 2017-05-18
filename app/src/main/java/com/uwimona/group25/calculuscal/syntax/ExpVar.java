package com.uwimona.group25.calculuscal.syntax;

import com.uwimona.group25.calculuscal.semantics.Visitor;
import com.uwimona.group25.calculuscal.sys.SmplException;

public class ExpVar extends Exp {

	String var;

	public ExpVar(String id){
		var = id;
	}

	public String getVar(){
		return var;
	}

	@Override
	public <S, T> T visit(Visitor<S, T> v, S arg) throws SmplException {
		return v.visitExpVar(this, arg);
	}

	@Override
	public String toString() {
		return var;
	}
}