/*
 * Copyright (c)  2.2020
 * This file (Result) is part of BinaryRelationPropertyAnalyser.
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by Screamer  <999screamer999@gmail.com>
 */

package org.kpi.TheoryOfDecision.entity;

import java.util.ArrayList;
import java.util.HashMap;

public class Result {
	private ArrayList inputedMatrix;
	private ArrayList<RelationObj> Pr;
	private ArrayList<RelationObj> Ir;
	private ArrayList<RelationObj> Nr;
	private HashMap<Integer, ArrayList<Integer>> slice;
	private HashMap<Integer, HashMap<Integer, ArrayList<Integer>>> transitivityExclusion;
	private HashMap<Integer, HashMap<Integer, ArrayList<Integer>>> negativeTransitivityExclusion;
	private HashMap<Integer, ArrayList<Integer>> negativeSlice;
	private boolean reflectivity;
	private boolean antireflectivity;
	private boolean simmetri;
	private boolean asimmetry;
	private boolean antisimmetry;
	private boolean transitivity;
	private boolean negativeTransitivity;

	public Result(ArrayList<RelationObj> pr, ArrayList<RelationObj> ir, HashMap<Integer, ArrayList<Integer>> slice) {
		Pr = pr;
		Ir = ir;
		this.slice = slice;
	}

	public Result() {
		Pr = new ArrayList<>();
		transitivityExclusion = new HashMap<>();
		negativeTransitivityExclusion = new HashMap<>();
		Ir = new ArrayList<>();
	}

	public ArrayList<RelationObj> getNr() {
		return Nr;
	}

	public void setNr(ArrayList<RelationObj> nr) {
		Nr = nr;
	}


	public void setInputedMatrix(ArrayList inputedMatrix) {
		this.inputedMatrix = inputedMatrix;
	}

	public HashMap<Integer, ArrayList<Integer>> getNegativeSlice() {
		return negativeSlice;
	}

	public void setNegativeSlice(HashMap<Integer, ArrayList<Integer>> negativeSlice) {
		this.negativeSlice = negativeSlice;
	}

	public boolean isNegativeTransitivity() {
		return negativeTransitivity;
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

	public void setAntiReflection(boolean antireflection) {
		this.antireflectivity = antireflection;
	}

	public boolean isSimmetri() {
		return simmetri;
	}

	public boolean isAsimmetry() {
		return asimmetry;
	}

	public boolean isAntisimmetry() {
		return antisimmetry;
	}

	public boolean isTransitivity() {
		return transitivity;
	}

	public ArrayList<RelationObj> getPr() {
		return Pr;
	}

	public void setPr(ArrayList<RelationObj> pr) {
		Pr = pr;
	}

	public ArrayList<RelationObj> getIr() {
		return Ir;
	}

	public void setIr(ArrayList<RelationObj> ir) {
		Ir = ir;
	}

	public HashMap<Integer, ArrayList<Integer>> getSlice() {
		return slice;
	}

	public void setSlice(HashMap<Integer, ArrayList<Integer>> slice) {
		this.slice = slice;
	}

	public void analyse() {
		simmetri = Pr.isEmpty() && (!Ir.isEmpty());
		antisimmetry = (Ir.isEmpty() || reflectivity) && (!Pr.isEmpty());
		asimmetry = antisimmetry && (antireflectivity);
		transitivity = checktransitivity(slice, transitivityExclusion);
		negativeTransitivity = checktransitivity(negativeSlice, negativeTransitivityExclusion);
	}

	public HashMap<Integer, HashMap<Integer, ArrayList<Integer>>> getTransitivityExclusion() {
		return transitivityExclusion;
	}

	public HashMap<Integer, HashMap<Integer, ArrayList<Integer>>> getNegativeTransitivityExclusion() {
		return negativeTransitivityExclusion;
	}

	public boolean checktransitivity(HashMap<Integer, ArrayList<Integer>> slice, HashMap<Integer, HashMap<Integer, ArrayList<Integer>>> exclusion) {
		for (Integer i :
				slice.keySet()) {
			ArrayList<Integer> current = slice.get(i);
			for (Integer j :
					current) {
				if (i.compareTo(j) == 0) {
					continue;
				}
				ArrayList<Integer> inner = slice.getOrDefault(j, new ArrayList<>());
				if (!current.containsAll(inner)) {
					HashMap<Integer, ArrayList<Integer>> exclusionCurrent = exclusion.getOrDefault(i, new HashMap<>());
					ArrayList<Integer> innerCheck = new ArrayList<>(inner);
					innerCheck.removeAll(current);
					exclusionCurrent.put(j, innerCheck);
					exclusion.put(i, exclusionCurrent);
					return false;
				}
			}
		}
		return true;
	}


}
