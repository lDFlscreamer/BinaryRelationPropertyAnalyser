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

	public List<List<Double>> normalizeCriteial(List<List<Double>>  criterial) {
		List<Double> denominators = new ArrayList<>();
		for (List<Double> weight : criterial) {
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
		for (int i = 0; i < criterial.size(); i++) {
			List<Double> temp = new ArrayList<>();
			for (int j = 0; j < criterial.get(i).size(); j++) {
				temp.add(criterial.get(i).get(j)/denominators.get(j));
			}
			criterial.set(i,temp);
		}

		return criterial;
	}

	public List<List<Double>> normalizeCriteial(List<List<Double>>  criterial,List<Integer>KPlus) {
		List<Double> min = new ArrayList<>(),
				max = new ArrayList<>();
		for (List<Double> weight : criterial) {
			for (int j = 0; j < weight.size(); j++) {
				if (min.size() <= j) {
					min.add(weight.get(j));
				} else {
					min.set(j, Double.min(weight.get(j),min.get(j)));
				}
				if (max.size() <= j) {
					max.add(weight.get(j));
				} else {
					max.set(j, Double.max(weight.get(j),max.get(j)));
				}
			}
		}

		for (int i = 0; i < criterial.size(); i++) {
			List<Double> temp = new ArrayList<>();
			for (int j = 0; j < criterial.get(i).size(); j++) {
				double value;
				if(KPlus.contains(j)){
					value = (criterial.get(i).get(j) - min.get(j)) / (max.get(j) - min.get(j));
				}else {
					value=(max.get(j)-criterial.get(i).get(j))/ (max.get(j) - min.get(j));
				}
				temp.add(value);

			}
			criterial.set(i,temp);
		}

		return criterial;
	}
}
