package com.solutionsiq.computers.monadic.goodcode;

public class Squarer implements MonadicFunction{
    public long compute(long argument) {
        return argument * argument;
    }
}