/*
 * Copyright (c)  2.2020
 * This file (PropertyAnalyser) is part of BinaryRelationPropertyAnalyser.
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by Screamer  <999screamer999@gmail.com>
 */

package org.kpi.TheoryOfDecision.service;


import org.kpi.TheoryOfDecision.entity.RelationObj;
import org.kpi.TheoryOfDecision.entity.Result;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


@Service
public class PropertyAnalyser {

    public  Result analyse(ArrayList<ArrayList<Integer>> matrix) {
        Result res = new Result(matrix);
        ArrayList<RelationObj> Pr = new ArrayList<>();
        ArrayList<RelationObj> Ir = new ArrayList<>();
        ArrayList<RelationObj> Nr = new ArrayList<>();
        HashMap<Integer, List<Integer>> slice = new HashMap<>();
        HashMap<Integer, List<Integer>> negativeSlice = new HashMap<>();
        boolean refl = true;
        boolean antirefl = true;

        for (int i = 0; i < matrix.size(); i++) {
            for (int j = 0; j < matrix.get(i).size(); j++) {
                Integer element = matrix.get(i).get(j);
                if (i == j) {
                    antirefl &= (element.equals(0));
                    refl &= (element.equals(1));
                }
                Integer symetricalElement = matrix.get(j).get(i);
                if (element == 1) {
                    if (symetricalElement.equals(element)) {
                        Ir.add(new RelationObj(i + 1, j + 1));
                    } else {
                        Pr.add(new RelationObj(i + 1, j + 1));
                    }
                    List<Integer> currentSlice;
                    currentSlice = slice.getOrDefault(i + 1, new ArrayList<>());
                    currentSlice.add(j + 1);
                    slice.put(i + 1, currentSlice);
                } else {
                    if (symetricalElement.equals(element)) {
                        Nr.add(new RelationObj(i + 1, j + 1));
                    }
                    List<Integer> currentSlice;
                    currentSlice = negativeSlice.getOrDefault(i + 1, new ArrayList<>());
                    currentSlice.add(j + 1);
                    negativeSlice.put(i + 1, currentSlice);
                }
            }
        }
        res.setSlice(slice);
        res.setPr(Pr);
        res.setIr(Ir);
        res.setNr(Nr);
        res.setReflectivity(refl);
        res.setAntireflectivity(antirefl);
        res.setNegativeSlice(negativeSlice);
        res.analyse();
        return res;
    }


}
