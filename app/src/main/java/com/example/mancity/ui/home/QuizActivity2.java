package com.example.mancity.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mancity.R;

import java.util.ArrayList;
import java.util.List;

public class QuizActivity2 extends AppCompatActivity {

    private TextView questionTextView;
    private Button optionButton1, optionButton2, optionButton3, optionButton4;

    private List<Question> questions;
    private int currentQuestionIndex = 0;
    private int score = 0;

    private String lastName;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz2);

        // Retrieve the passed from QuizActivity1
        Intent intent = getIntent();
        lastName = intent.getStringExtra("LAST_NAME");


        questionTextView = findViewById(R.id.questionTextView);
        optionButton1 = findViewById(R.id.optionButton1);
        optionButton2 = findViewById(R.id.optionButton2);
        optionButton3 = findViewById(R.id.optionButton3);
        optionButton4 = findViewById(R.id.optionButton4);

        initializeQuestions();
        displayCurrentQuestion();

        setupOptionButtons();
    }

    private void initializeQuestions() {
        questions = new ArrayList<>();

        questions.add(new Question(
                "Question 1: What is the name of Manchester City's home stadium?\n",
                new String[]{"Etihad Stadium", "Old Trafford", "Anfield", "Stamford Bridge"},
                0)); // index of the correct answer

        questions.add(new Question(
                "Question 2: Who is Manchester City's all-time leading goal scorer?",
                new String[]{"Erling Haaland", "Sergio Agüero", "Kevin DeBruyne", "Yaya Touré"},
                1));

        questions.add(new Question(
                "Question 3: Which manager guided Manchester City to their first Premier League title in the 2011-2012 season?",
                new String[]{"Roberto Mancini", "Manuel Pellegrini", "Pep Guardiola", "Mark Hughes"},
                0));

        questions.add(new Question(
                "Question 4: What year did Manchester City win their first UEFA Champions League title?",
                new String[]{"2008", "2016", "2020", "2023"},
                3));

        questions.add(new Question(
                "Question 5: What was the highest position Manchester City finished in the Premier League before the Abu Dhabi United Group takeover in 2008?",
                new String[]{"1st", "3rd", "5th", "8th"},
                1));
    }


    private void displayCurrentQuestion() {
        Question currentQuestion = questions.get(currentQuestionIndex);
        questionTextView.setText(currentQuestion.getQuestionText());
        optionButton1.setText(currentQuestion.getOptions()[0]);
        optionButton2.setText(currentQuestion.getOptions()[1]);
        optionButton3.setText(currentQuestion.getOptions()[2]);
        optionButton4.setText(currentQuestion.getOptions()[3]);
    }

    private void setupOptionButtons() {
        optionButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(0);
            }
        });

        optionButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(1);
            }
        });

        optionButton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(2);
            }
        });

        optionButton4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(3);
            }
        });
    }

    private void checkAnswer(int selectedOptionIndex) {
        if (questions.get(currentQuestionIndex).getCorrectAnswerIndex() == selectedOptionIndex) {
            score++;
            Toast.makeText(this, "You got it!", Toast.LENGTH_SHORT).show();
        } else {
            String correctAnswer = questions.get(currentQuestionIndex).getOptions()[questions.get(currentQuestionIndex).getCorrectAnswerIndex()];
            Toast.makeText(this, "Wrong answer! The correct answer is " + correctAnswer, Toast.LENGTH_LONG).show();
        }
        proceedToNext();
    }

    private void proceedToNext() {
        currentQuestionIndex++;
        if (currentQuestionIndex < questions.size()) {
            displayCurrentQuestion();
        } else {
            finishQuiz();
        }
    }

    private void finishQuiz() {
        Toast.makeText(this, "Your score: " + score, Toast.LENGTH_LONG).show();

        // Start QuizActivity3 and pass the score and lastName
        Intent intent = new Intent(QuizActivity2.this, QuizActivity3.class);
        intent.putExtra("SCORE", score);
        intent.putExtra("LAST_NAME", lastName); // Include lastName in the intent
        startActivity(intent);
        finish();
    }

    // Inner class for Question model
    private class Question {
        private String questionText;
        private String[] options;
        private int correctAnswerIndex;

        public Question(String questionText, String[] options, int correctAnswerIndex) {
            this.questionText = questionText;
            this.options = options;
            this.correctAnswerIndex = correctAnswerIndex;
        }

        public String getQuestionText() {
            return questionText;
        }

        public String[] getOptions() {
            return options;
        }

        public int getCorrectAnswerIndex() {
            return correctAnswerIndex;
        }
    }

    private void navigateToNextActivity() {
        Intent intent = new Intent(QuizActivity2.this, QuizActivity3.class);
        intent.putExtra("SCORE", score);
        intent.putExtra("LAST_NAME", lastName);
        startActivity(intent);
        finish();
    }

}
