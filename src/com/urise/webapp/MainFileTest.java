package com.urise.webapp;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class MainFileTest {
    public static void main(String[] args) {
        Consumer<String> print = System.out::println;
        print.accept("qwe");
        Consumer<List<String>> listConsumer = System.out::println;
        List<String> stringList = new ArrayList<>();
        stringList.add("s");
        listConsumer.accept(stringList);
    }
}
