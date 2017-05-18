package com.uwimona.group25.calculuscal.syntax;

import com.uwimona.group25.calculuscal.semantics.Visitor;
import com.uwimona.group25.calculuscal.sys.SmplException;
import java.util.*;

public class ExpList extends Exp {

  ArrayList<Exp> list;

  public ExpList(){
    list = new ArrayList();
  }

  public ExpList(ArrayList<Exp> list) {
    this.list = list;
  }

  public ArrayList<Exp> getList(){
    return list;
  }

  @Override
  public <S, T> T visit(Visitor<S, T> v, S arg) throws SmplException {
    return v.visitExpList(this, arg);
  }

  @Override
  public String toString() {
    return list.toString();
  }
}

