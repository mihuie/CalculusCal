package com.uwimona.group25.calculuscal.semantics;

import android.graphics.PointF;

/**
 * The Plotter is a device that performs the graphing of a given function over
 * a set (range of input values).  The plotter determines based on its own
 * capabilities (and maybe characteristics of the function) how to sample the
 * input space, and provides a method to accept a collection of points to be
 * plotted.  The idea is that the interpretor should first call the sample method
 * to obtain a set of input points, compute the function's outputs for those inputs
 * and then call the plot method of the plotter on the collection of pairs of
 * points generated in that way.
 * @author newts
 */
public interface Plotter {

    double[] sample(double low, double hi);

    void plot(PointF[][] points);

    void clear();

}
