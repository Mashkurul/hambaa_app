package com.hambaa;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.google.android.material.button.MaterialButton;

public class CowManagementFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cow_management, container, false);

        MaterialButton btnAddCow = view.findViewById(R.id.btnAddCowHub);
        btnAddCow.setOnClickListener(v -> {
            if (getActivity() instanceof MainActivity) {
                ((MainActivity) getActivity()).loadFragment(new AddCowFragment(), "Add Animal");
            }
        });

        Button btnViewAllCows = view.findViewById(R.id.btnViewAllCows);
        if (btnViewAllCows != null) {
            btnViewAllCows.setOnClickListener(v -> {
                if (getActivity() instanceof MainActivity) {
                    ((MainActivity) getActivity()).loadFragment(new AllCowsFragment(), "All Herd Cows");
                }
            });
        }

        View card1 = view.findViewById(R.id.cardCow1);
        View card2 = view.findViewById(R.id.cardCow2);
        View card3 = view.findViewById(R.id.cardCow3);

        View.OnClickListener detailsListener = v -> {
            if (getActivity() instanceof MainActivity) {
                ((MainActivity) getActivity()).loadFragment(new CowDetailsFragment(), "Cow Details");
            }
        };

        if (card1 != null) card1.setOnClickListener(detailsListener);
        if (card2 != null) card2.setOnClickListener(detailsListener);
        if (card3 != null) card3.setOnClickListener(detailsListener);

        return view;
    }
}
