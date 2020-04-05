/*
 * Copyright (c)  2.2020
 * This file (DecisionResult) is part of BinaryRelationPropertyAnalyser.
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by Screamer  <999screamer999@gmail.com>
 */

package org.kpi.TheoryOfDecision.entity.Desicision;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class DecisionResult {

	private List<Integer> maxP;
	private List<Integer> largestP;
	private List<Integer> maxR;
	private List<Integer> strictlyMaxR;
	private List<Integer> largestR;
	private List<Integer> strictlyLargestR;

	public List<Integer> getMaxP() {
		return maxP;
	}

	public void setMaxP(List<Integer> maxP) {
		this.maxP = maxP;
	}

	public List<Integer> getLargestP() {
		return largestP;
	}

	public void setLargestP(List<Integer> largestP) {
		this.largestP = largestP;
	}

	public List<Integer> getMaxR() {
		return maxR;
	}

	public void setMaxR(List<Integer> maxR) {
		this.maxR = maxR;
	}

	public List<Integer> getStrictlyMaxR() {
		return strictlyMaxR;
	}

	public void setStrictlyMaxR(List<Integer> strictlyMaxR) {
		this.strictlyMaxR = strictlyMaxR;
	}

	public List<Integer> getLargestR() {
		return largestR;
	}

	public void setLargestR(List<Integer> largestR) {
		this.largestR = largestR;
	}

	public List<Integer> getStrictlyLargestR() {
		return strictlyLargestR;
	}

	public void setStrictlyLargestR(List<Integer> strictlyLargestR) {
		this.strictlyLargestR = strictlyLargestR;
	}
}
