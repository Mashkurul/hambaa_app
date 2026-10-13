package com.hambaa;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.fragment.app.Fragment;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.switchmaterial.SwitchMaterial;

public class SettingsFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_settings, container, false);

        TextView emailView = view.findViewById(R.id.userInfoEmail);
        TextView farmView = view.findViewById(R.id.userInfoFarm);
        TextView roleView = view.findViewById(R.id.userInfoRole);
        SwitchMaterial themeSwitch = view.findViewById(R.id.themeSwitch);
        MaterialButton logoutButton = view.findViewById(R.id.settingsLogoutButton);

        emailView.setText("Email: " + MockData.MOCK_EMAIL);
        farmView.setText("Farm: " + MockData.FARM_NAME + " (" + MockData.DAIRY_NAME + ")");
        roleView.setText("Role: " + MockData.USER_ROLE);

        int currentMode = AppCompatDelegate.getDefaultNightMode();
        themeSwitch.setChecked(currentMode == AppCompatDelegate.MODE_NIGHT_YES);

        themeSwitch.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                Toast.makeText(getContext(), "Dark theme enabled", Toast.LENGTH_SHORT).show();
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                Toast.makeText(getContext(), "Light theme enabled", Toast.LENGTH_SHORT).show();
            }
        });

        logoutButton.setOnClickListener(v -> {
            if (getActivity() != null) {
                Intent intent = new Intent(getActivity(), LoginActivity.class);
                startActivity(intent);
                getActivity().finish();
            }
        });

        return view;
    }
}
