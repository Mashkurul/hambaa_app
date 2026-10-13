package com.hambaa.controller;

import com.hambaa.mock.MockData;
import com.hambaa.util.SceneManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Controller for the HAMBAA Dashboard Screen.
 */
public class DashboardController implements Initializable {

    @FXML private Label totalCowsLabel;
    @FXML private Label todayMilkLabel;
    @FXML private Label monthlyRevenueLabel;
    @FXML private Label todayExpensesLabel;
    @FXML private Label healthyCowsLabel;
    @FXML private Label needsAttentionLabel;

    @FXML private VBox activityListContainer;
    @FXML private VBox mainDashboardContent;
    @FXML private VBox placeholderContent;
    @FXML private Label placeholderTitleLabel;
    @FXML private Label placeholderSubtitleLabel;

    @FXML private Label currentViewTitle;
    @FXML private Button dashboardNavButton;
    @FXML private Button cowsNavButton;
    @FXML private Button milkNavButton;
    @FXML private Button feedNavButton;
    @FXML private Button healthNavButton;
    @FXML private Button employeeNavButton;
    @FXML private Button expensesNavButton;
    @FXML private Button salesNavButton;
    @FXML private Button reportsNavButton;
    @FXML private Button aiNavButton;
    @FXML private Button logoutButton;

    private Button currentActiveNavButton;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Load KPI values from MockData
        totalCowsLabel.setText(String.valueOf(MockData.TOTAL_COWS));
        todayMilkLabel.setText(MockData.TODAY_MILK_LITERS + " L");
        monthlyRevenueLabel.setText("৳" + String.format("%,.0f", MockData.MONTHLY_REVENUE_BDT));
        todayExpensesLabel.setText("৳" + String.format("%,.0f", MockData.TODAY_EXPENSES_BDT));
        healthyCowsLabel.setText(String.valueOf(MockData.HEALTHY_COWS));
        needsAttentionLabel.setText(String.valueOf(MockData.ATTENTION_COWS));

        // Populate recent activity feed
        populateRecentActivities();

        // Set initial view state
        showDashboardView();
        setActiveNavButton(dashboardNavButton);
    }

    private void populateRecentActivities() {
        if (activityListContainer == null) return;
        activityListContainer.getChildren().clear();

        for (MockData.ActivityItem activity : MockData.getRecentActivities()) {
            VBox itemBox = new VBox(3);
            itemBox.getStyleClass().add("activity-item");

            Label titleLabel = new Label(activity.title());
            titleLabel.getStyleClass().add("activity-title");

            Label metaLabel = new Label(activity.subtitle() + " • " + activity.timeAgo());
            metaLabel.getStyleClass().add("activity-meta");

            itemBox.getChildren().addAll(titleLabel, metaLabel);
            activityListContainer.getChildren().add(itemBox);
        }
    }

    /**
     * Handles clicking on the Dashboard menu item.
     */
    @FXML
    private void handleNavDashboard(ActionEvent event) {
        showDashboardView();
        setActiveNavButton(dashboardNavButton);
        if (currentViewTitle != null) {
            currentViewTitle.setText("Dashboard Overview");
        }
    }

    /**
     * Handles clicking on any placeholder menu item (Cow Management, Milk, etc.).
     */
    @FXML
    private void handleNavPlaceholder(ActionEvent event) {
        if (event.getSource() instanceof Button button) {
            String moduleName = button.getText().trim();
            showPlaceholderView(moduleName);
            setActiveNavButton(button);
            if (currentViewTitle != null) {
                currentViewTitle.setText(moduleName);
            }
        }
    }

    private void showDashboardView() {
        if (mainDashboardContent != null) {
            mainDashboardContent.setVisible(true);
            mainDashboardContent.setManaged(true);
        }
        if (placeholderContent != null) {
            placeholderContent.setVisible(false);
            placeholderContent.setManaged(false);
        }
    }

    private void showPlaceholderView(String moduleName) {
        if (mainDashboardContent != null) {
            mainDashboardContent.setVisible(false);
            mainDashboardContent.setManaged(false);
        }
        if (placeholderContent != null) {
            placeholderContent.setVisible(true);
            placeholderContent.setManaged(true);
            if (placeholderTitleLabel != null) {
                placeholderTitleLabel.setText(moduleName + " — Coming Soon");
            }
            if (placeholderSubtitleLabel != null) {
                placeholderSubtitleLabel.setText("The " + moduleName + " module is currently under development for HAMBAA Herd OS.");
            }
        }
    }

    private void setActiveNavButton(Button button) {
        if (currentActiveNavButton != null) {
            currentActiveNavButton.getStyleClass().remove("active-nav");
        }
        if (button != null) {
            if (!button.getStyleClass().contains("active-nav")) {
                button.getStyleClass().add("active-nav");
            }
            currentActiveNavButton = button;
        }
    }

    /**
     * Handles Return to Dashboard button on the placeholder view.
     */
    @FXML
    private void handleReturnToDashboard(ActionEvent event) {
        handleNavDashboard(event);
    }

    /**
     * Handles Logout button click.
     */
    @FXML
    private void handleLogout(ActionEvent event) {
        Stage stage = (Stage) logoutButton.getScene().getWindow();
        SceneManager.switchToLogin(stage);
    }
}
