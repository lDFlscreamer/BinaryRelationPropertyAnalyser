/*
 * Copyright (c)  3.2020
 * This file (RelationDetectController) is part of BinaryRelationPropertyAnalyser.
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by Screamer  <999screamer999@gmail.com>
 */

package org.kpi.TheoryOfDecision.controler;

import org.kpi.TheoryOfDecision.service.Converter.Normalizer;
import org.kpi.TheoryOfDecision.service.Electre;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/Relation")
public class RelationDetectController {
	@Autowired
	private Electre electre;
	@Autowired
	private Normalizer normalizer;


	@PostMapping("/ElectreConcorance")
	@ResponseStatus(value = HttpStatus.ACCEPTED)
	public List<List<Double>> getConcorace(@RequestBody HashMap<String, Object> param) {
		Object matrix = param.getOrDefault("Matrix", null);
		Object importance = param.getOrDefault("importance", null);
		double c = (double) param.getOrDefault("c", null);
		if (matrix == null || importance == null) {
			return null;
		}

		List<List<Double>> concordanceViaElectre = electre.getConcordanceViaElectre((List<ArrayList<Double>>) matrix, (List<Double>) importance, c);
		String resultStr = concordanceViaElectre.stream().map(s -> s.stream().map(Object::toString).reduce((s1, s2) -> s1.concat("\t\t\t,\t\t\t").concat(s2)).orElse(" ")).reduce((s, s2) -> s.concat("\n").concat(s2)).orElse("");
		System.out.println(resultStr);
		return concordanceViaElectre;
	}

	@PostMapping("/ElectreDiscordance")
	@ResponseStatus(value = HttpStatus.ACCEPTED)
	public List<List<Double>> getDisconcorace(@RequestBody HashMap<String, Object> param) {
		Object matrix = param.getOrDefault("Matrix", null);
		Object importance = param.getOrDefault("importance", null);
		double d = (double) param.getOrDefault("d", null);
		if (matrix == null || importance == null) {
			return null;
		}

		List<List<Double>> disconcordanceViaElectre = electre.getDisconcordanceViaElectre((List<ArrayList<Double>>) matrix, (List<Double>) importance, d);
		String resultStr = disconcordanceViaElectre.stream().map(s -> s.stream().map(Object::toString).reduce((s1, s2) -> s1.concat("\t\t\t,\t\t\t").concat(s2)).orElse(" ")).reduce((s, s2) -> s.concat("\n").concat(s2)).orElse("");
		System.out.println(resultStr);
		return disconcordanceViaElectre;
	}


	@PostMapping("/GetRelationViaElectre")
	@ResponseStatus(value = HttpStatus.ACCEPTED)
	public HashMap<String, List<?>> getRelationviaElectre(@RequestBody HashMap<String, Object> param) {
		Object matrix = param.getOrDefault("Matrix", null);
		Object importance = param.getOrDefault("importance", null);
		double d = (double) param.getOrDefault("d", null);
		double c = (double) param.getOrDefault("c", null);
		if (matrix == null || importance == null) {
			return null;
		}

		HashMap<String, List<?>> result = new HashMap<>();
		List<List<Double>> disconcordanceViaElectre = electre.getDisconcordanceViaElectre((List<ArrayList<Double>>) matrix, (List<Double>) importance, d);
		List<List<Double>> concordanceViaElectre = electre.getConcordanceViaElectre((List<ArrayList<Double>>) matrix, (List<Double>) importance, c);
			result.put("discordance", disconcordanceViaElectre);
			result.put("concordance", concordanceViaElectre);
		List<List<Integer>> concordanceAfterCompare = concordanceViaElectre.stream().map(s -> s.stream().map(s1 -> s1 >= c ? 1 : 0).collect(Collectors.toList())).collect(Collectors.toList());
		List<List<Integer>> discordanceAfterCompare = disconcordanceViaElectre.stream().map(s -> s.stream().map(s1 -> s1 <= d ? 1 : 0).collect(Collectors.toList())).collect(Collectors.toList());
			result.put("discordanceAfterCompare", discordanceAfterCompare);
			result.put("concordanceAfterCompare", concordanceAfterCompare);
		System.out.println("discordanceAfterCompare = \n" + ListToString(discordanceAfterCompare));
		System.out.println("concordanceAfterCompare = \n" + ListToString(concordanceAfterCompare));
		List<List<Integer>> relation = electre.getRelation(concordanceAfterCompare, discordanceAfterCompare);
		result.put("Relation", relation);
		System.out.println("resultStr :\n " + ListToString(relation));
		return result;
	}

	public String ListToString(List<List<Integer>> arr){

		return arr.stream().map(s -> s.stream().map(Object::toString).reduce((s1, s2) -> s1.concat("\t\t\t,\t\t\t").concat(s2)).orElse(" ")).reduce((s, s2) -> s.concat("\n").concat(s2)).orElse("");

	}

	@PostMapping("/GetRelationViaVIKOR")
	@ResponseStatus(value = HttpStatus.ACCEPTED)
	public HashMap<String, List<?>> getRelationViaVIKOR(@RequestBody HashMap<String, Object> param) {
		Object matrix = param.getOrDefault("Matrix", null);
		Object importance = param.getOrDefault("importance", null);
		double d = (double) param.getOrDefault("d", null);
		double c = (double) param.getOrDefault("c", null);
		if (matrix == null || importance == null) {
			return null;
		}

		HashMap<String, List<?>> result = new HashMap<>();
		List<Double> normalized = normalizer.normalize((List<Double>) importance);
		result.put("Relation", normalized);
		return result;
	}

	@PostMapping("/GetRelationViaTOPSIS")
	@ResponseStatus(value = HttpStatus.ACCEPTED)
	public HashMap<String, List<?>> getRelationViaTOPSIS(@RequestBody HashMap<String, Object> param) {
		Object matrix = param.getOrDefault("Matrix", null);
		Object importance = param.getOrDefault("importance", null);
		double d = (double) param.getOrDefault("d", null);
		double c = (double) param.getOrDefault("c", null);
		if (matrix == null || importance == null) {
			return null;
		}

		HashMap<String, List<?>> result = new HashMap<>();
		List<Double> normalized = normalizer.normalize((List<Double>) importance);
		result.put("Relation", normalized);
		return result;
	}

}
