package com.uwimona.group25.calculuscal.syntax;

import com.uwimona.group25.calculuscal.semantics.Visitor;
import com.uwimona.group25.calculuscal.sys.SmplException;
import java.util.*;

public class ExpTaylor extends Exp {

  ExpProcedure function;
  String var, respectTo;
  int form;
  Exp exp, centered ,order;

  public ExpTaylor(){
    super();
  }

  public ExpTaylor(ExpProcedure p, String r, Exp cen , Exp or){
    function = p;
    form = 0;
    respectTo = r;
    centered = cen;
    order = or;
  }

  public ExpTaylor(String v, String r, Exp cen , Exp or){
    var = v;
    form = 1;
    respectTo = r;
    centered = cen;
    order = or;
  }


  public ExpTaylor(Exp e, String r, Exp cen , Exp or){
    form = 2;
    exp = e;
    respectTo = r;
    centered = cen;
    order = or;
  }

  public Exp getBody(){
    
    return exp;
  }

  public String getRespectTo(){
    return respectTo;
  }

  public ExpProcedure getFunction(){
    return function;
  }

  public String getVar(){
    return var;
  }
  public Integer getForm(){
    return form;
  }

  public Exp getOrder(){
    return order;
  }

  public Exp getCenter(){
    return centered;
  }

  @Override
  public <S, T> T visit(Visitor<S, T> v, S arg) throws SmplException {
    return v.visitExpTaylor(this, arg);
  }

  @Override
  public String toString() {
    return " ";
  }
}

