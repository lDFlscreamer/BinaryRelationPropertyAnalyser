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
import org.kpi.TheoryOfDecision.service.Converter.RelationListConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;


@Service
public class BinaryRelationPropertyAnalyser {

	private static final Logger logger = LoggerFactory.getLogger(BinaryRelationPropertyAnalyser.class);
	@Autowired
	private RelationListConverter relationListConverter;

	public PropertiesResult getBasicProperties(PropertiesResult res) {
		List<ArrayList<Integer>> matrix = res.getMatrix();
		ArrayList<RelationObj> Pr = new ArrayList<>();
		ArrayList<RelationObj> Ir = new ArrayList<>();
		ArrayList<RelationObj> Nr = new ArrayList<>();
		HashMap<Integer, List<Integer>> slice = new HashMap<>();
		HashMap<Integer, List<Integer>> negativeSlice = new HashMap<>();
		HashMap<Integer, List<Integer>> positiveSlice = new HashMap<>();
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
					List<Integer> currentNegativeSlice;
					List<Integer> currentPositiveSlice;
					currentNegativeSlice = slice.getOrDefault(i + 1, new ArrayList<>());
					currentPositiveSlice = positiveSlice.getOrDefault(j + 1, new ArrayList<>());
					currentNegativeSlice.add(j + 1);
					currentPositiveSlice.add(i+1);
					slice.put(i + 1, currentNegativeSlice);
					positiveSlice.put(j+1,currentPositiveSlice);
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
		res.setNegativeSlice(slice);
		res.setPr(Pr);
		res.setIr(Ir);
		res.setNr(Nr);
		res.setReflectivity(refl);
		res.setAntireflective(antirefl);
		res.setPositiveSlice(positiveSlice);
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

			boolean symmetry = Pr.isEmpty();
			boolean antisymmetry = Ir.stream().allMatch(s->s.getFirst()==s.getSecond());
			boolean asymmetry = antisymmetry && (antireflective);

			Predicate<RelationObj> nonDiagonal = rel -> rel.getFirst() != rel.getSecond();
			List<RelationObj> NrWithOutDiagonal = Nr.stream().filter(nonDiagonal).collect(Collectors.toList());
			boolean connectedness = Nr.isEmpty();
			boolean weakConnectedness = NrWithOutDiagonal.isEmpty();

			boolean transitivity = checkTransitivity(result.getNegativeSlice(), result.getTransitivityExclusion());
			HashMap<Integer, List<Integer>> negativeZeroSlice = relationListConverter.invertSlice(result.getMatrix().size(), result.getNegativeSlice());
			boolean negativeTransitivity = checkTransitivity(negativeZeroSlice, result.getNegativeTransitivityExclusion());

			boolean aCyclic = checkAcyclic(result.getNegativeSlice(), result.getCycleExclusion());
			result.setSymmetry(symmetry);
			result.setAsymmetry(asymmetry);
			result.setAntisymmetry(antisymmetry);
			result.setConnectedness(connectedness);
			result.setWeakConnectedness(weakConnectedness);
			result.setTransitivity(transitivity);
			result.setNegativeTransitivity(negativeTransitivity);
			result.setAcyclic(aCyclic);

			return true;
		} catch (Exception e) {
			logger.error(String.format("Cause error: %s", e.getMessage()), e);
		}
		return false;
	}


	public List<Integer> checkAcyclicitaration(List<Integer> current, HashMap<Integer, List<Integer>> slice) {
		List<Integer> forCheck;
		forCheck = current.isEmpty() ? new ArrayList<>() : slice.getOrDefault(current.get(current.size() - 1), new ArrayList<>());
		for (Integer i :
				forCheck) {
			if (current.contains(i)) {
				current.add(i);
				return current;
			}
			List<Integer> iteration = new ArrayList<>(current);
			iteration.add(i);
			List<Integer> result = checkAcyclicitaration(iteration, slice);
			if (!result.isEmpty()) {
				return result;
			}
		}
		return new ArrayList<>();
	}

	public boolean checkAcyclic(HashMap<Integer, List<Integer>> slice, List<Integer> exclusion) {
		for (Integer i :
				slice.keySet()) {
			List<Integer> current = new ArrayList<>();
			current.add(i);
			List<Integer> exclusionIteration = checkAcyclicitaration(current, slice);
			if (!exclusionIteration.isEmpty()) {
				exclusion.addAll(exclusionIteration);
				return false;
			}
		}
		return true;
	}
}
