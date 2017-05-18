package com.uwimona.group25.calculuscal.syntax;

import com.uwimona.group25.calculuscal.semantics.Visitor;
import com.uwimona.group25.calculuscal.sys.SmplException;
import java.util.*;

public class ExpIntegrate extends Exp {

  ExpProcedure function;
  String var, respectTo;
  int form;
  Exp exp;

  public ExpIntegrate(){
    super();
  }

  public ExpIntegrate(ExpProcedure p, String r){
    function = p;
    form = 0;
    respectTo = r;
  }

 

  public ExpIntegrate(Exp e, String r){
    form = 2;
    exp = e;
    respectTo = r;
  }

  public ExpIntegrate(String v, String r){
    var = v;
    form = 1;
    respectTo = r;
  }

  public ExpIntegrate(String v, String r, Exp or){
    var = v;
    form = 1;
    respectTo = r;
    
  }

  public ExpIntegrate(Exp e, String r, Exp or){
    form = 2;
    exp = e;
    respectTo = r;
    
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

 

  @Override
  public <S, T> T visit(Visitor<S, T> v, S arg) throws SmplException {
    return v.visitExpIntegrate(this, arg);
  }

  @Override
  public String toString() {
    return " ";
  }
}

