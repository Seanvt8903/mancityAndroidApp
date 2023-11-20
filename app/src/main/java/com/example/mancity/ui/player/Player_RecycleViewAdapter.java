package com.example.mancity.ui.player;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.LinearLayoutManager;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mancity.R;

import java.util.ArrayList;
import java.util.List;


public class Player_RecycleViewAdapter extends RecyclerView.Adapter<Player_RecycleViewAdapter.MyViewHolder> implements Filterable{

    private final Player_RecycleViewInterface recycleViewInterface;
    Context context;
    static ArrayList<PlayerModel> playerModels;
    static ArrayList<PlayerModel> playerModelsFull;

    public Player_RecycleViewAdapter(Context context, ArrayList<PlayerModel> playerModels, Player_RecycleViewInterface recycleViewInterface){
        this.context = context;
        this.playerModels = playerModels;
        this.playerModelsFull = new ArrayList<>(playerModels);
        this.recycleViewInterface = recycleViewInterface;
        setHasStableIds(true);  // Indicates that each item has a stable, unique ID

    }

    @NonNull
    @Override
    public Player_RecycleViewAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //Inflate layout (Give a look to the rows)
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.player_recycle_view_row, parent, false);

        return new Player_RecycleViewAdapter.MyViewHolder(view, recycleViewInterface);
    }

    @Override
    public void onBindViewHolder(@NonNull Player_RecycleViewAdapter.MyViewHolder holder, int position) {
        //Assign value to the views based on the position
        PlayerModel currentPlayer = playerModels.get(position);

        holder.tvName.setText (currentPlayer.getMan_city_player());
        holder.tvNumber.setText (currentPlayer.getMan_city_player_number());
        holder.tvNation.setText (currentPlayer.getMan_city_player_nation());
        holder.flagView.setImageResource (currentPlayer.getFlag());
        holder.tvPosition.setText (currentPlayer.getMan_city_player_position());
        holder.imageView.setImageResource (currentPlayer.getImage());
    }

    @Override
    public int getItemCount() {
        //Total number of items
        return playerModels.size();
    }

    @Override
    public long getItemId(int position) {
        return playerModels.get(position).getPlayerId();  // Unique ID for each player
    }

    @Override
    public Filter getFilter() {
        return playerFilter;
    }

    private Filter playerFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            Log.d("Filter", "Performing filtering");
            ArrayList<PlayerModel> filteredList = new ArrayList<>();

            if (constraint == null || constraint.length() == 0) {
                filteredList.addAll(playerModelsFull);
            } else {
                String filterPattern = constraint.toString().toLowerCase().trim();

                for (PlayerModel player : playerModelsFull) {
                    Log.d("Filter", "Player name: " + player.getMan_city_player());
                    if (player.man_city_player.toLowerCase().contains(filterPattern)) {
                        Log.d("Filter", "Added player: " + player.getMan_city_player());
                        filteredList.add(player);
                    }
                    if (player.man_city_player_number.toLowerCase().contains(filterPattern)) {
                        Log.d("Filter", "Added player: " + player.getMan_city_player_number());
                        filteredList.add(player);
                    }
                    if (player.man_city_player_nation.toLowerCase().contains(filterPattern)) {
                        Log.d("Filter", "Added player: " + player.getMan_city_player_nation());
                        filteredList.add(player);
                    }
                    if (player.man_city_player_position.toLowerCase().contains(filterPattern)) {
                        Log.d("Filter", "Added player: " + player.getMan_city_player_position());
                        filteredList.add(player);
                    }
                }
            }

            FilterResults results = new FilterResults();
            results.values = filteredList;
            results.count = filteredList.size();
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            playerModels.clear();
            playerModels.addAll((ArrayList<PlayerModel>) results.values);
            notifyDataSetChanged();
        }
    };



    public static class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView flagView, imageView;
        TextView tvName, tvNumber, tvNation, tvPosition;

        public MyViewHolder(@NonNull View itemView, Player_RecycleViewInterface recycleViewInterface) {
            super(itemView);
            flagView = itemView.findViewById(R.id.flagView2);
            imageView = itemView.findViewById(R.id.imageView2);
            tvName = itemView.findViewById(R.id.textView2);
            tvNumber = itemView.findViewById(R.id.textView3);
            tvNation = itemView.findViewById(R.id.textView5);
            tvPosition = itemView.findViewById(R.id.textView4);
            itemView.setOnClickListener(view -> {
                int pos = getAdapterPosition();
                if (pos != RecyclerView.NO_POSITION && recycleViewInterface != null) {
                    recycleViewInterface.onItemClick(pos);
                }
            });
        }
    }
}
