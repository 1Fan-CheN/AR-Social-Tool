package com.example.argreeting.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.argreeting.R;
import com.example.argreeting.activity.login.LoginActivity;
import com.example.argreeting.activity.sendGreeting.SendGreetingActivity;
import com.example.argreeting.activity.viewGreeting.ViewGreetingActivity;
import com.example.argreeting.databinding.FragmentHomeBinding;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    private HomeViewModel homeViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_home, container, false);

        homeViewModel = new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.bind(root);
        binding.setHomeFragmentActions(new HomeFragment.ProxyClass());
        binding.setLifecycleOwner(this);

//        TextView textView = binding.textHome;
        TextView usernameTextView = binding.txtUsername;
        homeViewModel.getUsername().observe(getViewLifecycleOwner(), usernameTextView::setText);

//        homeViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    public class ProxyClass {
        public void buttonOnclick() {
//            homeViewModel.buttonOnclickAction();
            startActivity(new Intent(getActivity(), SendGreetingActivity.class));
        }
    }

}