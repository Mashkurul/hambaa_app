package com.hambaa.controller;

import com.hambaa.mock.MockData;
import com.hambaa.util.SceneManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.SVGPath;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Controller for the HAMBAA Login Page.
 */
public class LoginController implements Initializable {

    @FXML private TextField emailField;
    @FXML private PasswordField passwordField;
    @FXML private TextField passwordVisibleField;
    @FXML private StackPane passwordStackPane;
    @FXML private Button togglePasswordButton;
    @FXML private SVGPath eyeIconPath;
    @FXML private CheckBox rememberMeCheckBox;
    @FXML private Hyperlink forgotPasswordLink;
    @FXML private Button loginButton;
    @FXML private Label errorLabel;
    @FXML private Button quickLogBackButton;
    @FXML private Button userProfileButton;

    private boolean isPasswordVisible = false;

    // SVG Eye Icons for show / hide password
    private static final String EYE_OPEN_PATH = "M12 4.5C7 4.5 2.73 7.61 1 12c1.73 4.39 6 7.5 11 7.5s9.27-3.11 11-7.5c-1.73-4.39-6-7.5-11-7.5zM12 17c-2.76 0-5-2.24-5-5s2.24-5 5-5 5 2.24 5 5-2.24 5-5 5zm0-8c-1.66 0-3 1.34-3 3s1.34 3 3 3 3-1.34 3-3-1.34-3-3-3z";
    private static final String EYE_CLOSED_PATH = "M12 7c2.76 0 5 2.24 5 5 0 .65-.13 1.26-.36 1.83l2.92 2.92c1.51-1.26 2.7-2.89 3.44-4.75-1.73-4.39-6-7.5-11-7.5-1.4 0-2.74.25-3.98.7l2.16 2.16C10.74 7.13 11.35 7 12 7zM2 4.27l2.28 2.28.46.46C3.08 8.3 1.78 10.02 1 12c1.73 4.39 6 7.5 11 7.5 1.55 0 3.03-.3 4.38-.84l.42.42L19.73 22 21 20.73 3.27 3 2 4.27zM7.53 9.8l1.55 1.55c-.05.21-.08.43-.08.65 0 1.66 1.34 3 3 3 .22 0 .44-.03.65-.08l1.55 1.55c-.67.33-1.41.53-2.2.53-2.76 0-5-2.24-5-5 0-.79.2-1.53.53-2.2zm4.31-.78l3.15 3.15.02-.17c0-1.66-1.34-3-3-3l-.17.02z";

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Prefill mock data as requested
        emailField.setText(MockData.MOCK_EMAIL);
        passwordField.setText(MockData.MOCK_PASSWORD);
        passwordVisibleField.setText(MockData.MOCK_PASSWORD);

        // Bind text properties bidirectionally for password toggle
        passwordField.textProperty().bindBidirectional(passwordVisibleField.textProperty());

        // Configure initial password field visibility
        passwordVisibleField.setVisible(false);
        passwordVisibleField.setManaged(false);

        if (eyeIconPath != null) {
            eyeIconPath.setContent(EYE_OPEN_PATH);
        }

        // Hide error label initially
        if (errorLabel != null) {
            errorLabel.setVisible(false);
            errorLabel.setManaged(false);
        }

        // Remember me session check
        if (rememberMeCheckBox != null) {
            rememberMeCheckBox.setSelected(true);
        }
    }

    /**
     * Handles Log In button click.
     */
    @FXML
    private void handleLogin(ActionEvent event) {
        String email = emailField.getText();
        String password = passwordField.getText();

        if (MockData.validateLogin(email, password)) {
            if (errorLabel != null) {
                errorLabel.setVisible(false);
                errorLabel.setManaged(false);
            }
            Stage stage = (Stage) loginButton.getScene().getWindow();
            SceneManager.switchToDashboard(stage);
        } else {
            if (errorLabel != null) {
                errorLabel.setText("Invalid email or password.");
                errorLabel.setVisible(true);
                errorLabel.setManaged(true);
            }
        }
    }

    /**
     * Toggles visibility of the password field between masked and clear text.
     */
    @FXML
    private void togglePasswordVisibility(ActionEvent event) {
        isPasswordVisible = !isPasswordVisible;

        if (isPasswordVisible) {
            passwordField.setVisible(false);
            passwordField.setManaged(false);

            passwordVisibleField.setVisible(true);
            passwordVisibleField.setManaged(true);
            passwordVisibleField.requestFocus();
            passwordVisibleField.selectEnd();

            if (eyeIconPath != null) {
                eyeIconPath.setContent(EYE_CLOSED_PATH);
            }
        } else {
            passwordVisibleField.setVisible(false);
            passwordVisibleField.setManaged(false);

            passwordField.setVisible(true);
            passwordField.setManaged(true);
            passwordField.requestFocus();
            passwordField.selectEnd();

            if (eyeIconPath != null) {
                eyeIconPath.setContent(EYE_OPEN_PATH);
            }
        }
    }

    /**
     * Handles Forgot Password click.
     */
    @FXML
    private void handleForgotPassword(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Password Recovery");
        alert.setHeaderText("HAMBAA Herd OS");
        alert.setContentText("Password recovery will be available in a future version.");
        alert.showAndWait();
    }

    /**
     * Handles Quick Log Back button click.
     */
    @FXML
    private void handleQuickLogBack(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Quick Log");
        alert.setHeaderText("Navigation");
        alert.setContentText("Quick Log feature is already at the current main entry.");
        alert.showAndWait();
    }

    /**
     * Handles User Profile icon click in top bar.
     */
    @FXML
    private void handleUserProfileClick(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("User Profile");
        alert.setHeaderText("Herd Manager Profile");
        alert.setContentText("Logged in as: " + MockData.MOCK_EMAIL + "\nFarm: " + MockData.FARM_NAME);
        alert.showAndWait();
    }
}
