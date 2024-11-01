package com.example;

import com.github.kwhat.jnativehook.GlobalScreen;

/**
 * Hello world!
 *
 */
public class App {
    public static void main(String[] args) throws Exception {

        GlobalScreen.registerNativeHook();

        // lot more

        System.out.println("Hello World!");

    }
}
