/*
 * Copyright (c)  2.2020
 * This file (RelationClassAnalyser) is part of BinaryRelationPropertyAnalyser.
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by Screamer  <999screamer999@gmail.com>
 */

package org.kpi.TheoryOfDecision.service;

import org.kpi.TheoryOfDecision.constant.Constant;
import org.kpi.TheoryOfDecision.entity.binaryClassResult.RelationRelationClassPropertiesResult;
import org.kpi.TheoryOfDecision.entity.propertiesResult.BinaryRelationClass;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class RelationClassAnalyser {

	public Predicate<BinaryRelationClass> getPredicate(Method d, BinaryRelationClass inputed) {
		return rel -> {
			try {
				return !(boolean) d.invoke(rel) || (boolean) d.invoke(rel) == (boolean) d.invoke(inputed);
			} catch (IllegalAccessException | InvocationTargetException e) {
				e.printStackTrace();
			}
			return true;
		};
	}

	public Stream<BinaryRelationClass> checkAllParameter(Stream<BinaryRelationClass> stream, BinaryRelationClass inputed) {
		List<Method> methods = Arrays.stream(BinaryRelationClass.class.getDeclaredMethods()).filter(s -> s.getReturnType() == boolean.class).collect(Collectors.toList());
		for (Method m : methods
		) {
			stream = stream.filter(getPredicate(m, inputed));
		}
		return stream;
	}

	public String detectClass(RelationRelationClassPropertiesResult inputed) {
		Collection<BinaryRelationClass> classes = Constant.BINARY_CLASSES.keySet();
		Stream<BinaryRelationClass> classStream = classes.stream();
		List<BinaryRelationClass> detectedClass = checkAllParameter(classStream, inputed).sorted()
				.collect(Collectors.toList());
		if (detectedClass.isEmpty()) {
			return "does not belong to the class";
		}
		 return detectedClass.stream().filter((s) -> s.compareTo(detectedClass.get(detectedClass.size() - 1)) == 0).map((s) -> Constant.BINARY_CLASSES.getOrDefault(s, null)).reduce((s, s1) -> s + " or " + s1).orElse("\"does not belong to the class\"");
	}
}
