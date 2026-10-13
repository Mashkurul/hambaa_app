package com.hambaa.mock;

import java.util.ArrayList;
import java.util.List;

/**
 * Frontend-only Mock Data provider for HAMBAA Cow Shed Management System.
 * No database or backend connection is used.
 */
public class MockData {

    // Mock Login Credentials
    public static final String MOCK_EMAIL = "herdmanager@willowcreek.farm";
    public static final String MOCK_PASSWORD = "123456";

    // Farm Dashboard Metrics
    public static final int TOTAL_COWS = 128;
    public static final int TODAY_MILK_LITERS = 486;
    public static final double MONTHLY_REVENUE_BDT = 185400.0;
    public static final double TODAY_EXPENSES_BDT = 12850.0;
    public static final int HEALTHY_COWS = 117;
    public static final int ATTENTION_COWS = 11;

    // Farm Info
    public static final String FARM_NAME = "Willow Creek Dairy Farm";
    public static final String USER_NAME = "Herd Manager";
    public static final String USER_ROLE = "Senior Farm Supervisor";

    /**
     * Data record representing a recent farm activity entry.
     */
    public record ActivityItem(String title, String subtitle, String timeAgo, String iconType) {}

    /**
     * Returns a list of mock recent activity logs.
     */
    public static List<ActivityItem> getRecentActivities() {
        List<ActivityItem> activities = new ArrayList<>();
        activities.add(new ActivityItem("Milk production recorded — 486 L", "Morning shift milking completed", "10 mins ago", "MILK"));
        activities.add(new ActivityItem("Vaccination updated — Cow #102", "FMD Booster dose administered", "1 hour ago", "HEALTH"));
        activities.add(new ActivityItem("New cow added — Cow #128", "Holstein Friesian heifer registered", "3 hours ago", "COW"));
        activities.add(new ActivityItem("Feed stock updated", "Added 500 kg concentrate feed", "5 hours ago", "FEED"));
        activities.add(new ActivityItem("Health checkup completed", "Veterinary routine check for Barn B", "Yesterday", "VET"));
        return activities;
    }

    /**
     * Validates mock login credentials.
     *
     * @param email user input email
     * @param password user input password
     * @return true if matches mock credentials, false otherwise
     */
    public static boolean validateLogin(String email, String password) {
        if (email == null || password == null) {
            return false;
        }
        return MOCK_EMAIL.equalsIgnoreCase(email.trim()) && MOCK_PASSWORD.equals(password);
    }
}
