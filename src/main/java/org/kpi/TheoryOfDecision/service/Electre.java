/*
 * Copyright (c)  3.2020
 * This file (Electre) is part of BinaryRelationPropertyAnalyser.
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by Screamer  <999screamer999@gmail.com>
 */

package org.kpi.TheoryOfDecision.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class Electre {

	@Autowired
	BinaryRelationPropertyAnalyser propertyAnalyser;

	public List<Integer> findPart(ArrayList<Double> first, ArrayList<Double> second, int value) {
		ArrayList<Integer> result = new ArrayList<>();
		for (int i = 0; i < first.size(); i++) {
			if (first.get(i).compareTo(second.get(i)) == value) {
				result.add(i);
			}
		}
		return result;
	}


	public double cordance(Double positivePart, Double negativePart, Double equalPart, List<Double> importance, double c) {
		if (c < 0.5) {
			return 0;
		}
		return c > 1 ? positivePart / negativePart :
				((positivePart + equalPart) /
						importance.stream().reduce(Double::sum).orElse(0.0));
	}

	public double discordance(List<Integer> negativePart, List<Double> diference, List<Double> importance) {
		if (negativePart.isEmpty()) return 0.0;
		double numerator = 0;
		double denominator = 0;

		for (int i = 0; i < importance.size(); i++) {
			double value = Math.abs(importance.get(i) * diference.get(i));
			if (negativePart.contains(i)) {
				numerator = Math.max(numerator, value);
			}
			denominator = Math.max(denominator, value);
		}
		return numerator / (denominator * 1.0);
	}


	public List<List<Double>> getConcordanceViaElectre(List<ArrayList<Double>> criteria, List<Double> importance, double c) {
		List<List<Double>> result = new ArrayList<>();

		List<Integer> positivePart, negativePart, equalPart;
		Double pP, pN, pE;

		for (int indexL1 = 0; indexL1 < criteria.size(); indexL1++) {
			for (int indexL2 = indexL1; indexL2 < criteria.size(); indexL2++) {
				if (indexL1 == indexL2) {
					List<Double> line = result.size() > indexL1 ? result.get(indexL1) : new ArrayList<>();
					line.add(indexL1, Double.MIN_VALUE);
					if (result.size() > indexL1) {
						result.set(indexL1, line);
					} else {
						result.add(indexL1, line);
					}
					continue;
				}
				positivePart = findPart(criteria.get(indexL1), criteria.get(indexL2), 1);
				negativePart = findPart(criteria.get(indexL1), criteria.get(indexL2), -1);
				equalPart = findPart(criteria.get(indexL1), criteria.get(indexL2), 0);

				pP = positivePart.stream().map(importance::get).reduce(Double::sum).orElse(0.0);
				pN = negativePart.stream().map(importance::get).reduce(Double::sum).orElse(0.0);
				pE = equalPart.stream().map(importance::get).reduce(Double::sum).orElse(0.0);
				double condordance = this.cordance(pP, pN, pE, importance, c);
				double condordance1 = this.cordance(pN, pP, pE, importance, c);

				List<Double> line = result.size() > indexL1 ? result.get(indexL1) : new ArrayList<>();
				line.add(condordance);
				if (result.size() > indexL1) {
					result.set(indexL1, line);
				} else {
					result.add(indexL1, line);
				}


				line = result.size() > indexL2 ? result.get(indexL2) : new ArrayList<>();
				line.add(condordance1);
				if (result.size() > indexL2) {
					result.set(indexL2, line);
				} else {
					result.add(indexL2, line);
				}
			}
		}
		return result;
	}

	public List<Double> getDifference(List<Double> l1, List<Double> l2) {
		ArrayList<Double> result = new ArrayList<>();
		for (int i = 0; i < l1.size(); i++) {
			double v = l1.get(i) - l2.get(i);
			result.add(v);
		}
		return result;
	}

	public List<List<Double>> getDisconcordanceViaElectre(List<ArrayList<Double>> criteria, List<Double> importance, double d) {
		List<List<Double>> result = new ArrayList<>();

		List<Integer> positivePart, negativePart, equalPart;
		Double pP, pN, pE;

		for (int indexL1 = 0; indexL1 < criteria.size(); indexL1++) {
			for (int indexL2 = indexL1; indexL2 < criteria.size(); indexL2++) {
				if (indexL1 == indexL2) {
					List<Double> line = result.size() > indexL1 ? result.get(indexL1) : new ArrayList<>();
					line.add(indexL1, Double.MAX_VALUE);
					if (result.size() > indexL1) {
						result.set(indexL1, line);
					} else {
						result.add(indexL1, line);
					}
					continue;
				}
				ArrayList<Double> l1 = criteria.get(indexL1);
				ArrayList<Double> l2 = criteria.get(indexL2);

				positivePart = findPart(l1, l2, 1);
				negativePart = findPart(l1, l2, -1);
				List<Double> difference = this.getDifference(l1, l2);

				double discondordance = this.discordance(negativePart, difference, importance);
				difference = this.getDifference(l2, l1);
				double discondordance1 = this.discordance(positivePart, difference, importance);

				List<Double> line = result.size() > indexL1 ? result.get(indexL1) : new ArrayList<>();
				line.add(discondordance);
				if (result.size() > indexL1) {
					result.set(indexL1, line);
				} else {
					result.add(indexL1, line);
				}


				line = result.size() > indexL2 ? result.get(indexL2) : new ArrayList<>();
				line.add(discondordance1);
				if (result.size() > indexL2) {
					result.set(indexL2, line);
				} else {
					result.add(indexL2, line);
				}
			}
		}
		return result;
	}

	public List<List<Integer>> getRelation(List<List<Integer>> concordance, List<List<Integer>> discordance) {
		List<List<Integer>> result = new ArrayList<>();

		for (int i = 0; i < concordance.size(); i++) {
			List<Integer> line = new ArrayList<>();
			for (int j = 0; j < concordance.get(i).size(); j++) {
				Integer concordanceEl = concordance.get(i).get(j);
				Integer dicordanceEl = discordance.get(i).get(j);
				int element = concordanceEl * dicordanceEl;
				line.add(element);
			}
			result.add(line);
		}
		return result;
	}
}
