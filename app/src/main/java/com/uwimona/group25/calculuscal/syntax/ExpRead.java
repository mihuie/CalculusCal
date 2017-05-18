package com.uwimona.group25.calculuscal.syntax;

import com.uwimona.group25.calculuscal.semantics.Visitor;
import com.uwimona.group25.calculuscal.sys.SmplException;


public class ExpRead extends Exp {

  

  public ExpRead() {
   
  }

  

  @Override
  public <S, T> T visit(Visitor<S, T> v, S arg) throws SmplException {
    return v.visitExpRead(this, arg);
  }

  @Override
  public String toString() {
    return "read()";
  }
}