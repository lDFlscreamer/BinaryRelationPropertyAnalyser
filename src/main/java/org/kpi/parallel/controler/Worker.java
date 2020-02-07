package org.kpi.parallel.controler;


import org.kpi.parallel.entity.RelationObj;
import org.kpi.parallel.entity.Result;

import java.util.ArrayList;
import java.util.HashMap;

public class Worker {
    public Result analyse(int[][] matrix) {
        Result res = new Result();
        ArrayList<RelationObj> Pr = new ArrayList<>();
        ArrayList<RelationObj> Ir = new ArrayList<>();
        HashMap<Integer, ArrayList<Integer>> slice=new HashMap<>();
        HashMap<Integer, ArrayList<Integer>> negativeSlice=new HashMap<>();
        boolean refl = true;
        boolean antirefl = false;

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (matrix[i][j] == 1) {
                    refl &= (i == j);//if stay true is reflexivity
                    if (matrix[j][i] == 1) {
                        Ir.add(new RelationObj(i+1, j+1));
                        Ir.add(new RelationObj(j+1, i+1));
                    } else {
                        Pr.add(new RelationObj(i+1, j+1));
                    }
                    ArrayList<Integer> currentSlice;
                    currentSlice = slice.getOrDefault(i+1,new ArrayList<Integer>());
                    currentSlice.add(j+1);
                    slice.put(i+1,currentSlice);
                } else {
                    antirefl |= (i == j);//if anti stay true is antireflixivity
                    ArrayList<Integer> currentSlice;
                    currentSlice = negativeSlice.getOrDefault(i+1, new ArrayList<>());
                    currentSlice.add(j+1);
                    negativeSlice.put(i+1,currentSlice);
                }
            }
        }
        res.setSlice(slice);
        res.setPr(Pr);
        res.setIr(Ir);
        res.setReflection(refl);
        res.setAntiReflection(antirefl);
        res.setNegativeSlice(negativeSlice);
        res.analyse();
        return res;
    }


}
