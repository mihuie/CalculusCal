/*
 * Adapted from FnPlot
 */

package com.uwimona.group25.calculuscal.sys;

public class SyntaxSmplException extends SmplException {

    public SyntaxSmplException() {
        super("Syntax Error:");
    }
    
    public SyntaxSmplException(String msg) {
        super(msg);
    }    
    
    public SyntaxSmplException(String msg, Throwable t) {
        super(msg, t);
    }
}
