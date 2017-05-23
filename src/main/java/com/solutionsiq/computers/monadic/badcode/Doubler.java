package com.solutionsiq.computers.monadic.badcode;

public class Doubler {
    private long monadicArgument;
    private long monadicResults;

    public void computeDouble() {
        monadicResults = monadicArgument + monadicArgument;
    }

    public void setDoublerArgument(long monadicArgument) {
        this.monadicArgument = monadicArgument;
    }

    public long getDoubledResult() {
        return monadicResults;
    }
}
