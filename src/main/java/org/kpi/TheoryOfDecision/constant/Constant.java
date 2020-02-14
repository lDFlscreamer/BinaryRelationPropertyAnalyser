/*
 * Copyright (c)  2.2020
 * This file (Constant) is part of BinaryRelationPropertyAnalyser.
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by Screamer  <999screamer999@gmail.com>
 */

package org.kpi.TheoryOfDecision.constant;

import org.kpi.TheoryOfDecision.entity.propertiesResult.BinaryRelationClass;

import java.util.HashMap;

public class Constant {
	public final static HashMap<String, BinaryRelationClass> BINARY_CLASSES = new HashMap<>();
	static {
		BINARY_CLASSES.put("Equivalence", new BinaryRelationClass(true, false, true, false, false, true, false, false, false, false)); //Equivalence
		BINARY_CLASSES.put("strict order", new BinaryRelationClass(false, true, false, true, false, true, false, false, false, true));//strict order
		BINARY_CLASSES.put("unbending order", new BinaryRelationClass(true, false, false, false, true, true, false, false, false, false));//unbending order
		BINARY_CLASSES.put("quasi order", new BinaryRelationClass(true, false, false, false, false, true, false, false, false, false));//quasi order
		BINARY_CLASSES.put("poor order", new BinaryRelationClass(false, false, false, true, false, true, true, false, false, true));//poor order
		BINARY_CLASSES.put("tolerance order", new BinaryRelationClass(true, false, true, false, false, false, false, false, false, false));//tolerance order
	}
}
