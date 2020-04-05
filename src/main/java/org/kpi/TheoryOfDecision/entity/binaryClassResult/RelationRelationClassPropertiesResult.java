/*
 * Copyright (c)  2.2020
 * This file (RelationClassPropertiesResult) is part of BinaryRelationPropertyAnalyser.
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by Screamer  <999screamer999@gmail.com>
 */
package org.kpi.TheoryOfDecision.entity.binaryClassResult;

import org.kpi.TheoryOfDecision.entity.RelationObj;
import org.kpi.TheoryOfDecision.entity.propertiesResult.PropertiesResult;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class RelationRelationClassPropertiesResult extends PropertiesResult {

	private String className;

	public RelationRelationClassPropertiesResult(List<ArrayList<Integer>> inputedMatrix) {
		super(inputedMatrix);
	}

	public RelationRelationClassPropertiesResult(List<ArrayList<Integer>> inputedMatrix, String className) {
		super(inputedMatrix);
		this.className = className;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}
}
