package com.example.mancity.ui.home;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.mancity.R;
import com.example.mancity.databinding.FragmentHomeBinding;
import com.example.mancity.ui.club.ClubHistory;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textHome;
        homeViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);

        VideoView videoView = binding.videoView;

        String videoPath = "android.resource://" + requireContext().getPackageName() + "/" + R.raw.mcvmu;
        Uri uri = Uri.parse(videoPath);
        videoView.setVideoURI(uri);

        // Create a media controller
        MediaController mediaController = new MediaController(requireContext());
        mediaController.setAnchorView(videoView);
        videoView.setMediaController(mediaController);

        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                // Mute the MediaPlayer by setting volume to 0
                mp.setVolume(0.0f, 0.0f);
            }
        });

        // Start the video
        videoView.start();

        Button tableBTN = root.findViewById(R.id.button_table);
        tableBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clubTable();
            }
        });

        return root;
    }

    private void clubTable() {
        Intent intent = new Intent(requireContext(), HomeTable.class);
        startActivity(intent);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}