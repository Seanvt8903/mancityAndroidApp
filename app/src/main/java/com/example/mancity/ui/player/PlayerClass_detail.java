package com.example.mancity.ui.player;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mancity.R;

public class PlayerClass_detail extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_class_detail);

        // Retrieve data from intent extras
        String name = getIntent().getStringExtra("Name");
        String number = getIntent().getStringExtra("Number");
        String position = getIntent().getStringExtra("Position");
        String nation = getIntent().getStringExtra("Nation");
        int flag = getIntent().getIntExtra("Flag", 0);
        String dob = getIntent().getStringExtra("DOB");
        String joined = getIntent().getStringExtra("Joined");
        String value = getIntent().getStringExtra("Value");
        String description = getIntent().getStringExtra("Description");
        int image = getIntent().getIntExtra("Image", 0);

        // Find views by their IDs
        TextView nameTextView = findViewById(R.id.player_name_click);
        TextView numberTextView = findViewById(R.id.player_number_click);
        TextView positionTextView = findViewById(R.id.player_position_click);
        TextView nationTextView = findViewById(R.id.player_nation_click);
        ImageView flagView = findViewById(R.id.player_flag_click);
        TextView dobTextView = findViewById(R.id.player_dob_click);
        TextView joinedTextView = findViewById(R.id.player_joined_click);
        TextView valueTextView = findViewById(R.id.player_value_click);
        TextView descriptionTextView = findViewById(R.id.player_description_click);
        ImageView imageView = findViewById(R.id.player_image_click);

        // Set retrieved data to the views
        nameTextView.setText(name);
        numberTextView.setText(number);
        positionTextView.setText(position);
        nationTextView.setText(nation);
        flagView.setImageResource(flag);
        dobTextView.setText(dob);
        joinedTextView.setText(joined);
        valueTextView.setText(value);
        descriptionTextView.setText(description);
        imageView.setImageResource(image);
    }
}
