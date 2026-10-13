package com.hambaa;

import java.util.ArrayList;
import java.util.List;

/**
 * Frontend-only Mock Data provider for HAMBAA Cow Shed Management System.
 */
public class MockData {

    public static final String MOCK_EMAIL = "herdmanager@willowcreek.farm";
    public static final String MOCK_PASSWORD = "123456";

    public static final String USER_NAME = "Rakib";
    public static final String FARM_NAME = "Savar Farm";
    public static final String DAIRY_NAME = "Greenfield Dairy";
    public static final String WEATHER = "24°C • Humid 72%";
    public static final String USER_ROLE = "Senior Farm Supervisor";

    public static final int TOTAL_COWS = 142;
    public static final String TOTAL_COWS_SUB = "+4 this month";
    public static final String TODAY_MILK = "1,420 L";
    public static final String TODAY_MILK_SUB = "+3.8% vs yday";
    public static final String TODAYS_EXP = "৳ 14,850";
    public static final String TODAYS_EXP_SUB = "Feed & Medicine";
    public static final String MILK_REV = "৳ 92,300";
    public static final String MILK_REV_SUB = "1,300 L dispatched";

    public static final int HEALTHY_COWS = 132;
    public static final String HEALTHY_SUB = "93.0% herd";
    public static final int ATTENTION_COWS = 6;
    public static final String ATTENTION_SUB = "Heat & Alert";
    public static final int VACCINES_COUNT = 4;
    public static final String VACCINES_SUB = "Next 7 days";

    public static final String ALERT_COW_TITLE = "Cow #BD-042 (Shapla)";
    public static final String ALERT_COW_TAG = "Rumination";
    public static final String ALERT_COW_DESC = "Chewing dropped 28% below baseline. Inspect pasture intake and pen 3 water troff.";

    public static final String PADDOCK_TITLE = "North Paddock • 08:30 AM";
    public static final String PADDOCK_DESC = "Auto-tag scan verified: 88 head active in Paddock A";

    public record ActivityItem(String title, String subtitle, String timeAgo, String tagText, boolean isGreenTag) {}

    public record CowItem(String tagId, String breed, String status, boolean isHealthy) {}

    public static List<ActivityItem> getRecentActivities() {
        List<ActivityItem> activities = new ArrayList<>();
        activities.add(new ActivityItem("Morning Milking logged (720 L)", "Batch #M-102", "32m ago", "Parlor 1", false));
        activities.add(new ActivityItem("Vaccination administered to Ra...", "FMD Booster • Dr. Hasan", "2h ago", "Done", true));
        activities.add(new ActivityItem("Feed stock received: 250 kg Nap...", "Silo B Intake", "4h ago", "৳ 2,500", false));
        return activities;
    }

    public static List<CowItem> getAllCows() {
        List<CowItem> cows = new ArrayList<>();
        cows.add(new CowItem("#BD-104", "Holstein Friesian Cross", "Healthy", true));
        cows.add(new CowItem("#BD-118", "Sahiwal Cross", "Healthy", true));
        cows.add(new CowItem("#BD-112", "Jersey Cross", "Needs Attention", false));
        cows.add(new CowItem("#BD-101", "Holstein Friesian", "Healthy", true));
        cows.add(new CowItem("#BD-102", "Friesian Local", "Healthy", true));
        cows.add(new CowItem("#BD-103", "Sahiwal Pure", "Healthy", true));
        cows.add(new CowItem("#BD-105", "Jersey Cross", "Needs Attention", false));
        cows.add(new CowItem("#BD-106", "Red Sindhi", "Healthy", true));
        cows.add(new CowItem("#BD-107", "Holstein Friesian", "Healthy", true));
        cows.add(new CowItem("#BD-108", "Local Dairy", "Healthy", true));
        cows.add(new CowItem("#BD-109", "Sahiwal Cross", "Needs Attention", false));
        cows.add(new CowItem("#BD-110", "HF Cross", "Healthy", true));
        return cows;
    }

    public static boolean validateLogin(String email, String password) {
        if (email == null || password == null) {
            return false;
        }
        return MOCK_EMAIL.equalsIgnoreCase(email.trim()) && MOCK_PASSWORD.equals(password);
    }
}
