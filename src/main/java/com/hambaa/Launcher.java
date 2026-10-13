package com.hambaa;

/**
 * Entry point wrapper for launching the JavaFX application.
 * Avoids "JavaFX runtime components are missing" error when launched directly.
 */
public class Launcher {
    public static void main(String[] args) {
        Main.main(args);
    }
}
