package com.hambaa;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DashboardFragment extends Fragment {

    private TextView greetingDateFarm;
    private final Handler handler = new Handler(Looper.getMainLooper());
    private final Runnable timeRunnable = new Runnable() {
        @Override
        public void run() {
            updateDateTime();
            handler.postDelayed(this, 10000); // Update every 10 seconds
        }
    };

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dashboard, container, false);

        TextView greetingTitle = view.findViewById(R.id.greetingTitle);
        greetingDateFarm = view.findViewById(R.id.greetingDateFarm);
        TextView totalCows = view.findViewById(R.id.totalCowsValue);
        TextView todayMilk = view.findViewById(R.id.todayMilkValue);
        TextView monthlyRevenue = view.findViewById(R.id.monthlyRevenueValue);
        TextView todayExpenses = view.findViewById(R.id.todayExpensesValue);
        RecyclerView recyclerView = view.findViewById(R.id.activityRecyclerView);
        View addCowAction = view.findViewById(R.id.actionAddCow);

        if (greetingTitle != null) {
            greetingTitle.setText("Good Morning, " + MockData.USER_NAME + "!");
        }

        updateDateTime();

        if (totalCows != null) {
            totalCows.setText(String.valueOf(MockData.TOTAL_COWS));
        }
        if (todayMilk != null) {
            todayMilk.setText("1,420 L");
        }
        if (monthlyRevenue != null) {
            monthlyRevenue.setText("৳ 92,300");
        }
        if (todayExpenses != null) {
            todayExpenses.setText("৳ 14,850");
        }

        if (recyclerView != null) {
            recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
            recyclerView.setAdapter(new ActivityAdapter(MockData.getRecentActivities()));
        }

        if (addCowAction != null) {
            addCowAction.setOnClickListener(v -> {
                if (getActivity() instanceof MainActivity) {
                    ((MainActivity) getActivity()).loadFragment(new AddCowFragment(), "Add Animal");
                }
            });
        }

        return view;
    }

    private void updateDateTime() {
        if (greetingDateFarm != null) {
            SimpleDateFormat sdf = new SimpleDateFormat("EEEE, MMM d, h:mm a", Locale.getDefault());
            String formattedDate = "📅 " + sdf.format(new Date()) + " • Savar Farm";
            greetingDateFarm.setText(formattedDate);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        handler.post(timeRunnable);
    }

    @Override
    public void onPause() {
        super.onPause();
        handler.removeCallbacks(timeRunnable);
    }
}
