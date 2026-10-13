package com.hambaa;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.google.android.material.button.MaterialButton;

public class AddCowFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_cow, container, false);

        Button discardButton = view.findViewById(R.id.discardButton);
        MaterialButton saveButton = view.findViewById(R.id.saveCowButton);

        discardButton.setOnClickListener(v -> {
            if (getActivity() instanceof MainActivity) {
                ((MainActivity) getActivity()).navigateToDashboard();
            }
        });

        saveButton.setOnClickListener(v -> {
            Toast.makeText(getContext(), "Cow Record Saved Successfully!", Toast.LENGTH_SHORT).show();
            if (getActivity() instanceof MainActivity) {
                ((MainActivity) getActivity()).navigateToDashboard();
            }
        });

        return view;
    }
}
