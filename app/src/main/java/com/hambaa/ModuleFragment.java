package com.hambaa;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class ModuleFragment extends Fragment {

    private static final String ARG_TITLE = "module_title";

    public static ModuleFragment newInstance(String title) {
        ModuleFragment fragment = new ModuleFragment();
        Bundle args = new Bundle();
        args.putString(ARG_TITLE, title);
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_module, container, false);

        String title = getArguments() != null ? getArguments().getString(ARG_TITLE, "Module") : "Module";

        TextView titleLabel = view.findViewById(R.id.moduleTitleLabel);
        TextView subtitleLabel = view.findViewById(R.id.moduleSubtitleLabel);
        Button returnButton = view.findViewById(R.id.returnButton);

        titleLabel.setText(title + " — Coming Soon");
        subtitleLabel.setText("The " + title + " module is currently under development for HAMBAA Herd OS.");

        returnButton.setOnClickListener(v -> {
            if (getActivity() instanceof MainActivity) {
                ((MainActivity) getActivity()).navigateToDashboard();
            }
        });

        return view;
    }
}
