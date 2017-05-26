package com.uwimona.group25.calculuscal.semantics;

import com.uwimona.group25.calculuscal.syntax.*;
import com.uwimona.group25.calculuscal.sys.SmplException;

public interface Visitor<S, T> {

    // program
    T visitSmplProgram(SmplProgram p, S arg) throws SmplException;

    // statements
    T visitStmtSequence(StmtSequence exp, S arg) throws SmplException;
    T visitStmtDefinition(StmtDefinition exp, S arg) throws SmplException;
    T visitStmtLet(StmtLet exp, S arg) throws SmplException;
    T visitStmtCase(StmtCase exp, S arg) throws SmplException;
    T visitStmtPrint(StmtPrint exp, S arg) throws SmplException;
    T visitExpAdd(ExpAdd exp, S arg) throws SmplException;
    T visitExpSub(ExpSub exp, S arg) throws SmplException;
    T visitExpMul(ExpMul exp, S arg) throws SmplException;
    T visitExpDiv(ExpDiv exp, S arg) throws SmplException;
    T visitExpMod(ExpMod exp, S arg) throws SmplException;
    T visitExpPow(ExpPow exp, S arg) throws SmplException;
    T visitExpLit(ExpLit exp, S arg) throws SmplException;
    T visitExpVar(ExpVar exp, S arg) throws SmplException;
    T visitExpProcedure(ExpProcedure exp, S arg) throws SmplException;
    T visitExpProcedureCall(ExpProcedureCall exp, S arg) throws SmplException;
    T visitExpPair(ExpPair exp, S arg) throws SmplException;
    T visitExpList(ExpList exp, S arg) throws SmplException;
    T visitExpVector(ExpVector exp, S arg) throws SmplException;
    T visitExpVectorRef(ExpVectorRef exp, S arg) throws SmplException;
    T visitExpSize(ExpSize exp, S arg) throws SmplException;
    T visitExpSubVector(ExpSubVector exp, S arg) throws SmplException;
    T visitExpPairCheck(ExpPairCheck exp, S arg) throws SmplException;
    T visitExpCar(ExpCar exp, S arg) throws SmplException;
    T visitExpCdr(ExpCdr exp, S arg) throws SmplException;
    T visitExpEq(ExpEq exp, S arg) throws SmplException;
    T visitExpGt(ExpGt exp, S arg) throws SmplException;
    T visitExpLt(ExpLt exp, S arg) throws SmplException;
    T visitExpLe(ExpLe exp, S arg) throws SmplException;
    T visitExpGe(ExpGe exp, S arg) throws SmplException;
    T visitExpNeq(ExpNeq exp, S arg) throws SmplException;
    T visitExpLogicNot(ExpLogicNot exp, S arg) throws SmplException;
    T visitExpLogicAnd(ExpLogicAnd exp, S arg) throws SmplException;
    T visitExpLogicOr(ExpLogicOr exp, S arg) throws SmplException;
    T visitExpBitWiseNot(ExpBitWiseNot exp, S arg) throws SmplException;
    T visitExpBitWiseAnd(ExpBitWiseAnd exp, S arg) throws SmplException;
    T visitExpBitWiseOr(ExpBitWiseOr exp, S arg) throws SmplException;
    T visitExpSubStr(ExpSubStr exp, S arg) throws SmplException;
    T visitExpEqv(ExpEqv exp, S arg) throws SmplException;
    T visitExpEqual(ExpEqual exp, S arg) throws SmplException;
    T visitExpCall(ExpCall exp, S arg) throws SmplException;
    T visitExpLazy(ExpLazy exp, S arg) throws SmplException;
    T visitExpDef(ExpDef exp, S arg) throws SmplException;
    T visitExpConcatLst(ExpConcatLst exp, S arg) throws SmplException;
    T visitExpRead(ExpRead exp, S arg) throws SmplException;
    T visitExpReadInt(ExpReadInt exp, S arg) throws SmplException;
    T visitExpIf(ExpIf exp, S arg) throws SmplException;
    T visitExpCase(ExpCase exp, S arg) throws SmplException;

    //new visitors
    T visitExpSin(ExpSin exp, S arg) throws SmplException;
    T visitExpCos(ExpCos exp, S arg) throws SmplException;
    T visitExpTan(ExpTan exp, S arg) throws SmplException;
    T visitExpSec(ExpSec exp, S arg) throws SmplException;
    T visitExpCosec(ExpCosec exp, S arg) throws SmplException;
    T visitExpCot(ExpCot exp, S arg) throws SmplException;



    T visitExpDifferentiate(ExpDifferentiate exp, S arg) throws SmplException;
    T visitExpIntegrate(ExpIntegrate exp, S arg) throws SmplException;
    T visitExpTaylor(ExpTaylor exp, S arg) throws SmplException;
    T visitExpContinuity(ExpContinuity exp, S arg) throws SmplException;

    T visitExpPlot(ExpPlot exp, S arg) throws SmplException;

    T visitExpLimit(ExpLimit exp, S arg) throws SmplException;
    T visitExpLog(ExpLog exp, S arg) throws SmplException;

    T visitExpFactorial(ExpFactorial exp, S arg) throws SmplException;

    T visitExpRoot(ExpRoot exp, S arg) throws SmplException;

}