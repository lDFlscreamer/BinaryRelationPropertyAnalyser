package org.kpi.parallel.entity;

public class RelationObj {
    private int first;
    private int second;

    public RelationObj(int first, int second) {
        this.first = first;
        this.second = second;
    }

    @Override
    public String toString() {
        return "(".concat(first+":"+second).concat(")");
    }

    public int getFirst() {
        return first;
    }

    public void setFirst(int first) {
        this.first = first;
    }

    public int getSecond() {
        return second;
    }

    public void setSecond(int second) {
        this.second = second;
    }
}
