/*
 * Copyright (c)  2.2020
 * This file (BasicRelationProperties) is part of BinaryRelationPropertyAnalyser.
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by Screamer  <999screamer999@gmail.com>
 */
package org.kpi.TheoryOfDecision.entity.propertiesResult;

import java.util.List;

public class BasicRelationProperties extends BinaryRelationClass {
	private final List<List<Integer>> matrix;

	public BasicRelationProperties(List<List<Integer>> matrix) {
		this.matrix = matrix;
	}

	public List<List<Integer>> getMatrix() {
		return matrix;
	}
}
