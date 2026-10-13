package com.hambaa;

import com.hambaa.util.SceneManager;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 * Main Entry Point for HAMBAA — AI-Based Cow Shed Management System.
 */
public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        SceneManager.switchToLogin(primaryStage);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
