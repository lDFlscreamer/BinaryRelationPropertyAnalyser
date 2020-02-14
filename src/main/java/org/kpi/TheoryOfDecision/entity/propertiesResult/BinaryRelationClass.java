/*
 * Copyright (c)  2.2020
 * This file (BinaryClass) is part of BinaryRelationPropertyAnalyser.
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by Screamer  <999screamer999@gmail.com>
 */

package org.kpi.TheoryOfDecision.entity.propertiesResult;

public class BinaryRelationClass {
	protected boolean reflectivity;
	protected boolean antireflective;
	protected boolean symmetry;
	protected boolean asymmetry;
	protected boolean antisymmetry;
	protected boolean acyclic;
	protected boolean connectedness;
	protected boolean weakConnectedness;
	protected boolean transitivity;
	protected boolean negativeTransitivity;

	public BinaryRelationClass(boolean reflectivity, boolean antireflective, boolean symmetry, boolean asymmetry, boolean antisymmetry, boolean transitivity, boolean negativeTransitivity, boolean connectedness, boolean weakConnectedness, boolean acyclic) {
		this.reflectivity = reflectivity;
		this.antireflective = antireflective;
		this.symmetry = symmetry;
		this.asymmetry = asymmetry;
		this.antisymmetry = antisymmetry;
		this.acyclic = acyclic;
		this.connectedness = connectedness;
		this.weakConnectedness = weakConnectedness;
		this.transitivity = transitivity;
		this.negativeTransitivity = negativeTransitivity;
	}

	public BinaryRelationClass() {
	}

	public boolean isReflectivity() {
		return reflectivity;
	}

	public void setReflectivity(boolean reflectivity) {
		this.reflectivity = reflectivity;
	}

	public boolean isAntireflective() {
		return antireflective;
	}

	public void setAntireflective(boolean antireflective) {
		this.antireflective = antireflective;
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

	public boolean isAcyclic() {
		return acyclic;
	}

	public void setAcyclic(boolean acyclic) {
		this.acyclic = acyclic;
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
