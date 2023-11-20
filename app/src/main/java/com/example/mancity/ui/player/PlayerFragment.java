package com.example.mancity.ui.player;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.SearchView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.example.mancity.R;
import com.example.mancity.databinding.FragmentPlayerBinding;
import com.example.mancity.MainActivity;

import java.util.ArrayList;


public class PlayerFragment extends Fragment implements Player_RecycleViewInterface {

    ArrayList<PlayerModel> playerModels = new ArrayList<>();

    int [] man_city_player_image = {R.drawable.haaland, R.drawable.alvarez, R.drawable.philips, R.drawable.kovacic, R.drawable.jack, R.drawable.doku, R.drawable.rodri, R.drawable.kdb, R.drawable.silva,
            R.drawable.nunes, R.drawable.mcatee, R.drawable.perrone, R.drawable.foden, R.drawable.bobb, R.drawable.walker, R.drawable.ruben, R.drawable.stones, R.drawable.ake,
            R.drawable.cancelo, R.drawable.gomez, R.drawable.gvardiol, R.drawable.akanji, R.drawable.rico, R.drawable.jwe, R.drawable.ortega, R.drawable.ederson, R.drawable.carson};

    int [] man_city_player_flag = {R.mipmap.norway, R.mipmap.argentina, R.mipmap.england, R.mipmap.croatia, R.mipmap.england, R.mipmap.belgium, R.mipmap.spain, R.mipmap.belgium, R.mipmap.portugal,
            R.mipmap.portugal, R.mipmap.england, R.mipmap.argentina, R.mipmap.england, R.mipmap.norway, R.mipmap.england, R.mipmap.portugal, R.mipmap.england, R.mipmap.netherlands,
            R.mipmap.portugal, R.mipmap.spain, R.mipmap.croatia, R.mipmap.switzerland, R.mipmap.england, R.mipmap.england, R.mipmap.germany, R.mipmap.brazil, R.mipmap.england};

    private FragmentPlayerBinding binding;

    private Player_RecycleViewAdapter adapter;

    ArrayList<PlayerModel> playerModels_menu;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        PlayerViewModel playerViewModel =
                new ViewModelProvider(this).get(PlayerViewModel.class);

        binding = FragmentPlayerBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textPlayer;
        playerViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);

        //Recycle view
        RecyclerView recyclerView = binding.playerRecycleView;

        setUpPlayerModel();

        Player_RecycleViewAdapter adapter = new Player_RecycleViewAdapter(requireContext(), playerModels, this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));

        return root;

    }

    private void setUpPlayerModel(){
        String[] man_city_player = getResources().getStringArray(R.array.man_city_player);
        String[] man_city_player_number = getResources().getStringArray(R.array.man_city_player_number);
        String[] man_city_player_position = getResources().getStringArray(R.array.man_city_player_position);
        String[] man_city_player_nation = getResources().getStringArray(R.array.man_city_player_nation);
        String[] man_city_player_dob = getResources().getStringArray(R.array.man_city_player_dob);
        String[] man_city_player_joined = getResources().getStringArray(R.array.man_city_player_joined);
        String[] man_city_player_value = getResources().getStringArray(R.array.man_city_player_value);
        String[] man_city_player_description = getResources().getStringArray(R.array.man_city_player_description);

        for (int i = 0; i < man_city_player.length; i++){
            playerModels.add(new PlayerModel(man_city_player[i],
                                             man_city_player_number[i],
                                             man_city_player_position[i],
                                             man_city_player_nation[i],
                                             man_city_player_flag[i],
                                             man_city_player_dob[i],
                                             man_city_player_joined[i],
                                             man_city_player_value[i],
                                             man_city_player_description[i],
                                             man_city_player_image[i]));
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void onItemClick(int position) {
        Intent intent = new Intent(requireActivity(), PlayerClass_detail.class);

        // Add data to the intent
        intent.putExtra("Name", playerModels.get(position).getMan_city_player());
        intent.putExtra("Number", playerModels.get(position).getMan_city_player_number());
        intent.putExtra("Position", playerModels.get(position).getMan_city_player_position());
        intent.putExtra("Nation", playerModels.get(position).getMan_city_player_nation());
        intent.putExtra("Flag", playerModels.get(position).getFlag());
        intent.putExtra("DOB", playerModels.get(position).getMan_city_player_dob());
        intent.putExtra("Joined", playerModels.get(position).getMan_city_player_joined());
        intent.putExtra("Value", playerModels.get(position).getMan_city_player_value());
        intent.putExtra("Description", playerModels.get(position).getMan_city_player_description());
        intent.putExtra("Image", playerModels.get(position).getImage());

        // Start the activity using the intent
        startActivity(intent);
    }


}