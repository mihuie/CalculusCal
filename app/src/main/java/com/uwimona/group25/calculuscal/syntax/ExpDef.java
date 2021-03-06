package com.uwimona.group25.calculuscal.syntax;

import com.uwimona.group25.calculuscal.semantics.Visitor;
import com.uwimona.group25.calculuscal.syntax.Exp;
import com.uwimona.group25.calculuscal.sys.SmplException;


public class ExpDef extends Exp {

	String var;
	Exp exp;

	public ExpDef(String id, Exp e) {
		var = id;
		exp = e;
	}

	public String getVar(){
		return var;
	}

	public Exp getExp(){
		return exp;
	}

	@Override
	public <S, T> T visit(Visitor<S, T> v, S arg) throws SmplException {
		return v.visitExpDef(this, arg);
	}

	@Override
	public String toString() {
		return String.format("def %s -> %s" , var, exp.toString());
	}
}