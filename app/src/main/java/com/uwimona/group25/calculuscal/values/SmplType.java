/*
 * Adapted from FnPlot
 */

package com.uwimona.group25.calculuscal.values;

public enum SmplType {
	INTEGER("int"),
	REAL("real"),
	BOOLEAN("bool"),
	CHARACTER("char"),
	STRING("string"),
	PAIR("pair"),
	LIST("list"),
	VECTOR("vector"),
	SUBVECTOR("subvector"),
	EMPTYLIST("nil"),
	PROCEDURE("user function"),
	QUADRATIC("quadratic"),
	INFINITY("infinity");

	private final String docString;

	SmplType(String docString){
		this.docString = docString;
	}
}