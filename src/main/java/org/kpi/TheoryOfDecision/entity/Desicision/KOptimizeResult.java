/*
 * Copyright (c)  2.2020
 * This file (KOptimizeResult) is part of BinaryRelationPropertyAnalyser.
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
public class KOptimizeResult {

	private List<Integer> k1;
	private List<Integer> optk1;
	private List<Integer> k2;
	private List<Integer> optk2;
	private List<Integer> k3;
	private List<Integer> optk3;
	private List<Integer> k4;
	private List<Integer> optk4;

	public List<Integer> getK1() {
		return k1;
	}

	public void setK1(List<Integer> k1) {
		this.k1 = k1;
	}

	public List<Integer> getOptk1() {
		return optk1;
	}

	public void setOptk1(List<Integer> optk1) {
		this.optk1 = optk1;
	}

	public List<Integer> getK2() {
		return k2;
	}

	public void setK2(List<Integer> k2) {
		this.k2 = k2;
	}

	public List<Integer> getOptk2() {
		return optk2;
	}

	public void setOptk2(List<Integer> optk2) {
		this.optk2 = optk2;
	}

	public List<Integer> getK3() {
		return k3;
	}

	public void setK3(List<Integer> k3) {
		this.k3 = k3;
	}

	public List<Integer> getOptk3() {
		return optk3;
	}

	public void setOptk3(List<Integer> optk3) {
		this.optk3 = optk3;
	}

	public List<Integer> getK4() {
		return k4;
	}

	public void setK4(List<Integer> k4) {
		this.k4 = k4;
	}

	public List<Integer> getOptk4() {
		return optk4;
	}

	public void setOptk4(List<Integer> optk4) {
		this.optk4 = optk4;
	}
}
