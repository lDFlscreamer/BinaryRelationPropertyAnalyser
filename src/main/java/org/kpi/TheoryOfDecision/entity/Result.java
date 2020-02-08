package org.kpi.TheoryOfDecision.entity;

import java.util.ArrayList;
import java.util.HashMap;

public class Result {
    private ArrayList<RelationObj> Pr;
    private ArrayList<RelationObj> Ir;
    private HashMap<Integer, ArrayList<Integer>> slice;
    private HashMap<Integer, HashMap<Integer, ArrayList<Integer>>> transitivityExclusion;
    private HashMap<Integer, HashMap<Integer, ArrayList<Integer>>> negativeTransitivityExclusion;
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
        transitivityExclusion= new HashMap<>();
        negativeTransitivityExclusion= new HashMap<>();
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
        antisimetri = (Ir.isEmpty()||reflection) && (!Pr.isEmpty());
        asimetri = antisimetri && (antireflection);
        transitivity = checktransitivity(slice,transitivityExclusion);
        negativeTransitivity = checktransitivity(negativeSlice,negativeTransitivityExclusion);
    }


    public boolean checktransitivity(HashMap<Integer, ArrayList<Integer>> slice,HashMap<Integer,HashMap<Integer, ArrayList<Integer>>> exclusion) {
        for (Integer i :
                slice.keySet()) {
            ArrayList<Integer> current = slice.get(i);
            for (Integer j :
                    current) {
                if (i.compareTo(j) == 0) {
                    continue;
                }
                ArrayList<Integer> inner = slice.getOrDefault(j, new ArrayList<>());
                if (!current.containsAll(inner)) {
                    HashMap<Integer, ArrayList<Integer>> exclusionCurrent = exclusion.getOrDefault(i, new HashMap<>());
                    ArrayList<Integer> innerCheck = new ArrayList<>(inner);
                    innerCheck.removeAll(current);
                    exclusionCurrent.put(j,innerCheck);
                    exclusion.put(i,exclusionCurrent);
                    return false;
                }
            }
        }
        return true;
    }

    public String sliceToString(HashMap<Integer,ArrayList<Integer>> slice){
        return slice.entrySet().stream().
                map(hm1 -> "\t\t".concat(hm1.getKey().toString()).concat("->").concat(
                        hm1.getValue().stream().map(Object::toString).reduce((n1, n2) -> n1.concat(",").concat(n2)).orElse("")
                ))
                .reduce((line1, line2) -> line1.concat("\n").concat(line2)).orElse("");

    }

    public String exclusionToString(HashMap<Integer,HashMap<Integer, ArrayList<Integer>>> slice){
        return slice.entrySet().stream().
                map(hm1 -> "\t\t".concat(hm1.getKey().toString()).concat("->").concat(
                                        hm1.getValue().entrySet().stream().map(
                                                hm2->"\n\t\t\t\t".concat(
                                                        hm2.getKey().toString()).concat("->")
                                                        .concat(
                                                        hm2.getValue().stream().map(Object::toString)
                                                                .reduce((n1,n2)->n1.concat(",").concat(n2)).orElse(" ")
                                                )).reduce((line1,line2)->line1.concat("").concat(line2)).orElse(" ")
                                //.stream().map(Object::toString).reduce((n1, n2) -> n1.concat(",").concat(n2)).orElse("")
                ))
                .reduce((line1, line2) -> line1.concat("\n").concat(line2)).orElse("");

    }

    public void printResult() {
        String PrLine = Pr.stream().map(RelationObj::toString).reduce((s1, s2) -> s1.concat(",").concat(s2)).orElse("empty");
        String IrLine = Ir.stream().map(RelationObj::toString).reduce((s1, s2) -> s1.concat(",").concat(s2)).orElse("empty");
        System.out.println("Pr=".concat(PrLine));
        System.out.println("Ir=".concat(IrLine));
        System.out.println("slice =\n".concat(this.sliceToString(slice)));
        System.out.println("negative slice =\n".concat(this.sliceToString(negativeSlice)));

        System.out.println("\n");
        System.out.println("Reflexivity:".concat(reflection ? "+" : "-"));
        System.out.println("Antireflexivity:".concat(antireflection ? "+" : "-"));
        System.out.println("Simetry:".concat(simetry ? "+" : "-"));
        System.out.println("aSimetry:".concat(asimetri ? "+" : "-"));
        System.out.println("antiSimetry:".concat(antisimetri ? "+" : "-"));
        System.out.println("Transitivity:".concat(transitivity ? "+" : "-"));
        System.out.println(transitivity?"":this.exclusionToString(transitivityExclusion));
        System.out.println("negative Transitivity:".concat(negativeTransitivity ? "+" : "-"));
        System.out.println(negativeTransitivity?"":this.exclusionToString(negativeTransitivityExclusion));
    }
}
