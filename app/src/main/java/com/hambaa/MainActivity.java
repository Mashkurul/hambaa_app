package com.hambaa;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.PopupMenu;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    private BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MaterialToolbar toolbar = findViewById(R.id.topToolbar);
        bottomNavigationView = findViewById(R.id.bottomNavigationView);

        toolbar.setOnMenuItemClickListener(item -> {
            int id = item.getItemId();
            if (id == android.R.id.home || id == R.id.action_profile) {
                Toast.makeText(this, "Logged in as: " + MockData.MOCK_EMAIL + "\nFarm: " + MockData.FARM_NAME, Toast.LENGTH_LONG).show();
                return true;
            } else if (id == R.id.action_settings) {
                loadFragment(new SettingsFragment(), "Settings");
                return true;
            } else if (id == R.id.action_logout) {
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
                return true;
            } else if (id == R.id.mod_cows) {
                loadFragment(new CowManagementFragment(), "Cow Management");
                return true;
            } else if (id == R.id.mod_milk) {
                loadFragment(ModuleFragment.newInstance("Milk Production"), "Milk Production");
                return true;
            } else if (id == R.id.mod_health) {
                loadFragment(ModuleFragment.newInstance("Herd Health Triage"), "Health Triage");
                return true;
            } else if (id == R.id.mod_reports) {
                loadFragment(ModuleFragment.newInstance("Reports & Analytics"), "Reports");
                return true;
            }
            return false;
        });

        bottomNavigationView.setOnItemSelectedListener(item -> {
            int id = item.getItemId();
            if (id == R.id.nav_home) {
                loadFragment(new DashboardFragment(), "Home");
                return true;
            } else if (id == R.id.nav_cows) {
                loadFragment(new CowManagementFragment(), "Cow Management");
                return true;
            } else if (id == R.id.nav_more) {
                showMoreMenu();
                return false; // Keep current tab selection intact
            } else if (id == R.id.nav_settings) {
                loadFragment(new SettingsFragment(), "Settings");
                return true;
            }
            return false;
        });

        if (savedInstanceState == null) {
            loadFragment(new DashboardFragment(), "Home");
        }
    }

    private void showMoreMenu() {
        View anchor = bottomNavigationView.findViewById(R.id.nav_more);
        if (anchor == null) {
            anchor = bottomNavigationView;
        }
        PopupMenu popup = new PopupMenu(this, anchor);
        popup.getMenuInflater().inflate(R.menu.menu_modules, popup.getMenu());
        popup.setOnMenuItemClickListener(menuItem -> {
            int itemId = menuItem.getItemId();
            if (itemId == R.id.mod_cows) {
                loadFragment(new CowManagementFragment(), "Cow Management");
                return true;
            } else if (itemId == R.id.mod_milk) {
                loadFragment(ModuleFragment.newInstance("Milk Production"), "Milk Production");
                return true;
            } else if (itemId == R.id.mod_health) {
                loadFragment(ModuleFragment.newInstance("Herd Health Triage"), "Health Triage");
                return true;
            } else if (itemId == R.id.mod_reports) {
                loadFragment(ModuleFragment.newInstance("Reports & Analytics"), "Reports");
                return true;
            }
            return false;
        });
        popup.show();
    }

    public void loadFragment(Fragment fragment, String title) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragmentContainer, fragment)
                .commit();
        MaterialToolbar toolbar = findViewById(R.id.topToolbar);
        if (toolbar != null) {
            toolbar.setTitle(title);
        }
    }

    public void navigateToDashboard() {
        if (bottomNavigationView != null) {
            bottomNavigationView.setSelectedItemId(R.id.nav_home);
        }
    }
}
