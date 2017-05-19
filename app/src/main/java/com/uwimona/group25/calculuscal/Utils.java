package com.uwimona.group25.calculuscal;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by michael on 4/21/17.
 */
public final class Utils {

    final static String SPACE = " ";
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
    final static String LOG = "`log";
    final static String DECIMAL = ".";
    final static String EXPONENT = "e";
    final static String FUNCTIONF = "f";
    final static String FACTORIAL = "!";
    final static String FUNCTIONG = "g";
    final static String VARIABLEX = "x";
    final static String VARIABLEY = "y";
    final static String LEFTBRACKET = "(";
    final static String RIGHTBRACKET = ")";
    final static String INTEGRAL = "\u222B ";
    final static String PI = "\u03C0 ";
    final static String ROOT = "\u221A{";
    final static String INFINITY = "\u221E";
    final static String DIFF = "\u2202x/\u2202y ";
    final static String LIMIT = "`lim\u2199{";
    final static String TOWARDS = "\u2192";

    final static ArrayList<String> basicOperands = new ArrayList<>(Arrays.asList(DIVIDE, MULTIPLY, MINUS, ADD, POWER, EQUAL));
    final static ArrayList<String> advanceOperands = new ArrayList<>(Arrays.asList(LN, COS, SIN, TAN, LOG));
    final static ArrayList<String> noDuplicates = new ArrayList<>(Arrays.asList(LN, COS, SIN, TAN, LOG, DIVIDE,
            MULTIPLY, MINUS, ADD, POWER, EQUAL, DECIMAL, FUNCTIONF, FUNCTIONG, ROOT, INTEGRAL, INFINITY, TOWARDS,
            EXPONENT, VARIABLEX, VARIABLEY, PI, FACTORIAL));


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
//        constants.add(VARIABLEX);
//        constants.add(VARIABLEY);
        constants.add(FUNCTIONF);
        constants.add(FUNCTIONG);
        constants.add(EXPONENT);
        constants.add(DECIMAL);
        constants.add(LEFTBRACKET);
        constants.add(RIGHTBRACKET);
        constants.add(FACTORIAL);

        return constants;
    }

    public static boolean isBasicOperator(String c){
        return getBasicOperators().contains(c);
    }

    public static boolean isAdvancedOperator(String c){
        return getAdvancedOperators().contains(c);
    }

    public static boolean isInNoDuplicates(String c){
        return getNoDuplicates().contains(c);
    }

    public static ArrayList<String> getBasicOperators(){
        return basicOperands;
    }

    public static ArrayList<String> getAdvancedOperators(){
        return advanceOperands;
    }

    public static ArrayList<String> getNoDuplicates(){
        return noDuplicates;
    }

    public static String evaluate(final String str){
        return str;
    }

}