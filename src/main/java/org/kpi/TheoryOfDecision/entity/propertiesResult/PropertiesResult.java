/*
 * Copyright (c)  2.2020
 * This file (PropertiesResult) is part of BinaryRelationPropertyAnalyser.
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by Screamer  <999screamer999@gmail.com>
 */
package org.kpi.TheoryOfDecision.entity.propertiesResult;

import org.kpi.TheoryOfDecision.entity.RelationObj;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class PropertiesResult extends BasicRelationProperties {
	protected List<RelationObj> Pr;
	protected List<RelationObj> Ir;
	protected List<RelationObj> Nr;
	protected HashMap<Integer, List<Integer>> slice;
	protected HashMap<Integer, HashMap<Integer, List<Integer>>> transitivityExclusion;
	protected HashMap<Integer, HashMap<Integer, List<Integer>>> negativeTransitivityExclusion;
	protected List<Integer> cycleExclusion;
	protected HashMap<Integer, List<Integer>> negativeSlice;

	public PropertiesResult(List<List<Integer>> inputedMatrix, List<RelationObj> pr, List<RelationObj> ir, HashMap<Integer, List<Integer>> slice) {
		super(inputedMatrix);
		this.Pr = pr;
		this.Ir = ir;
		this.slice = slice;
	}
	public PropertiesResult(List<List<Integer>> inputedMatrix) {
		super(inputedMatrix);
		Pr = new ArrayList<>();
		transitivityExclusion = new HashMap<>();
		negativeTransitivityExclusion = new HashMap<>();
		cycleExclusion = new ArrayList<>();
		Ir = new ArrayList<>();
	}

	public List<Integer> getCycleExclusion() {
		return cycleExclusion;
	}

	public void setCycleExclusion(List<Integer> cycleExclusion) {
		this.cycleExclusion = cycleExclusion;
	}

	public List<RelationObj> getNr() {
		return Nr;
	}

	public void setNr(List<RelationObj> nr) {
		Nr = nr;
	}

	public HashMap<Integer, List<Integer>> getNegativeSlice() {
		return negativeSlice;
	}

	public void setNegativeSlice(HashMap<Integer, List<Integer>> negativeSlice) {
		this.negativeSlice = negativeSlice;
	}

	public List<RelationObj> getPr() {
		return Pr;
	}

	public void setPr(List<RelationObj> pr) {
		Pr = pr;
	}

	public List<RelationObj> getIr() {
		return Ir;
	}

	public void setIr(List<RelationObj> ir) {
		Ir = ir;
	}

	public HashMap<Integer, List<Integer>> getSlice() {
		return slice;
	}

	public void setSlice(HashMap<Integer, List<Integer>> slice) {
		this.slice = slice;
	}

	public HashMap<Integer, HashMap<Integer, List<Integer>>> getTransitivityExclusion() {
		return transitivityExclusion;
	}

	public HashMap<Integer, HashMap<Integer, List<Integer>>> getNegativeTransitivityExclusion() {
		return negativeTransitivityExclusion;
	}

}
