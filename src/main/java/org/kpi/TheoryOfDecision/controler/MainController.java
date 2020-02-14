/*
 * Copyright (c)  2.2020
 * This file (MainController) is part of BinaryRelationPropertyAnalyser.
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by Screamer  <999screamer999@gmail.com>
 */

package org.kpi.TheoryOfDecision.controler;

import org.kpi.TheoryOfDecision.entity.binaryClassResult.RelationRelationClassPropertiesResult;
import org.kpi.TheoryOfDecision.entity.propertiesResult.BinaryRelationClass;
import org.kpi.TheoryOfDecision.entity.propertiesResult.PropertiesResult;
import org.kpi.TheoryOfDecision.service.BinaryRelationPropertyAnalyser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/")
public class MainController {
	private static final Logger logger = LoggerFactory.getLogger(MainController.class);

	@Autowired
	private BinaryRelationPropertyAnalyser propertyAnalyser;


	@GetMapping("/AnalyseRelation")
	@ResponseStatus(value = HttpStatus.ACCEPTED)
	public List<?> analyse() {
		int[][] ints = {
				{1, 0, 1, 1, 1, 0, 1},
				{0, 1, 1, 1, 1, 0, 1},
				{0, 0, 1, 1, 0, 0, 0},
				{0, 0, 0, 1, 0, 0, 0},
				{0, 0, 1, 1, 1, 0, 0},
				{1, 1, 1, 1, 1, 1, 1},
				{0, 0, 1, 1, 1, 0, 1}
		};
		return Arrays.stream(ints).map((s1) -> Arrays.stream(s1).boxed().collect(Collectors.toList())).collect(Collectors.toList());
	}


	@GetMapping("/test")
	@ResponseStatus(value = HttpStatus.ACCEPTED)
	public Set<Map.Entry<String, BinaryRelationClass>> test() {
		HashMap<String, BinaryRelationClass> test=new HashMap<>();
		test.put("Equivalence",new BinaryRelationClass(true,false,true,false,false,true,false,false,false,false)); //Equivalence
		test.put("strict order",new BinaryRelationClass(false,true,false,true,false,true,false,false,false,true));//strict order
		test.put("unbending order",new BinaryRelationClass(true,false,false,false,true,true,false,false,false,false));//unbending order
		test.put("quasi order",new BinaryRelationClass(true,false,false,false,false,true,false,false,false,false));//quasi order
		test.put("poor order",new BinaryRelationClass(false,false,false,true,false,true,true,false,false,true));//poor order
		test.put("tolerance order",new BinaryRelationClass(true,false,true,false,false,false,false,false,false,false));//tolerance order

		return test.entrySet();
	}

	@PostMapping("/AnalyseRelation")
	@ResponseStatus(value = HttpStatus.ACCEPTED)
	public PropertiesResult analyse(@RequestBody HashMap<String, Object> param) {
		Object matrix = param.getOrDefault("Relation", null);
		if (matrix == null) {
			return null;
		}
		PropertiesResult result=new RelationRelationClassPropertiesResult((ArrayList<ArrayList<Integer>>) matrix);
		result = propertyAnalyser.getBasicProperties(result);
		logger.info("Analyse matrix ");
		return result;
	}


}
