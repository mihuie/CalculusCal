package com.uwimona.group25.calculuscal.syntax;

import com.uwimona.group25.calculuscal.semantics.Visitor;
import com.uwimona.group25.calculuscal.sys.SmplException;
import java.util.*;
import com.uwimona.group25.calculuscal.values.*;

public class ExpCall extends Exp {

  Exp exp;
  Exp lst;

  public ExpCall(Exp e){
    exp = e;
    lst = new ExpList();
  }

  public ExpCall(Exp e, Exp l){
    exp = e;
    lst = l;
  }

  public Exp getExpL(){
    return exp;
  }

  public Exp getExpR(){
    return lst;
  }

  @Override
  public <S, T> T visit(Visitor<S, T> v, S arg) throws SmplException {
    return v.visitExpCall(this, arg);
  }

  @Override
  public String toString() {
    return "call(" + exp.toString() + ", " + lst.toString() + ")";
  }
}