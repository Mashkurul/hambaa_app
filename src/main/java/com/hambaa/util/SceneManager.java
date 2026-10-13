package com.hambaa.util;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

/**
 * Scene navigation utility for HAMBAA application.
 */
public class SceneManager {

    public static final String APP_TITLE = "HAMBAA — AI-Based Cow Shed Management System";
    public static final double DEFAULT_WIDTH = 950.0;
    public static final double DEFAULT_HEIGHT = 720.0;
    public static final double MIN_WIDTH = 880.0;
    public static final double MIN_HEIGHT = 650.0;

    /**
     * Switches stage to the Login scene.
     */
    public static void switchToLogin(Stage stage) {
        loadScene(stage, "/fxml/login.fxml", "Login — " + APP_TITLE);
    }

    /**
     * Switches stage to the Dashboard scene.
     */
    public static void switchToDashboard(Stage stage) {
        loadScene(stage, "/fxml/dashboard.fxml", "Dashboard — " + APP_TITLE);
    }

    private static void loadScene(Stage stage, String fxmlPath, String title) {
        try {
            URL resourceUrl = SceneManager.class.getResource(fxmlPath);
            if (resourceUrl == null) {
                throw new IOException("FXML resource not found: " + fxmlPath);
            }

            FXMLLoader loader = new FXMLLoader(resourceUrl);
            Parent root = loader.load();

            double currentWidth = stage.getScene() != null ? stage.getScene().getWidth() : DEFAULT_WIDTH;
            double currentHeight = stage.getScene() != null ? stage.getScene().getHeight() : DEFAULT_HEIGHT;

            if (currentWidth < MIN_WIDTH) currentWidth = DEFAULT_WIDTH;
            if (currentHeight < MIN_HEIGHT) currentHeight = DEFAULT_HEIGHT;

            Scene scene = new Scene(root, currentWidth, currentHeight);
            stage.setTitle(title);
            stage.setMinWidth(MIN_WIDTH);
            stage.setMinHeight(MIN_HEIGHT);
            stage.setScene(scene);
            stage.centerOnScreen();
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Failed to load scene: " + fxmlPath + " - " + e.getMessage());
        }
    }
}
