/*
 * Copyright (c)  4.2020
 * This file (TOPSIS) is part of BinaryRelationPropertyAnalyser.
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by Screamer  <999screamer999@gmail.com>
 */

package org.kpi.TheoryOfDecision.service;

import org.kpi.TheoryOfDecision.entity.VIKOR_Result;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class VIKOR {

	public VIKOR_Result getRelation(List<List<Double>> criteria, List<Double> importance, double v) {
		List<List<Double>> resultQ = new ArrayList<>(),
				resultR = new ArrayList<>(),
				resultS = new ArrayList<>();
		List<Double> maxList = criteria.stream().map(s -> s.stream().max(Double::compareTo).orElse(0.0))
				.collect(Collectors.toList());
		List<Double> minList = criteria.stream().map(s -> s.stream().min(Double::compareTo).orElse(0.0))
				.collect(Collectors.toList());

		List<Double> maxInterval = minList.stream().map(s -> 0.0).collect(Collectors.toList()),
				averageInterval = minList.stream().map(s -> 0.0).collect(Collectors.toList()),
				listQ = minList.stream().map(s -> 0.0).collect(Collectors.toList());
		for (int i = 0; i < criteria.size(); i++) {
			for (int j = 0; j < criteria.get(i).size(); j++) {
				double variable = (importance.get(j) * Math.abs(maxList.get(j) - criteria.get(i).get(j))) / (maxList.get(j) - minList.get(j));
				averageInterval.set(i, averageInterval.get(i) + variable);
				maxInterval.set(i, maxInterval.get(i) > variable ? maxInterval.get(i) : variable);
			}
		}
		Double minS = averageInterval.stream().min(Double::compareTo).orElse(0.0),
				maxS = averageInterval.stream().max(Double::compareTo).orElse(1.0),
				maxR = maxInterval.stream().max(Double::compareTo).orElse(0.0),
				minR = maxInterval.stream().min(Double::compareTo).orElse(1.0);
		for (int i = 0; i < averageInterval.size(); i++) {
			listQ.set(i, (v * (averageInterval.get(i) - minS) / (maxS - minS)) + ((1 - v) * (maxInterval.get(i) - minR) / (maxR - minR)));
		}
		for (int i = 0; i < averageInterval.size(); i++) {
			ArrayList<Double> temp = new ArrayList<>();
			temp.add(i * 1.0);
			temp.add(listQ.get(i));
			resultQ.add(temp);
			temp = new ArrayList<>();
			temp.add(i * 1.0);
			temp.add(maxInterval.get(i));
			resultR.add(temp);
			temp = new ArrayList<>();
			temp.add(i * 1.0);
			temp.add(averageInterval.get(i));
			resultS.add(temp);
		}
		resultQ = resultQ.stream().sorted(Comparator.comparing(doubles -> doubles.get(1))).collect(Collectors.toList());
		resultS = resultS.stream().sorted(Comparator.comparing(doubles -> doubles.get(1))).collect(Collectors.toList());
		resultR = resultR.stream().sorted(Comparator.comparing(doubles -> doubles.get(1))).collect(Collectors.toList());
		return new VIKOR_Result(resultQ, resultR, resultS);
	}
}
