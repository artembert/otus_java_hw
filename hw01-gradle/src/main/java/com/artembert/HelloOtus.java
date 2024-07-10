package com.artembert;

import com.google.common.base.Joiner;

@SuppressWarnings("java:S106")
public class HelloOtus {
    public static void main(String... args) {
        Joiner joiner = Joiner.on("; ").skipNulls();
        String characters = joiner.join("Harry", null, "Ron", "Hermione");
        System.out.println(characters);
    }
}