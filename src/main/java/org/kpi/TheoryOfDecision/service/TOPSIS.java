/*
 * Copyright (c)  4.2020
 * This file (TOPSIS) is part of BinaryRelationPropertyAnalyser.
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by Screamer  <999screamer999@gmail.com>
 */

package org.kpi.TheoryOfDecision.service;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TOPSIS {
	public List<List<Double>> getRelation(List<List<Double>> criteria, List<Double> importance, List<Integer> KPlus) {
		List<List<Double>> afterWeight = new ArrayList<>(),
				resultD = new ArrayList<>(),
				resultC = new ArrayList<>();
		List<Double> MaxList = new ArrayList<>(),
				MinList = new ArrayList<>();
		for (List<Double> criterion : criteria) {
			ArrayList<Double> line = new ArrayList<>();
			for (int j = 0; j < criterion.size(); j++) {
				line.add(importance.get(j) * criterion.get(j));
			}
			afterWeight.add(line);
		}

		for (List<Double> criterial : afterWeight) {
			for (int j = 0; j < criterial.size(); j++) {
				if (MinList.size() <= j || MaxList.size() <= j) {
					MinList.add(criterial.get(j));
					MaxList.add(criterial.get(j));
				} else {
					double max = Double.max(criterial.get(j), MaxList.get(j));
					double min = Double.min(criterial.get(j), MinList.get(j));
					MinList.set(j, min);
					MaxList.set(j, max);
				}
			}
		}


		for (List<Double> doubleList : afterWeight) {
			double dplus = 0, dminus = 0;
			ArrayList<Double> line = new ArrayList<>();
			for (int j = 0; j < doubleList.size(); j++) {
				Double PIS = KPlus.contains(j) ? MaxList.get(j) : MinList.get(j),
						NIS = KPlus.contains(j) ? MinList.get(j) : MaxList.get(j);
				dplus += Math.pow(doubleList.get(j) - PIS, 2);
				dminus += Math.pow(doubleList.get(j) - NIS, 2);
			}
			line.add(Math.sqrt(dplus));
			line.add(Math.sqrt(dminus));
			resultD.add(line);
		}
		for (int i = 0; i < resultD.size(); i++) {
			ArrayList<Double> line = new ArrayList<>();
			line.add(i * 1.0);
			line.add(resultD.get(i).get(1) / (resultD.get(i).get(0) + resultD.get(i).get(1)));
			resultC.add(line);
		}
		resultC = resultC.stream().sorted(Comparator.comparing(doubles -> doubles.get(1))).collect(Collectors.toList());
		Collections.reverse(resultC);
		return resultC;
	}
}
