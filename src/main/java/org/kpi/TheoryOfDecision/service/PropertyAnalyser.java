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


@Service
public class PropertyAnalyser {
    public static Result analyse(int[][] matrix) {
        Result res = new Result();
        ArrayList<RelationObj> Pr = new ArrayList<>();
        ArrayList<RelationObj> Ir = new ArrayList<>();
        HashMap<Integer, ArrayList<Integer>> slice = new HashMap<>();
        HashMap<Integer, ArrayList<Integer>> negativeSlice = new HashMap<>();
        boolean refl = true;
        boolean antirefl = true;

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (i == j) {
                    antirefl &= matrix[i][j] == 0;
                    refl &= matrix[i][j] == 1;
                }
                if (matrix[i][j] == 1) {
                    if (matrix[j][i] == 1) {
                        Ir.add(new RelationObj(i + 1, j + 1));
                    } else {
                        Pr.add(new RelationObj(i + 1, j + 1));
                    }
                    ArrayList<Integer> currentSlice;
                    currentSlice = slice.getOrDefault(i + 1, new ArrayList<>());
                    currentSlice.add(j + 1);
                    slice.put(i + 1, currentSlice);
                } else {
                    ArrayList<Integer> currentSlice;
                    currentSlice = negativeSlice.getOrDefault(i + 1, new ArrayList<>());
                    currentSlice.add(j + 1);
                    negativeSlice.put(i + 1, currentSlice);
                }
            }
        }
        res.setSlice(slice);
        res.setPr(Pr);
        res.setIr(Ir);
        res.setReflectivity(refl);
        res.setAntiReflection(antirefl);
        res.setNegativeSlice(negativeSlice);
        res.analyse();
        return res;
    }

    public  Result analyse(ArrayList<ArrayList<Integer>> matrix) {
        Result res = new Result();
        res.setInputedMatrix(matrix);
        ArrayList<RelationObj> Pr = new ArrayList<>();
        ArrayList<RelationObj> Ir = new ArrayList<>();
        ArrayList<RelationObj> Nr = new ArrayList<>();
        HashMap<Integer, ArrayList<Integer>> slice = new HashMap<>();
        HashMap<Integer, ArrayList<Integer>> negativeSlice = new HashMap<>();
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
                    ArrayList<Integer> currentSlice;
                    currentSlice = slice.getOrDefault(i + 1, new ArrayList<>());
                    currentSlice.add(j + 1);
                    slice.put(i + 1, currentSlice);
                } else {
                    if (symetricalElement.equals(element)) {
                        Nr.add(new RelationObj(i + 1, j + 1));
                    }
                    ArrayList<Integer> currentSlice;
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
        res.setAntiReflection(antirefl);
        res.setNegativeSlice(negativeSlice);
        res.analyse();
        return res;
    }


}
