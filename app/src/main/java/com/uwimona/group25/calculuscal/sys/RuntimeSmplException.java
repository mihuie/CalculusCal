/*
 * Adapted from FnPlot
 */

package com.uwimona.group25.calculuscal.sys;

public class RuntimeSmplException extends SmplException {

    public RuntimeSmplException() {
        super("Smpl Runtime Error");
    }

    public RuntimeSmplException(String msg) {
        super(msg);
    }

    public RuntimeSmplException(String msg, Throwable cause) {
        super(msg, cause);
    }

}
