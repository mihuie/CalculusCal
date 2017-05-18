package com.uwimona.group25.calculuscal.syntax;

import com.uwimona.group25.calculuscal.semantics.Visitor;
import com.uwimona.group25.calculuscal.sys.SmplException;

public class ExpVectorRef extends Exp {

	String var;
	Exp ref;

	public ExpVectorRef(String var, Exp ref){
		this.var = var;
		this.ref = ref;
	}

	public String getVar(){
		return var;
	}

	public Exp getRef(){
		return ref;
	}

	@Override
	public <S, T> T visit(Visitor<S, T> v, S arg) throws SmplException {
		return v.visitExpVectorRef(this, arg);
	}

	@Override
	public String toString() {
		return var + "[" + ref + "]";
	}
}