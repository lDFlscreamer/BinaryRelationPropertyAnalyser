/*
 * Copyright (c)  2.2020
 * This file (PropertiesResult) is part of BinaryRelationPropertyAnalyser.
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by Screamer  <999screamer999@gmail.com>
 */
package org.kpi.TheoryOfDecision.entity.propertiesResult;

import org.kpi.TheoryOfDecision.entity.RelationObj;
import org.kpi.TheoryOfDecision.entity.propertiesResult.BasicRelationProperties;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class PropertiesResult extends BasicRelationProperties {

	private List<RelationObj> Pr;
	private List<RelationObj> Ir;
	private List<RelationObj> Nr;
	private HashMap<Integer, List<Integer>> slice;
	private HashMap<Integer, HashMap<Integer, List<Integer>>> transitivityExclusion;
	private HashMap<Integer, HashMap<Integer, List<Integer>>> negativeTransitivityExclusion;
	private HashMap<Integer, List<Integer>> negativeSlice;

	public PropertiesResult(List<List<Integer>> inputedMatrix, List<RelationObj> pr, List<RelationObj> ir, HashMap<Integer, List<Integer>> slice) {
		super(inputedMatrix);
		this.Pr = pr;
		this.Ir = ir;
		this.slice = slice;
	}

	public PropertiesResult(List<?> inputedMatrix) {
		super(inputedMatrix);
		Pr = new ArrayList<>();
		transitivityExclusion = new HashMap<>();
		negativeTransitivityExclusion = new HashMap<>();
		Ir = new ArrayList<>();
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
