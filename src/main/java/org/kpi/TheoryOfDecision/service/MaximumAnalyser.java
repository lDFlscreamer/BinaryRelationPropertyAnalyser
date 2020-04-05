/*
 * Copyright (c)  2.2020
 * This file (MaximumAnalyser) is part of BinaryRelationPropertyAnalyser.
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by Screamer  <999screamer999@gmail.com>
 */

package org.kpi.TheoryOfDecision.service;

import org.kpi.TheoryOfDecision.entity.Desicision.DecisionResult;
import org.kpi.TheoryOfDecision.entity.Desicision.KOptimizeResult;
import org.kpi.TheoryOfDecision.entity.Desicision.NeimanMorgenshternResult;
import org.kpi.TheoryOfDecision.entity.RelationObj;
import org.kpi.TheoryOfDecision.entity.propertiesResult.PropertiesResult;
import org.kpi.TheoryOfDecision.service.Converter.RelationListConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Service
public class MaximumAnalyser {

	@Autowired
	private RelationListConverter relationListConverter;

	public Predicate<Map.Entry<Integer, List<Integer>>> getPredicateAllLineExceptDiag(int size) {
		return s -> (s.getValue().size() == size - 1) && !s.getValue().contains(s.getKey());
	}

	public Predicate<Map.Entry<Integer, List<Integer>>> getPredicateAllLine(int size) {
		return s -> (s.getValue().size() == size);
	}

	public Predicate<Map.Entry<Integer, List<Integer>>> getPredicateAllLineExceptSymmetry(List<RelationObj> Ir) {
		return s -> {
			List<Integer> column = Ir.stream().filter(k -> s.getKey() == k.getSecond()).map(RelationObj::getFirst).collect(Collectors.toList());
			return column.containsAll(s.getValue());
		};
	}

	public List<Integer> check(HashMap<Integer, List<Integer>> slice, Predicate<Map.Entry<Integer, List<Integer>>> predicate) {
		return slice.entrySet().stream().filter(predicate).map(Map.Entry::getKey).collect(Collectors.toList());
	}

	public DecisionResult analyseDominationAndBlocking(PropertiesResult toAnalyse) {
		int size = toAnalyse.getMatrix().size();
		DecisionResult decisionResult = new DecisionResult();

		Predicate<Map.Entry<Integer, List<Integer>>> predicateAllLineExceptDiag = getPredicateAllLineExceptDiag(size);
		Predicate<Map.Entry<Integer, List<Integer>>> predicateAllLine = getPredicateAllLine(size);
		if (toAnalyse.getIr().isEmpty()) {
			List<Integer> max = check(toAnalyse.getNegativeSlice(), predicateAllLineExceptDiag);
			HashMap<Integer, List<Integer>> positiveZeroSlice = relationListConverter.invertSlice(size, toAnalyse.getPositiveSlice());
			List<Integer> largest = check(positiveZeroSlice, predicateAllLine);

			decisionResult.setMaxP(max);
			decisionResult.setLargestP(largest);
			return decisionResult;
		}
		List<Integer> max = check(toAnalyse.getNegativeSlice(), predicateAllLine);
		HashMap<Integer, List<Integer>> positiveZeroSlice = relationListConverter.invertSlice(size, toAnalyse.getPositiveSlice());
		List<Integer> columnZeroExceptDiag = check(positiveZeroSlice, predicateAllLineExceptDiag);
		columnZeroExceptDiag.addAll(check(positiveZeroSlice, predicateAllLine));
		columnZeroExceptDiag.retainAll(max);

		List<Integer> largest = check(toAnalyse.getPositiveSlice(), getPredicateAllLineExceptSymmetry(toAnalyse.getIr()));
		List<Integer> strictlyLargest = check(relationListConverter.invertSlice(size, toAnalyse.getPositiveSlice()), predicateAllLineExceptDiag);

		decisionResult.setMaxR(max);
		decisionResult.setStrictlyMaxR(columnZeroExceptDiag);
		decisionResult.setLargestR(largest);
		decisionResult.setStrictlyLargestR(strictlyLargest);
		return decisionResult;
	}

	public NeimanMorgenshternResult analyseNeimanMorgenshtern(PropertiesResult toAnalyse) {

		int size = toAnalyse.getMatrix().size();
		HashMap<Integer, List<Integer>> column = toAnalyse.getPositiveSlice();
		HashMap<Integer, List<Integer>> line = toAnalyse.getNegativeSlice();
		Set<Integer> Zeroentry = relationListConverter.invertSlice(size, column).keySet();
		Zeroentry.removeAll(column.keySet());
		if (Zeroentry.isEmpty()) {
			return null;
		}
		List<int[]> s0 = Collections.singletonList(Zeroentry.stream().mapToInt(s -> s).toArray());
		return null;
	}

	public Predicate<List<Integer>> checkForResistance(List<List<Integer>> allLine) {
		return s -> {
			boolean fine = true;
			for (List<Integer> line :
					allLine) {
				for (int i = 0; i < line.size(); i++) {
					Integer elem = line.get(i);
					if (elem == 1) {
						fine &= s.get(i) == 1;
					}
				}
			}
			return fine;
		};
	}

