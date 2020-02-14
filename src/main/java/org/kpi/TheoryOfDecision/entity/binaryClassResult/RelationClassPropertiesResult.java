/*
 * Copyright (c)  2.2020
 * This file (RelationClassPropertiesResult) is part of BinaryRelationPropertyAnalyser.
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by Screamer  <999screamer999@gmail.com>
 */
package org.kpi.TheoryOfDecision.entity.binaryClassResult;

import org.kpi.TheoryOfDecision.entity.propertiesResult.PropertiesResult;

import java.util.List;

public class RelationClassPropertiesResult extends PropertiesResult {

	private String className;

	public RelationClassPropertiesResult(List<?> inputedMatrix) {
		super(inputedMatrix);
	}

	public RelationClassPropertiesResult(List<?> inputedMatrix, String className) {
		super(inputedMatrix);
		this.className = className;
	}

	public void ClassAnalyser() {
		//biggest
		if (this.getIr().isEmpty()) {
			// TODO: 13.02.2020 find biggest on asymmetry part
		} else {
			// TODO: 13.02.2020 find biggest on relation
		}
		//maximum element
	}
}
