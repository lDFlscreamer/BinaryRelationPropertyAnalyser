/*
 * Copyright (c)  2.2020
 * This file (ResultConverter) is part of BinaryRelationPropertyAnalyser.
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by Screamer  <999screamer999@gmail.com>
 */

package org.kpi.TheoryOfDecision.service;


import org.kpi.TheoryOfDecision.entity.RawResult;
import org.kpi.TheoryOfDecision.entity.RelationObj;
import org.kpi.TheoryOfDecision.entity.Result;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;

@Service
public class ResultConverter {
	public String sliceToString(HashMap<Integer, ArrayList<Integer>> slice) {
		return slice.entrySet().stream().
				map(hm1 -> "\t\t".concat(hm1.getKey().toString()).concat("->").concat(
						hm1.getValue().stream().map(Object::toString).reduce((n1, n2) -> n1.concat(",").concat(n2)).orElse("")
				))
				.reduce((line1, line2) -> line1.concat("\n").concat(line2)).orElse("");

	}

	public String exclusionToString(HashMap<Integer, HashMap<Integer, ArrayList<Integer>>> slice) {
		return slice.entrySet().stream().
				map(hm1 -> "\t\t".concat(hm1.getKey().toString()).concat("->").concat(
						hm1.getValue().entrySet().stream().map(
								hm2 -> "\n\t\t\t\t".concat(
										hm2.getKey().toString()).concat("->")
										.concat(
												hm2.getValue().stream().map(Object::toString)
														.reduce((n1, n2) -> n1.concat(",").concat(n2)).orElse(" ")
										)).reduce((line1, line2) -> line1.concat("").concat(line2)).orElse(" ")
						//.stream().map(Object::toString).reduce((n1, n2) -> n1.concat(",").concat(n2)).orElse("")
				))
				.reduce((line1, line2) -> line1.concat("\n").concat(line2)).orElse("");

	}

	public String relationPartToString(ArrayList<RelationObj> arr){
		return arr.stream().map(RelationObj::toString).reduce((s1, s2) -> s1.concat(",").concat(s2)).orElse("empty");
	}
	public RawResult convert(Result result) {
		RawResult rawResult=new RawResult();

		rawResult.setPr(relationPartToString(result.getPr()));
		rawResult.setIr(relationPartToString(result.getIr()));
		rawResult.setNr(relationPartToString(result.getNr()));
		rawResult.setSlice(sliceToString(result.getSlice()));
		rawResult.setNegativeSlice(sliceToString(result.getNegativeSlice()));
		rawResult.setReflectivity(result.isReflectivity());
		rawResult.setAntireflectivity(result.isAntireflectivity());
		rawResult.setSimmetry(result.isSimmetri());
		rawResult.setAsimmetry(result.isAsimmetry());
		rawResult.setAntisimmetry(result.isAntisimmetry());
		rawResult.setAntisimmetry(result.isAntisimmetry());
		rawResult.setTransitivity(result.isTransitivity());
		rawResult.setNegativeTransitivity(result.isNegativeTransitivity());
		rawResult.setTransitivityExclusion(exclusionToString(result.getTransitivityExclusion()));
		rawResult.setNegativeTransitivityExclusion(exclusionToString(result.getNegativeTransitivityExclusion()));
		return rawResult;
	}
}
