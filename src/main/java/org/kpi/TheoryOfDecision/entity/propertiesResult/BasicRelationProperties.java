/*
 * Copyright (c)  2.2020
 * This file (BasicRelationProperties) is part of BinaryRelationPropertyAnalyser.
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by Screamer  <999screamer999@gmail.com>
 */
package org.kpi.TheoryOfDecision.entity.propertiesResult;

import java.util.List;

public class BasicRelationProperties {
	private final List<?> matrix;
	protected boolean reflectivity;
	protected boolean antireflectivity;
	protected boolean symmetry;
	protected boolean asymmetry;
	protected boolean antisymmetry;
	protected boolean acyclicity;
	protected boolean connectedness;
	protected boolean weakConnectedness;
	protected boolean transitivity;
	protected boolean negativeTransitivity;

	public BasicRelationProperties(List<?> matrix) {
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

	public boolean isSymmetry() {
		return symmetry;
	}

	public void setSymmetry(boolean symmetry) {
		this.symmetry = symmetry;
	}

	public boolean isAsymmetry() {
		return asymmetry;
	}

	public void setAsymmetry(boolean asymmetry) {
		this.asymmetry = asymmetry;
	}

	public boolean isAntisymmetry() {
		return antisymmetry;
	}

	public void setAntisymmetry(boolean antisymmetry) {
		this.antisymmetry = antisymmetry;
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