	public List<Integer> checkForOPtimum(List<ArrayList<Integer>> k, List<Integer> kMax) {
		return kMax.stream().filter(s -> k.get(s).stream().map(integer -> integer.compareTo(1) == 0).
				reduce((aBoolean, aBoolean2) -> aBoolean & aBoolean2).orElse(false))
				.collect(Collectors.toList());
	}

	public List<Integer> findKMax(List<ArrayList<Integer>> toCheck) {
		// TODO: 25.02.2020 bidlocode
		List<List<Integer>> sorted = toCheck.stream().sorted((integers, t1) -> {
			int f = t1.stream().filter(s1 -> s1.compareTo(1) == 0).reduce(Integer::sum).orElse(0);
			int f2 = integers.stream().filter(s1 -> s1.compareTo(1) == 0).reduce(Integer::sum).orElse(0);

			return Integer.compare(f, f2);
		}).collect(Collectors.toList());
		List<List<Integer>> max = sorted.stream().filter(integers -> {
			int current = integers.stream().filter(s1 -> s1.compareTo(1) == 0).reduce(Integer::sum).orElse(0);
			int first = sorted.get(0).stream().filter(s1 -> s1.compareTo(1) == 0).reduce(Integer::sum).orElse(0);
			return !(current < first);
		}).collect(Collectors.toList());

		max = max.stream().filter(checkForResistance(sorted)).collect(Collectors.toList());
		return max.stream().map(toCheck::indexOf).map(integer -> integer+1).collect(Collectors.toList());
	}

	public KOptimizeResult Koptimize(PropertiesResult toAnalyse) {
		int size = toAnalyse.getMatrix().size();
		KOptimizeResult kOptimizeResult = new KOptimizeResult();

		List<RelationObj> k1Reletion =new ArrayList<>(toAnalyse.getPr());
		k1Reletion.addAll(toAnalyse.getIr());
		k1Reletion.addAll(toAnalyse.getNr());
		ArrayList<ArrayList<Integer>> k1 = relationListConverter.convertRelationListToArray(k1Reletion, size);
		List<Integer> kMax = findKMax(k1);
		kOptimizeResult.setK1(kMax);
		kOptimizeResult.setOptk1(checkForOPtimum(k1,kMax));


		List<RelationObj> k2Reletion = new ArrayList<>(toAnalyse.getPr());
		k2Reletion.addAll(toAnalyse.getNr());
		ArrayList<ArrayList<Integer>> k2 = relationListConverter.convertRelationListToArray(k2Reletion, size);

		List<Integer> kMax2 = findKMax(k2);
		kOptimizeResult.setK2(kMax2);
		kOptimizeResult.setOptk2(checkForOPtimum(k2,kMax2));

		List<RelationObj> k3Reletion = new ArrayList<>(toAnalyse.getPr());
		k3Reletion.addAll(toAnalyse.getIr());
		ArrayList<ArrayList<Integer>> k3 = relationListConverter.convertRelationListToArray(k3Reletion, size);
		List<Integer> kMax3 = findKMax(k3);
		kOptimizeResult.setK3(kMax3);
		kOptimizeResult.setOptk3(checkForOPtimum(k3,kMax3));

		ArrayList<ArrayList<Integer>> k4 = relationListConverter.convertRelationListToArray(toAnalyse.getPr(), size);
		List<Integer> kMax4 = findKMax(k4);
		kOptimizeResult.setK4(kMax4);
		kOptimizeResult.setOptk4(checkForOPtimum(k4,kMax4));

		System.out.println("matrix\n".concat(toAnalyse.getMatrix().stream().map(s->s.stream().map(Object::toString).reduce((s1, s2) -> s1+","+s2).orElse("")).reduce((s, s2) -> s+"\n"+s2).orElse("")).concat("\n"));
		System.out.println("k1\n".concat(k1.stream().map(s->s.stream().map(Object::toString).reduce((s1, s2) -> s1+","+s2).orElse("")).reduce((s, s2) -> s+"\n"+s2).orElse("")).concat("\n"));
		System.out.println("k2\n".concat(k2.stream().map(s->s.stream().map(Object::toString).reduce((s1, s2) -> s1+","+s2).orElse("")).reduce((s, s2) -> s+"\n"+s2).orElse("")).concat("\n"));
		System.out.println("k3\n".concat(k3.stream().map(s->s.stream().map(Object::toString).reduce((s1, s2) -> s1+","+s2).orElse("")).reduce((s, s2) -> s+"\n"+s2).orElse("")).concat("\n"));
		System.out.println("k4\n".concat(k4.stream().map(s->s.stream().map(Object::toString).reduce((s1, s2) -> s1+","+s2).orElse("")).reduce((s, s2) -> s+"\n"+s2).orElse("")).concat("\n"));
		return kOptimizeResult;
	}
}
