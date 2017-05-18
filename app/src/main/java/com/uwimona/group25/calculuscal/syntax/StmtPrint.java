package com.uwimona.group25.calculuscal.syntax;

import com.uwimona.group25.calculuscal.semantics.Visitor;
import com.uwimona.group25.calculuscal.syntax.Exp;
import com.uwimona.group25.calculuscal.sys.SmplException;

public class StmtPrint extends Exp {

	Exp exp;
	char terminator;

	public StmtPrint(Exp e){
		this(e, '\0');
	}

	public StmtPrint(Exp e, char t) {
		exp = e;
		terminator = t;
	}

	public Exp getExp(){
		return exp;
	}

	public char getTerminatingCharacter(){
		return terminator;
	}

	@Override
	public <S, T> T visit(Visitor<S, T> v, S arg) throws SmplException {
		return v.visitStmtPrint(this, arg);
	}

	@Override
	public String toString() {
		return exp.toString() + terminator;
	}
}