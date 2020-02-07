package org.kpi.parallel.entity;

import java.util.ArrayList;
import java.util.HashMap;

public class Result {
    private ArrayList<RelationObj> Pr;
    private ArrayList<RelationObj> Ir;
    private HashMap<Integer, ArrayList<Integer>> slice;
    private HashMap<Integer, ArrayList<Integer>> negativeSlice;
    private boolean reflection;
    private boolean antireflection;
    private boolean simetry;
    private boolean asimetri;
    private boolean antisimetri;
    private boolean transitivity;
    private boolean negativeTransitivity;

    public Result(ArrayList<RelationObj> pr, ArrayList<RelationObj> ir, HashMap<Integer, ArrayList<Integer>> slice) {
        Pr = pr;
        Ir = ir;
        this.slice = slice;
    }

    public Result() {
        Pr = new ArrayList<>();
        Ir = new ArrayList<>();
    }

    public HashMap<Integer, ArrayList<Integer>> getNegativeSlice() {
        return negativeSlice;
    }

    public void setNegativeSlice(HashMap<Integer, ArrayList<Integer>> negativeSlice) {
        this.negativeSlice = negativeSlice;
    }

    public boolean isNegativeTransitivity() {
        return negativeTransitivity;
    }

    public void setNegativeTransitivity(boolean negativeTransitivity) {
        this.negativeTransitivity = negativeTransitivity;
    }

    public boolean isReflection() {
        return reflection;
    }

    public void setReflection(boolean reflection) {
        this.reflection = reflection;
    }

    public boolean isAntireflection() {
        return antireflection;
    }

    public void setAntiReflection(boolean antireflection) {
        this.antireflection = antireflection;
    }

    public boolean isSimetry() {
        return simetry;
    }

    public void setSimetry(boolean simetry) {
        this.simetry = simetry;
    }

    public boolean isAsimetri() {
        return asimetri;
    }

    public void setAsimetri(boolean asimetri) {
        this.asimetri = asimetri;
    }

    public boolean isAntisimetri() {
        return antisimetri;
    }

    public void setAntisimetri(boolean antisimetri) {
        this.antisimetri = antisimetri;
    }

    public boolean isTransitivity() {
        return transitivity;
    }

    public void setTransitivity(boolean transitivity) {
        this.transitivity = transitivity;
    }

    public boolean isNegative_transitivity() {
        return negativeTransitivity;
    }

    public void setNegative_transitivity(boolean negative_transitivity) {
        this.negativeTransitivity = negative_transitivity;
    }

    public ArrayList<RelationObj> getPr() {
        return Pr;
    }

    public void setPr(ArrayList<RelationObj> pr) {
        Pr = pr;
    }

    public ArrayList<RelationObj> getIr() {
        return Ir;
    }

    public void setIr(ArrayList<RelationObj> ir) {
        Ir = ir;
    }

    public HashMap<Integer, ArrayList<Integer>> getSlice() {
        return slice;
    }

    public void setSlice(HashMap<Integer, ArrayList<Integer>> slice) {
        this.slice = slice;
    }

    public void analyse() {
        simetry = Pr.isEmpty() && (!Ir.isEmpty());
        antisimetri = Ir.isEmpty() && (!Pr.isEmpty()) ;
        asimetri = antisimetri && (antireflection);
        transitivity = checktransitivity(slice);
        negativeTransitivity = checktransitivity(negativeSlice);
    }


    public boolean checktransitivity(HashMap<Integer, ArrayList<Integer>> slice) {
        for (Integer i :
                slice.keySet()) {
            ArrayList<Integer> current = slice.get(i);
            for (Integer j :
                    current) {
                if (i.compareTo(j) == 0) {
                    continue;
                }
                ArrayList<Integer> inner = slice.getOrDefault(j, new ArrayList<Integer>());
                if (!current.containsAll(inner)) {
                    return false;
                }
            }
        }
        return true;
    }

    public void printResult() {
        String PrLine = Pr.stream().map(RelationObj::toString).reduce((s1, s2) -> s1.concat(",").concat(s2)).orElse("empty");
        String IrLine = Ir.stream().map(RelationObj::toString).reduce((s1, s2) -> s1.concat(",").concat(s2)).orElse("empty");
        System.out.println("Pr=".concat(PrLine));
        System.out.println("Ir=".concat(IrLine));
        String s = slice.entrySet().stream().
                map(hm1 -> "\t\t".concat(hm1.getKey().toString()).concat("->").concat(
                        hm1.getValue().stream().map(Object::toString).reduce((n1, n2) -> n1.concat(",").concat(n2)).orElse("")
                ))
                .reduce((line1, line2) -> line1.concat("\n").concat(line2)).orElse("");
        String ns = negativeSlice.entrySet().stream().
                map(hm1 -> "\t\t".concat(hm1.getKey().toString()).concat("->").concat(
                        hm1.getValue().stream().map(Object::toString).reduce((n1, n2) -> n1.concat(",").concat(n2)).orElse("")
                ))
                .reduce((line1, line2) -> line1.concat("\n").concat(line2)).orElse("");


        System.out.println("slice =\n".concat(s));
        System.out.println("negative slice =\n".concat(ns));


        System.out.println("\n\n");
        System.out.println("Reflexivity:".concat(reflection ? "+" : "-"));
        System.out.println("Antireflexivity:".concat(antireflection ? "+" : "-"));
        System.out.println("Simetry:".concat(simetry ? "+" : "-"));
        System.out.println("aSimetry:".concat(asimetri ? "+" : "-"));
        System.out.println("antiSimetry:".concat(antisimetri ? "+" : "-"));
        System.out.println("Transitivity:".concat(transitivity ? "+" : "-"));
        System.out.println("negative Transitivity:".concat(negativeTransitivity ? "+" : "-"));
    }
}