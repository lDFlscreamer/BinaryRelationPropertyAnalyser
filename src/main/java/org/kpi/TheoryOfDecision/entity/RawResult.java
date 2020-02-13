/*
 * Copyright (c)  2.2020
 * This file (RawResult) is part of BinaryRelationPropertyAnalyser.
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by Screamer  <999screamer999@gmail.com>
 */

package org.kpi.TheoryOfDecision.entity;

public class RawResult {
	private String transitivityExclusion;
	private String negativeTransitivityExclusion;
	private String Pr;
	private String Ir;
	private String Nr;
	private String negativeSlice;
	private String Slice;
	private boolean reflectivity;
	private boolean antireflectivity;
	private boolean simmetry;
	private boolean asimmetry;
	private boolean antisimmetry;
	private boolean transitivity;
	private boolean negativeTransitivity;

	public RawResult() {
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

	public boolean isReflectivity() {
		return reflectivity;
	}

	public void setReflectivity(boolean reflectivity) {
		this.reflectivity = reflectivity;
	}

	public boolean isAntireflectivity() {
		return antireflectivity;
	}

	public void setAntireflectivity(boolean antireflectivity) {
		this.antireflectivity = antireflectivity;
	}

	public boolean isSimmetry() {
		return simmetry;
	}

	public void setSimmetry(boolean simmetry) {
		this.simmetry = simmetry;
	}

	public boolean isAsimmetry() {
		return asimmetry;
	}

	public void setAsimmetry(boolean asimmetry) {
		this.asimmetry = asimmetry;
	}

	public boolean isAntisimmetry() {
		return antisimmetry;
	}

	public void setAntisimmetry(boolean antisimmetry) {
		this.antisimmetry = antisimmetry;
	}

	public boolean isTransitivity() {
		return transitivity;
	}

	public void setTransitivity(boolean transitivity) {
		this.transitivity = transitivity;
	}

	public boolean isNegativeTransitivity() {
		return negativeTransitivity;
	}

	public void setNegativeTransitivity(boolean negativeTransitivity) {
		this.negativeTransitivity = negativeTransitivity;
	}
}
