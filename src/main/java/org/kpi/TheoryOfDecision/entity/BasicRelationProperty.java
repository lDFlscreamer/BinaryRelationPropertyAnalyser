/*
 * Copyright (c)  2.2020
 * This file (BasicRelationProperty) is part of BinaryRelationPropertyAnalyser.
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by Screamer  <999screamer999@gmail.com>
 */

package org.kpi.TheoryOfDecision.entity;

import java.util.List;

public class BasicRelationProperty {
	private final List<?> matrix;
	protected boolean reflectivity;
	protected boolean antireflectivity;
	protected boolean simmetry;
	protected boolean asimmetry;
	protected boolean antisimmetry;
	protected boolean acyclicity;
	protected boolean connectedness;
	protected boolean weakConnectedness;
	protected boolean transitivity;
	protected boolean negativeTransitivity;

	public BasicRelationProperty(List<?> matrix) {
		this.matrix = matrix;
	}

	public List<?> getMatrix() {
		return matrix;
	}

	public boolean isReflectivity() {
		return reflectivity;
	}

	public void setReflectivity(boolean reflectivity) {
		this.reflectivity = reflectivity;
	}

	public boolean isAntireflectivity() {
		return antireflectivity;
	}

	public void setAntireflectivity(boolean antireflectivity) {
		this.antireflectivity = antireflectivity;
	}

	public boolean isSimmetry() {
		return simmetry;
	}

	public void setSimmetry(boolean simmetry) {
		this.simmetry = simmetry;
	}

	public boolean isAsimmetry() {
		return asimmetry;
	}

	public void setAsimmetry(boolean asimmetry) {
		this.asimmetry = asimmetry;
	}

	public boolean isAntisimmetry() {
		return antisimmetry;
	}

	public void setAntisimmetry(boolean antisimmetry) {
		this.antisimmetry = antisimmetry;
	}

	public boolean isAcyclicity() {
		return acyclicity;
	}

	public void setAcyclicity(boolean acyclicity) {
		this.acyclicity = acyclicity;
	}

	public boolean isConnectedness() {
		return connectedness;
	}

	public void setConnectedness(boolean connectedness) {
		this.connectedness = connectedness;
	}

	public boolean isWeakConnectedness() {
		return weakConnectedness;
	}

	public void setWeakConnectedness(boolean weakConnectedness) {
		this.weakConnectedness = weakConnectedness;
	}

	public boolean isTransitivity() {
		return transitivity;
	}

	public void setTransitivity(boolean transitivity) {
		this.transitivity = transitivity;
	}

	public boolean isNegativeTransitivity() {
		return negativeTransitivity;
	}

	public void setNegativeTransitivity(boolean negativeTransitivity) {
		this.negativeTransitivity = negativeTransitivity;
	}
}
