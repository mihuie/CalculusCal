package com.uwimona.group25.calculuscal.syntax;

import com.uwimona.group25.calculuscal.semantics.Visitor;
import com.uwimona.group25.calculuscal.sys.SmplException;
import java.util.*;

public class StmtSequence extends Exp {

    ArrayList<Statement> seq;		// sequence of commands

    public StmtSequence() {
	   seq = new ArrayList<>();
    }

    public StmtSequence(Statement s) {
	   this();
	   seq.add(s);
    }

    public ArrayList<Statement> getSeq() {
	   return seq;
    }

    public StmtSequence add(Statement s) {
	   seq.add(s);
	   return this;
    }

    @Override
    public <S,T> T visit(Visitor<S, T> v, S arg) throws SmplException {
    	return v.visitStmtSequence(this, arg);
    }

    @Override
    public String toString() {
	   Iterator iter = seq.iterator();

    	String result = "";
    	while (iter.hasNext()) {
    	    result = result + iter.next().toString() + "\n";
    	}

    	return result;
    }

}

