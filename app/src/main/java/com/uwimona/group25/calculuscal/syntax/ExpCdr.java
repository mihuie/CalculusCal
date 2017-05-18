package com.uwimona.group25.calculuscal.syntax;

import com.uwimona.group25.calculuscal.semantics.Visitor;
import com.uwimona.group25.calculuscal.sys.SmplException;

public class ExpCdr extends Exp {

  Exp exp;

  public ExpCdr(Exp e) {
    exp = e;
  }

  public Exp getExp(){
    return exp;
  }


  @Override
  public <S, T> T visit(Visitor<S, T> v, S arg) throws SmplException {
    return v.visitExpCdr(this, arg);
  }

  @Override
  public String toString() {
    return "cdr: " + exp.toString();
  }
}

