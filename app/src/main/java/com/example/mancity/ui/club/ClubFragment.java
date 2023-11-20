package com.example.mancity.ui.club;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.mancity.R;
import com.example.mancity.databinding.FragmentClubBinding;

public class ClubFragment extends Fragment {

    private FragmentClubBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        ClubViewModel clubViewModel =
                new ViewModelProvider(this).get(ClubViewModel.class);

        binding = FragmentClubBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textClub;
        clubViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);

        Button historyBTN = root.findViewById(R.id.button_history);
        TextView managerBTN = root.findViewById(R.id.managerTextButton);

        historyBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clubHistory();
            }
        });

        managerBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clubManager();
            }
        });

        return root;
    }

    private void clubHistory() {
        Intent intent = new Intent(requireContext(), ClubHistory.class);
        startActivity(intent);
    }

    private void clubManager() {
        Intent intent = new Intent(requireContext(), ClubManager.class);
        startActivity(intent);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}