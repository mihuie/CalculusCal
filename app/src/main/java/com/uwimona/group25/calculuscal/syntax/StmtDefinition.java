package com.uwimona.group25.calculuscal.syntax;

import com.uwimona.group25.calculuscal.semantics.Visitor;
import com.uwimona.group25.calculuscal.syntax.Exp;
import com.uwimona.group25.calculuscal.sys.SmplException;
import java.util.*;

public class StmtDefinition extends Exp {

	ArrayList<String> vars;
	ArrayList<Exp> exps;

	ExpVectorRef vr;
	Exp r;

	public StmtDefinition(ArrayList<String> ids, ArrayList<Exp> e) {
		vars = ids;
		exps = e;
	}

	public StmtDefinition(ExpVectorRef vr, Exp r) {
		this.vr = vr;
		this.r = r;
	}

	public ArrayList<String> getVars(){
		return vars;
	}

	public ArrayList<Exp> getExps(){
		return exps;
	}

	public ExpVectorRef getVectorReference(){
		return vr;
	}

	public Exp getExp(){
		return r;
	}

	@Override
	public <S, T> T visit(Visitor<S, T> v, S arg) throws SmplException {
		return v.visitStmtDefinition(this, arg);
	}

	@Override
	public String toString() {
		if(vr != null)
			return String.format("%s := %s", vr.toString(), r.toString());
		else
			return String.format("%s := %s", vars, exps.toString());
	}
}