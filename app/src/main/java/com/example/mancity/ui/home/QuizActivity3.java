package com.example.mancity.ui.home;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mancity.MainActivity;
import com.example.mancity.R;
public class QuizActivity3 extends AppCompatActivity {

    private TextView tvUserName, tvScore;
    private EditText editTextNumber, editTextGeneral;
    private Button submitButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz3);

        // Initialize TextViews
        tvUserName = findViewById(R.id.tvUserName);
        tvScore = findViewById(R.id.tvScore);

        // Initialize EditTexts
        editTextNumber = findViewById(R.id.editTextNumber);
        editTextGeneral = findViewById(R.id.editTextGeneral);

        // Retrieve data passed from QuizActivity2
        Intent intent = getIntent();
        int score = intent.getIntExtra("SCORE", 0);
        String lastName = intent.getStringExtra("LAST_NAME");

        // Set data to TextViews
        tvUserName.setText("Last Name: " + lastName);
        tvScore.setText("Score: " + score + " out of 5");

        // Initialize the Button
        submitButton = findViewById(R.id.submitButton2);

        // Set OnClickListener for the Button
        submitButton.setOnClickListener(v -> {
            Toast.makeText(QuizActivity3.this, "We will send you an email if you win a Man City Jersey", Toast.LENGTH_LONG).show();
            // Navigate back to MainActivity and finish current activity
            startActivity(new Intent(QuizActivity3.this, MainActivity.class));
            finish();
        });
    }
}

