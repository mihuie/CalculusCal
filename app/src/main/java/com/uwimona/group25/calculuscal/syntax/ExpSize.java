package com.uwimona.group25.calculuscal.syntax;

import com.uwimona.group25.calculuscal.semantics.Visitor;
import com.uwimona.group25.calculuscal.sys.SmplException;
import java.util.*;

public class ExpSize extends Exp {

  Exp body;

  public ExpSize(Exp body){
    this.body = body;
  }

  public Exp getBody(){
    return body;
  }

  @Override
  public <S, T> T visit(Visitor<S, T> v, S arg) throws SmplException {
    return v.visitExpSize(this, arg);
  }

  @Override
  public String toString() {
    return "size(" + body + ")";
  }
}

