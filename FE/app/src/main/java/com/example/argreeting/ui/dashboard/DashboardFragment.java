package com.example.argreeting.ui.dashboard;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.argreeting.MainActivity;
import com.example.argreeting.R;
import com.example.argreeting.activity.login.LoginActivity;
import com.example.argreeting.activity.profile.ProfileActivity;
import com.example.argreeting.databinding.FragmentDashboardBinding;
import com.example.argreeting.generated.callback.OnClickListener;

public class DashboardFragment extends Fragment {

    private FragmentDashboardBinding binding;
    private DashboardViewModel dashboardViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_dashboard, container, false);

        dashboardViewModel = new ViewModelProvider(this).get(DashboardViewModel.class);

        binding = FragmentDashboardBinding.bind(root);
        binding.setFragment(new ProxyClass());
        binding.setLifecycleOwner(this);

//        dashboardViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    public class ProxyClass {
        public void btnDashProfile() {
            startActivity(new Intent(getActivity(), ProfileActivity.class));
        }
        public void btnDashLogout() {
            startActivity(new Intent(getActivity(), LoginActivity.class));
        }
    }

}