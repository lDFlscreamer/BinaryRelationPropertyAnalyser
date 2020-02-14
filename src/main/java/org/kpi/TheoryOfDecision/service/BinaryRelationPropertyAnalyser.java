/*
 * Copyright (c)  2.2020
 * This file (PropertyAnalyser) is part of BinaryRelationPropertyAnalyser.
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by Screamer  <999screamer999@gmail.com>
 */

package org.kpi.TheoryOfDecision.service;


import org.kpi.TheoryOfDecision.entity.RelationObj;
import org.kpi.TheoryOfDecision.entity.propertiesResult.PropertiesResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;


@Service
public class BinaryRelationPropertyAnalyser {

	private static final Logger logger = LoggerFactory.getLogger(BinaryRelationPropertyAnalyser.class);

	public PropertiesResult getBasicProperties(PropertiesResult res) {
		List<List<Integer>> matrix = res.getMatrix();
		ArrayList<RelationObj> Pr = new ArrayList<>();
		ArrayList<RelationObj> Ir = new ArrayList<>();
		ArrayList<RelationObj> Nr = new ArrayList<>();
		HashMap<Integer, List<Integer>> slice = new HashMap<>();
		HashMap<Integer, List<Integer>> negativeSlice = new HashMap<>();
		boolean refl = true;
		boolean antirefl = true;

		for (int i = 0; i < matrix.size(); i++) {
			for (int j = 0; j < matrix.get(i).size(); j++) {
				Integer element = matrix.get(i).get(j);
				if (i == j) {
					antirefl &= (element.equals(0));
					refl &= (element.equals(1));
				}
				Integer symetricalElement = matrix.get(j).get(i);
				if (element == 1) {
					if (symetricalElement.equals(element)) {
						Ir.add(new RelationObj(i + 1, j + 1));
					} else {
						Pr.add(new RelationObj(i + 1, j + 1));
					}
					List<Integer> currentSlice;
					currentSlice = slice.getOrDefault(i + 1, new ArrayList<>());
					currentSlice.add(j + 1);
					slice.put(i + 1, currentSlice);
				} else {
					if (symetricalElement.equals(element)) {
						Nr.add(new RelationObj(i + 1, j + 1));
					}
					List<Integer> currentSlice;
					currentSlice = negativeSlice.getOrDefault(i + 1, new ArrayList<>());
					currentSlice.add(j + 1);
					negativeSlice.put(i + 1, currentSlice);
				}
			}
		}
		res.setSlice(slice);
		res.setPr(Pr);
		res.setIr(Ir);
		res.setNr(Nr);
		res.setReflectivity(refl);
		res.setAntireflective(antirefl);
		res.setNegativeSlice(negativeSlice);
		checkTransitivityProperties(res);
		return res;
	}

	public boolean checkTransitivity(HashMap<Integer, List<Integer>> slice, HashMap<Integer, HashMap<Integer, List<Integer>>> exclusion) {
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

	private boolean checkTransitivityProperties(PropertiesResult result) {
		try {
			List<RelationObj> Pr = result.getPr();
			List<RelationObj> Ir = result.getIr();
			List<RelationObj> Nr = result.getNr();
			boolean antireflective = result.isAntireflective();
			boolean reflectivity = result.isReflectivity();

			boolean symmetry = Pr.isEmpty() && (!Ir.isEmpty());
			boolean antisymmetry = (Ir.isEmpty() || reflectivity) && (!Pr.isEmpty());
			boolean asymmetry = antisymmetry && (antireflective);

			Predicate<RelationObj> nonDiagonal = rel -> rel.getFirst() != rel.getSecond();
			List<RelationObj> NrWithOutDiagonal = Nr.stream().filter(nonDiagonal).collect(Collectors.toList());
			boolean connectedness = Nr.isEmpty();
			boolean weakConnectedness = NrWithOutDiagonal.isEmpty();

			boolean transitivity = checkTransitivity(result.getSlice(), result.getTransitivityExclusion());
			boolean negativeTransitivity = checkTransitivity(result.getNegativeSlice(), result.getNegativeTransitivityExclusion());

			result.setSymmetry(symmetry);
			result.setAsymmetry(asymmetry);
			result.setAntisymmetry(antisymmetry);
			result.setConnectedness(connectedness);
			result.setWeakConnectedness(weakConnectedness);
			result.setTransitivity(transitivity);
			result.setNegativeTransitivity(negativeTransitivity);
			return true;
		} catch (Exception e) {
			logger.error(String.format("Cause error: %s", e.getMessage()), e);
		}
		return false;
	}
}
