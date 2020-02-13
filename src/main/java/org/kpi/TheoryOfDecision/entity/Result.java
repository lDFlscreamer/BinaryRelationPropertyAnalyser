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
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Result {
	private final List<?> inputedMatrix;
	private List<RelationObj> Pr;
	private List<RelationObj> Ir;
	private List<RelationObj> Nr;
	private HashMap<Integer, List<Integer>> slice;
	private HashMap<Integer, HashMap<Integer, List<Integer>>> transitivityExclusion;
	private HashMap<Integer, HashMap<Integer, List<Integer>>> negativeTransitivityExclusion;
	private HashMap<Integer, List<Integer>> negativeSlice;
	private boolean reflectivity;
	private boolean antireflectivity;
	private boolean simmetri;
	private boolean asimmetry;
	private boolean antisimmetry;
	private boolean acyclicity;
	private boolean connectedness;
	private boolean weakConnectedness;
	private boolean transitivity;
	private boolean negativeTransitivity;

	public Result(List<List<Integer>> inputedMatrix,List<RelationObj> pr, List<RelationObj> ir, HashMap<Integer, List<Integer>> slice) {
		this.inputedMatrix = inputedMatrix;
		this.Pr = pr;
		this.Ir = ir;
		this.slice = slice;
	}

	public Result(List<?> inputedMatrix) {
		this.inputedMatrix = inputedMatrix;
		Pr = new ArrayList<>();
		transitivityExclusion = new HashMap<>();
		negativeTransitivityExclusion = new HashMap<>();
		Ir = new ArrayList<>();
	}

	public boolean isAcyclicity() {
		return acyclicity;
	}

	public boolean isConnectedness() {
		return connectedness;
	}

	public boolean isWeakConnectedness() {
		return weakConnectedness;
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

	public void analyse() {
		simmetri = Pr.isEmpty() && (!Ir.isEmpty());
		antisimmetry = (Ir.isEmpty() || reflectivity) && (!Pr.isEmpty());
		asimmetry = antisimmetry && (antireflectivity);
		Predicate<RelationObj> nonDiagonal = rel -> rel.getFirst() != rel.getSecond();
		List<RelationObj> NrWithOutDiagonal = Nr.stream().filter(nonDiagonal).collect(Collectors.toList());
		connectedness = Nr.isEmpty();
		weakConnectedness = NrWithOutDiagonal.isEmpty();
		transitivity = checktransitivity(slice, transitivityExclusion);
		negativeTransitivity = checktransitivity(negativeSlice, negativeTransitivityExclusion);
	}

	public HashMap<Integer, HashMap<Integer, List<Integer>>> getTransitivityExclusion() {
		return transitivityExclusion;
	}

	public HashMap<Integer, HashMap<Integer, List<Integer>>> getNegativeTransitivityExclusion() {
		return negativeTransitivityExclusion;
	}

	public boolean checktransitivity(HashMap<Integer, List<Integer>> slice, HashMap<Integer, HashMap<Integer, List<Integer>>> exclusion) {
		for (Integer i :
				slice.keySet()) {
			List<Integer> current = slice.get(i);
			for (Integer j :
					current) {
				if (i.compareTo(j) == 0) {
					continue;
				}
				List<Integer> inner = slice.getOrDefault(j, new ArrayList<>());
				if (!current.containsAll(inner)) {
					HashMap<Integer, List<Integer>> exclusionCurrent = exclusion.getOrDefault(i, new HashMap<>());
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
