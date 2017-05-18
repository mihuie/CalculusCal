package com.uwimona.group25.calculuscal.syntax;

import com.uwimona.group25.calculuscal.semantics.Visitor;
import com.uwimona.group25.calculuscal.sys.SmplException;


public class ExpSubVector extends Exp {

  Exp size, proc;

  public ExpSubVector(Exp size, Exp proc){
    this.size = size;
    this.proc = proc;
  }

  public Exp getSize(){
    return size;
  }

  public Exp getProcedure(){
    return proc;
  }

  @Override
  public <S, T> T visit(Visitor<S, T> v, S arg) throws SmplException {
    return v.visitExpSubVector(this, arg);
  }

  @Override
  public String toString() {
    return size.toString() + ": " + proc.toString();
  }
}