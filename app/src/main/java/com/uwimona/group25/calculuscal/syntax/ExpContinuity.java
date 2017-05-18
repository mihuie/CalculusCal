package com.uwimona.group25.calculuscal.syntax;

import com.uwimona.group25.calculuscal.semantics.Visitor;
import com.uwimona.group25.calculuscal.sys.SmplException;
import java.util.*;

public class ExpContinuity extends Exp {

  Exp function, point;
  String var;

  public ExpContinuity(){
    super();
  }

  public ExpContinuity(Exp f, String v, Exp p){
    function = f;
    var = v;
    point = p;
  }

  public Exp getFunction(){
    return function;
  }

  public Exp getPoint(){
    return point;
  }

  public String getVar(){
    return var;
  }



  @Override
  public <S, T> T visit(Visitor<S, T> v, S arg) throws SmplException {
    return v.visitExpContinuity(this, arg);
  }


  @Override
  public String toString() {
    return " ";
  }
}

