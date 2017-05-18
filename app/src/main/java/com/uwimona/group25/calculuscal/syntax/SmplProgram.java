package com.uwimona.group25.calculuscal.syntax;

import com.uwimona.group25.calculuscal.semantics.Visitor;
import com.uwimona.group25.calculuscal.sys.SmplException;

public class SmplProgram extends Exp {
    StmtSequence seq;

    public SmplProgram(StmtSequence s) {
	   seq = s;
    }

    public StmtSequence getSeq(){
        return seq;
    }

    @Override
    public <S, T> T visit(Visitor<S, T> v, S arg) throws SmplException {
        return v.visitSmplProgram(this, arg);
    }

    public String toString() {
	   return seq.toString();
    }
}
