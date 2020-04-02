/*
 * Copyright (c)  4.2020
 * This file (Normalizer) is part of BinaryRelationPropertyAnalyser.
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by Screamer  <999screamer999@gmail.com>
 */

package org.kpi.TheoryOfDecision.service.Converter;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class Normalizer {
	public List<Double> normalize(List<Double> weights) {
		Double sum = weights.stream().reduce(Double::sum).orElse(0.0);
		return weights.stream().map(aDouble -> aDouble / sum).collect(Collectors.toList());
	}
}
