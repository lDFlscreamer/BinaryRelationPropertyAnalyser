/*
 * Copyright (c)  2.2020
 * This file (RelationListConverter) is part of BinaryRelationPropertyAnalyser.
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by Screamer  <999screamer999@gmail.com>
 */

package org.kpi.TheoryOfDecision.service.Converter;

import org.kpi.TheoryOfDecision.entity.RelationObj;
import org.kpi.TheoryOfDecision.entity.propertiesResult.PropertiesResult;
import org.kpi.TheoryOfDecision.service.BinaryRelationPropertyAnalyser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RelationListConverter {

	@Autowired
	private BinaryRelationPropertyAnalyser binaryRelationPropertyAnalyser;

	public PropertiesResult checkAcyclic(PropertiesResult toRemoveCycle) {

		List<ArrayList<Integer>> result = toRemoveCycle.getMatrix();
		PropertiesResult currentResult = new PropertiesResult(result);
		HashMap<Integer, List<Integer>> slice ;
		while (true) {
			currentResult.setMatrix(result);
			PropertiesResult basicProperties = binaryRelationPropertyAnalyser.getBasicProperties(currentResult);
			slice = basicProperties.getNegativeSlice();
			for (Integer i :
					slice.keySet()) {
				List<Integer> current = new ArrayList<>();
				current.add(i);
				List<Integer> exclusionIteration = binaryRelationPropertyAnalyser.checkAcyclicitaration(current, slice);
				if (!exclusionIteration.isEmpty()) {
					int exclusionSize = exclusionIteration.size();
					Integer k = exclusionIteration.get(exclusionSize - 1);
					Integer j = exclusionIteration.get(exclusionSize - 2);
					result.get(j - 1).set(k - 1, 0);
				} else {
					return basicProperties;
				}
			}
		}
	}

	public HashMap<Integer, List<Integer>> invertSlice(int size, HashMap<Integer, List<Integer>> sliceToInvert) {
		HashMap<Integer, List<Integer>> result = new HashMap<>();
		for (int i = 0; i < size; i++) {
			List<Integer> lineToInvert = sliceToInvert.getOrDefault(i + 1, null);
			ArrayList<Integer> resultedLine = new ArrayList<>();
			for (int j = 0; j < size; j++) {
				if (lineToInvert == null || !(lineToInvert.contains(j + 1))) {
					resultedLine.add(j + 1);
				}
			}
			if (resultedLine.isEmpty()) {
				continue;
			}
			result.put(i + 1, resultedLine);
		}
		return result;
	}

	public ArrayList<Integer> replace(ArrayList<Integer> existing, RelationObj replacement) {

		if (existing == null) {
			ArrayList<Integer> integers = new ArrayList<>();
			integers.add(replacement.getSecond());
			return integers;
		}
		existing.add(replacement.getSecond());
		return existing;
	}

	public ArrayList<ArrayList<Integer>> convertRelationListToArray(List<RelationObj> toConvert, int size) {
		ArrayList<ArrayList<Integer>> resulted = new ArrayList<>();
		toConvert.sort(RelationObj::compareTo);
		for (int i = 0; i < size; i++) {
			int finalI = i;
			List<Integer> linePart = toConvert.stream().filter(s -> s.getFirst()-1 == finalI).map(s -> s.getSecond()-1).collect(Collectors.toList());
			ArrayList<Integer> line = new ArrayList<>();
			for (int j = 0; j < size; j++) {
				line.add(linePart.contains(j) ? 1 : 0);
			}
			resulted.add(line);
		}

		return resulted;
	}
}
