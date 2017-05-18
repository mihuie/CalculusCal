package com.uwimona.group25.calculuscal.syntax;

import com.uwimona.group25.calculuscal.semantics.Visitor;
import com.uwimona.group25.calculuscal.sys.SmplException;

public class ExpBitWiseNot extends Exp {

  Exp exp;

  public ExpBitWiseNot(Exp e) {
    exp = e;
  }

  public Exp getExp(){
    return exp;
  }

  @Override
  public <S, T> T visit(Visitor<S, T> v, S arg) throws SmplException {
    return v.visitExpBitWiseNot(this, arg);
  }

  @Override
  public String toString() {
    return "~" + exp.toString();
  }
}

