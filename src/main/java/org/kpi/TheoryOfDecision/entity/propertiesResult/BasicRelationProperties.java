/*
 * Copyright (c)  2.2020
 * This file (BasicRelationProperties) is part of BinaryRelationPropertyAnalyser.
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by Screamer  <999screamer999@gmail.com>
 */
package org.kpi.TheoryOfDecision.entity.propertiesResult;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.ArrayList;
import java.util.List;
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class BasicRelationProperties extends BinaryRelationClass {
	private  List<ArrayList<Integer>> matrix;

	public BasicRelationProperties(List<ArrayList<Integer>> matrix) {
		this.matrix = matrix;
	}

	public List<ArrayList<Integer>> getMatrix() {
		return matrix;
	}

	public void setMatrix(List<ArrayList<Integer>> matrix) {
		this.matrix = matrix;
	}
}
