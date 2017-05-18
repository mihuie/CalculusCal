/*
 * Adapted from FnPlot
 */

package com.uwimona.group25.calculuscal.sys;

public class SmplException extends Exception {

    private static final long serialVersionUID = 1L;

    public SmplException() {
        super("SMPL Error");
    }
    
    public SmplException(String msg) {
        super(msg);
    }
    
    public SmplException(String msg, Throwable cause) {
        super(msg, cause);
    }

}
