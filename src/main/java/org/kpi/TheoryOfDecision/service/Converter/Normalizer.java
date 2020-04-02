/*
 * Copyright (c)  4.2020
 * This file (Normalizer) is part of BinaryRelationPropertyAnalyser.
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by Screamer  <999screamer999@gmail.com>
 */

package org.kpi.TheoryOfDecision.service.Converter;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class Normalizer {
	public List<Double> normalize(List<Double> weights) {
		Double sum = weights.stream().reduce(Double::sum).orElse(0.0);
		return weights.stream().map(aDouble -> aDouble / sum).collect(Collectors.toList());
	}

	public List<List<Double>> normalizeCriteial(List<List<Double>>  weights) {
		List<Double> denominators = new ArrayList<>();
		for (List<Double> weight : weights) {
			for (int j = 0; j < weight.size(); j++) {
				double value = Math.pow(weight.get(j), 2);
				if (denominators.size() <= j) {
					denominators.add(value);
				} else {
					value = value + denominators.get(j);
					denominators.set(j, value);
				}
			}
		}
		denominators=denominators.stream().map(Math::sqrt).collect(Collectors.toList());
		for (int i = 0; i < weights.size(); i++) {
			List<Double> temp = new ArrayList<>();
			for (int j = 0; j < weights.get(i).size(); j++) {
				temp.add(weights.get(i).get(j)/denominators.get(j));
			}
			weights.set(i,temp);
		}

		return weights;
	}


}
