package com.uwimona.group25.calculuscal.syntax;

import com.uwimona.group25.calculuscal.semantics.Visitor;
import com.uwimona.group25.calculuscal.sys.SmplException;

public class ExpRoot extends Exp {

  Exp exp, lowerLimit, upperLimit;
  String var;

  public ExpRoot(Exp e, String v, Exp ll, Exp ul) {
    exp = e;
    var = v;
    lowerLimit = ll;
    upperLimit = ul;
  }

  public Exp getExp(){
    return exp;
  }

  public String getVar(){
    return var;
  }

  public Exp getLowerLimit(){
    return lowerLimit;
  }

  public Exp getUpperLimit(){
    return upperLimit;
  }

  @Override
  public <S, T> T visit(Visitor<S, T> v, S arg) throws SmplException {
    return v.visitExpRoot(this, arg);
  }

  @Override
  public String toString() {
    return "Root(" +exp.toString() +" , " +var +" in [" +lowerLimit.toString() +" , " +upperLimit.toString() +"])";
  }
}

