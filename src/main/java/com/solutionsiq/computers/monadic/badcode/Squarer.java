package com.solutionsiq.computers.monadic.badcode;

public class Squarer {
    private long monadicArgument;
    private long monadicResults;

    public void computeSquare() {
        monadicResults = monadicArgument * monadicArgument;
    }

    public void setSquarerArgument(long monadicArgument) {
        this.monadicArgument = monadicArgument;
    }

    public long getSquaredResult() {
        return monadicResults;
    }

}