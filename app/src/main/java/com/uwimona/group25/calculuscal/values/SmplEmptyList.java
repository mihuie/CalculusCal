package com.uwimona.group25.calculuscal.values;

import com.uwimona.group25.calculuscal.sys.SmplException;
import static com.uwimona.group25.calculuscal.values.SmplValue.make;
import java.util.*;

public class SmplEmptyList extends SmplList {

	public SmplEmptyList(){
		super(null, null);
	}

	@Override
	public SmplType getType(){
		return SmplType.EMPTYLIST;
	}

	@Override
	public SmplValue<?> eq(SmplValue<?> arg) throws SmplException {
		return make(getType() == arg.getType());
	}

	@Override
	public SmplValue<?> neq(SmplValue<?> arg) throws SmplException {
		return make(getType() != arg.getType());
	}

	@Override
	public String toString() {
		return "nil";
	}
}