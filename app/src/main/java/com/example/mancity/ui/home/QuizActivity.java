package com.example.mancity.ui.home;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mancity.R;
import com.example.mancity.ui.home.QuizActivity2;

import java.util.Calendar;

public class QuizActivity extends AppCompatActivity {

    EditText editTextEmail, editTextFirstName, editTextLastName, editTextDate;
    EditText editTextAge;

    private String firstName;
    private int userAge;

    Button submitButton;
    String selectedDate;
    private RadioGroup radioGroupGender;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        editTextEmail = findViewById(R.id.editTextEmail);
        editTextFirstName = findViewById(R.id.editTextFirstName);
        editTextLastName = findViewById(R.id.editTextLastName);
        editTextDate = findViewById(R.id.editTextDate);
        editTextAge = findViewById(R.id.editTextAge);
        radioGroupGender = findViewById(R.id.radioGroupGender);
        submitButton = findViewById(R.id.submitButton);

        editTextDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog();
            }
        });

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    String email = editTextEmail.getText().toString();
                    String firstName = editTextFirstName.getText().toString();
                    String lastName = editTextLastName.getText().toString();
                    String gender = getSelectedGender();

                    // Check if any field is empty and validate inputs
                    if (!isAnyFieldEmpty(email, firstName, lastName, gender) &&
                            isValidEmail(email) && isValidName(firstName) && isValidName(lastName)) {
                        showConfirmationDialog(lastName, selectedDate, gender);
                    } else {
                        Toast.makeText(QuizActivity.this, "Please fill in all the fields or correct your input", Toast.LENGTH_SHORT).show();
                    }
                    //Exception
                } catch (IllegalArgumentException e) {
                    // Handle exception
                    Toast.makeText(QuizActivity.this, "Error: Invalid input format", Toast.LENGTH_SHORT).show();
                    Log.e("QuizActivity", "Input validation error", e);
                } catch (Exception e) {
                    Toast.makeText(QuizActivity.this, "An unexpected error occurred", Toast.LENGTH_SHORT).show();
                    Log.e("QuizActivity", "Unexpected error", e);
                }
            }
        });
    }

    private void showDatePickerDialog() {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        selectedDate = dayOfMonth + "/" + (month + 1) + "/" + year;
                        editTextDate.setText(selectedDate);

                        // Calculate and display age
                        int age = calculateAge(year);
                        editTextAge.setText(String.valueOf(age));

                        // Calculate age difference with Haaland and display message
                        displayAgeDifferenceMessage(year);
                    }
                }, year, month, day);
        datePickerDialog.show();
    }

    private String getSelectedGender() {
        int selectedId = radioGroupGender.getCheckedRadioButtonId();
        if (selectedId == R.id.radioButtonMale) {
            return "Male";
        } else if (selectedId == R.id.radioButtonFemale) {
            return "Female";
        }
        return ""; // Default or error case
    }

    private boolean isAnyFieldEmpty(String... fields) {
        for (String field : fields) {
            if (field == null || field.trim().isEmpty()) {
                return true;
            }
        }
        // Check if gender is selected
        if (radioGroupGender.getCheckedRadioButtonId() == -1) {
            return true; // No gender selected
        }
        return false;
    }

    private int calculateAge(int yearOfBirth) {
        Calendar calendar = Calendar.getInstance();
        int currentYear = calendar.get(Calendar.YEAR);
        return currentYear - yearOfBirth;
    }

    private void displayAgeDifferenceMessage(int yearOfBirth) {
        int haalandBirthYear = 2000;
        int ageDifference = Math.abs(haalandBirthYear - yearOfBirth);
        String message;

        if (yearOfBirth > haalandBirthYear) {
            message = "Great! You are " + ageDifference + " year(s) younger than Haaland; Keep pursuing your dream!";
        } else if (yearOfBirth < haalandBirthYear) {
            message = "You are " + ageDifference + " year(s) older than Haaland";
        } else {
            message = "Wow! You were born in the same year as Haaland!";
        }

        Toast.makeText(QuizActivity.this, message, Toast.LENGTH_LONG).show();
    }

    private void showConfirmationDialog(final String lastName, final String selectedDate, final String gender) {
        new AlertDialog.Builder(QuizActivity.this)
                .setTitle("Confirm Action")
                .setMessage("Once you enter the quiz, you need to finish it before going back.")
                .setPositiveButton("Sure", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        navigateToNextActivity(lastName, selectedDate, gender);
                    }
                })
                .setNegativeButton("No", null)
                .show();
    }


    private void navigateToNextActivity(String lastName, String selectedDate, String gender) {
        Intent intent = new Intent(QuizActivity.this, QuizActivity2.class);
        intent.putExtra("LAST_NAME", lastName);
        intent.putExtra("DATE", selectedDate);
        intent.putExtra("GENDER", gender);
        startActivity(intent);
        finish();
    }

    private boolean isValidEmail(String email) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        return email.matches(emailRegex);
    }

    private boolean isValidName(String name) {
        return name != null && !name.trim().isEmpty() && name.matches("[a-zA-Z]+");
    }
}
