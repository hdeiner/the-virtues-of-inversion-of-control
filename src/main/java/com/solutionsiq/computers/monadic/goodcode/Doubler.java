package com.solutionsiq.computers.monadic.goodcode;

public class Doubler implements MonadicFunction{
    public long compute(long argument) {
        return argument + argument;
    }
}
