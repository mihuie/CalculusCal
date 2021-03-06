package com.uwimona.group25.calculuscal.syntax;

import com.uwimona.group25.calculuscal.semantics.Visitor;
import com.uwimona.group25.calculuscal.sys.SmplException;
import java.util.*;

public class ExpProcedureCall extends Exp {

  ArrayList<Exp> args;
  String var;
  Exp procExp;

  public ExpProcedureCall(){
    super();
  }

  public ExpProcedureCall(String var, ArrayList<Exp> args){
    this.args = args;
    this.var = var;
    this.procExp = null;
  }

  public ExpProcedureCall(Exp procExp, ArrayList<Exp> args){
    this.args = args;
    this.var = null;
    this.procExp = procExp;
  }

  public ArrayList<Exp> getArguments(){
    return args;
  }

  public String getVar(){
    return var;
  }

  public Exp getProcExp(){
    return procExp;
  }

  @Override
  public <S, T> T visit(Visitor<S, T> v, S arg) throws SmplException {
    return v.visitExpProcedureCall(this, arg);
  }

  @Override
  public String toString() {
    return var + "(" + args + ")";
  }
}

