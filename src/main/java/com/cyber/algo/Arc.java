package com.cyber.algo;

public record Arc(String target, Integer weight) implements Comparable<Arc> {
    @Override
    public int compareTo(Arc o2) {
        return this.weight().compareTo(o2.weight());
    }
}
