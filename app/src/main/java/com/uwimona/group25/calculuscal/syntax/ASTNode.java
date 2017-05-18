package com.uwimona.group25.calculuscal.syntax;

import com.uwimona.group25.calculuscal.semantics.Visitor;
import com.uwimona.group25.calculuscal.sys.SmplException;

public abstract class ASTNode {

    public abstract <S, T> T visit(Visitor<S, T> v, S state) throws SmplException ;

    @Override
    public abstract String toString();

}
