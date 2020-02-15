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
	public static HashMap<BinaryRelationClass, String> BINARY_CLASSES = new HashMap<>();

	static {
		BINARY_CLASSES.put(new BinaryRelationClass(true, false, true, false, false, true, false, false, false, false), "Equivalence"); //Equivalence
		BINARY_CLASSES.put(new BinaryRelationClass(false, true, false, true, false, true, false, false, false, true), "strict order");//strict order
		BINARY_CLASSES.put(new BinaryRelationClass(true, false, false, false, true, true, false, false, false, false), "unbending order");//unbending order
		BINARY_CLASSES.put(new BinaryRelationClass(true, false, false, false, false, true, false, false, false, false), "quasi order");//quasi order
		BINARY_CLASSES.put(new BinaryRelationClass(false, false, false, true, false, true, true, false, false, true), "poor order");//poor order
		BINARY_CLASSES.put(new BinaryRelationClass(true, false, true, false, false, false, false, false, false, false), "tolerance order");//tolerance order
	}
}
