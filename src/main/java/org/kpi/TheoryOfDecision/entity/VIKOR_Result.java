/*
 * Copyright (c)  4.2020
 * This file (VIKOR_Result) is part of BinaryRelationPropertyAnalyser.
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by Screamer  <999screamer999@gmail.com>
 */

package org.kpi.TheoryOfDecision.entity;

import java.util.List;

public class VIKOR_Result {
	private List<List<Double>> resultQ,
			resultR,
			resultS;

	public VIKOR_Result(List<List<Double>> resultQ, List<List<Double>> resultR, List<List<Double>> resultS) {
		this.resultQ = resultQ;
		this.resultR = resultR;
		this.resultS = resultS;
	}

	public List<List<Double>> getResultQ() {
		return resultQ;
	}

	public void setResultQ(List<List<Double>> resultQ) {
		this.resultQ = resultQ;
	}

	public List<List<Double>> getResultR() {
		return resultR;
	}

	public void setResultR(List<List<Double>> resultR) {
		this.resultR = resultR;
	}

	public List<List<Double>> getResultS() {
		return resultS;
	}

	public void setResultS(List<List<Double>> resultS) {
		this.resultS = resultS;
	}
}
