/*
 * Copyright (c)  2.2020
 * This file (RawResult) is part of BinaryRelationPropertyAnalyser.
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by Screamer  <999screamer999@gmail.com>
 */

package org.kpi.TheoryOfDecision.entity;

import java.util.List;

public class RawResult extends BasicRelationProperties {
	private String transitivityExclusion;
	private String negativeTransitivityExclusion;
	private String Pr;
	private String Ir;
	private String Nr;
	private String negativeSlice;
	private String Slice;

	public RawResult(List<?> inputedMatrix) {
		super(inputedMatrix);
	}

	public String getTransitivityExclusion() {
		return transitivityExclusion;
	}

	public void setTransitivityExclusion(String transitivityExclusion) {
		this.transitivityExclusion = transitivityExclusion;
	}

	public String getNegativeTransitivityExclusion() {
		return negativeTransitivityExclusion;
	}

	public void setNegativeTransitivityExclusion(String negativeTransitivityExclusion) {
		this.negativeTransitivityExclusion = negativeTransitivityExclusion;
	}

	public String getPr() {
		return Pr;
	}

	public void setPr(String pr) {
		Pr = pr;
	}

	public String getIr() {
		return Ir;
	}

	public void setIr(String ir) {
		Ir = ir;
	}

	public String getNr() {
		return Nr;
	}

	public void setNr(String nr) {
		Nr = nr;
	}

	public String getNegativeSlice() {
		return negativeSlice;
	}

	public void setNegativeSlice(String negativeSlice) {
		this.negativeSlice = negativeSlice;
	}

	public String getSlice() {
		return Slice;
	}

	public void setSlice(String slice) {
		Slice = slice;
	}
}
