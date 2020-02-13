/*
 * Copyright (c)  2.2020
 * This file (RelationClassResult) is part of BinaryRelationPropertyAnalyser.
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by Screamer  <999screamer999@gmail.com>
 */

package org.kpi.TheoryOfDecision.entity;

import java.util.List;

public class RelationClassResult extends Result {

	private String className;


	public RelationClassResult(List<?> inputedMatrix) {
		super(inputedMatrix);
	}

	public RelationClassResult(List<?> inputedMatrix, String className) {
		super(inputedMatrix);
		this.className = className;
	}

	public void ClassAnalyser() {
		//todo:write code  for anaalyse
	}
}
