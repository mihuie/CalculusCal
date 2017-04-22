package com.uwimona.group25.calculuscal;

import java.util.ArrayList;

/**
 * Created by michael on 4/21/17.
 */
public final class Utils {

    final static String DIVIDE = "/";
    final static String MULTIPLY = "*";
    final static String MINUS = "-";
    final static String ADD = "+";
    final static String EQUAL = "=";
    final static String POWER = "^";
    final static String COS = "`cos ";
    final static String SIN = "`sin ";
    final static String TAN = "`tan ";
    final static String LN = "`ln";
    final static String LOG = "`log_";
    final static String DECIMAL = ".";
    final static String EXPONENT = "e";
    final static String FUNCTIONF = "f";
    final static String FUNCTIONG = "g";
    final static String VARIABLEX = "x";
    final static String VARIABLEY = "y";
    final static String LEFTBRACKET = "(";
    final static String RIGHTBRACKET = ")";
    final static String INTEGRAL = "\u222B ";
    final static String PI = "\u03C0 ";
    final static String ROOT = "\u221A{";
    final static String INFINITY = "\u221E";
    final static String DIFF = "\u2202";
    final static String LIMIT = "`lim\u2199{";
    final static String TOWARDS = "\u2192";

    private Utils(){

    }

    public static ArrayList<String> getOperands(){

        ArrayList<String> constants = new ArrayList<>();

        constants.add(DIVIDE);
        constants.add(MULTIPLY);
        constants.add(MINUS);
        constants.add(ADD);
        constants.add(EQUAL);
        constants.add(POWER);
        constants.add(COS);
        constants.add(SIN);
        constants.add(TAN);
        constants.add(LN);
        constants.add(LOG);
        constants.add(INTEGRAL);
        constants.add(INFINITY);
        constants.add(ROOT);
        constants.add(TOWARDS);
        constants.add(LIMIT);
        constants.add(PI);
        constants.add(DIFF);
        constants.add(VARIABLEX);
        constants.add(VARIABLEY);
        constants.add(FUNCTIONF);
        constants.add(FUNCTIONG);
        constants.add(EXPONENT);
        constants.add(DECIMAL);
        constants.add(LEFTBRACKET);
        constants.add(RIGHTBRACKET);

        return constants;
    }

    public static boolean isOperator(String c){
        return getOperands().contains(c);
    }

    public static String evaluate(final String str){
        return str;
    }

}