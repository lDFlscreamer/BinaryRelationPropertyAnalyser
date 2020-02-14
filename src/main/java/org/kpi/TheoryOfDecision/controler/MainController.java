/*
 * Copyright (c)  2.2020
 * This file (MainController) is part of BinaryRelationPropertyAnalyser.
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by Screamer  <999screamer999@gmail.com>
 */

package org.kpi.TheoryOfDecision.controler;

import org.kpi.TheoryOfDecision.entity.propertiesResult.PropertiesResult;
import org.kpi.TheoryOfDecision.service.BinaryRelationPropertyAnalyser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;

@RestController
@RequestMapping("/")
public class MainController {
	private static final Logger logger = LoggerFactory.getLogger(MainController.class);

	@Autowired
	private BinaryRelationPropertyAnalyser propertyAnalyser;


	@GetMapping("/AnalyseRelation")
	@ResponseStatus(value = HttpStatus.ACCEPTED)
	public int[][] analyse() {
		return new int[][]{
				{1, 0, 1, 1, 1, 0, 1},
				{0, 1, 1, 1, 1, 0, 1},
				{0, 0, 1, 1, 0, 0, 0},
				{0, 0, 0, 1, 0, 0, 0},
				{0, 0, 1, 1, 1, 0, 0},
				{1, 1, 1, 1, 1, 1, 1},
				{0, 0, 1, 1, 1, 0, 1}
		};
	}


	@PostMapping("/AnalyseRelation")
	@ResponseStatus(value = HttpStatus.ACCEPTED)
	public PropertiesResult analyse(@RequestBody HashMap<String, Object> param) {
		Object matrix = param.getOrDefault("Relation", null);
		if (matrix == null) {
			return null;
		}
		PropertiesResult result;
		result = propertyAnalyser.getBasicProperties((ArrayList<ArrayList<Integer>>) matrix);
		logger.info("Analyse matrix ");
		return result;
	}


}
