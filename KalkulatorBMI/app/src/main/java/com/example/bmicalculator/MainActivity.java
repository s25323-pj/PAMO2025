package com.example.bmicalculator;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText weightInput, heightInput;
    private TextView resultText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        weightInput = findViewById(R.id.weightInput);
        heightInput = findViewById(R.id.heightInput);
        resultText = findViewById(R.id.resultText);
        Button calculateButton = findViewById(R.id.calculateButton);

        calculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateBMI();
            }
        });
    }

    private void calculateBMI() {
        String weightStr = weightInput.getText().toString();
        String heightStr = heightInput.getText().toString();

        if (weightStr.isEmpty() || heightStr.isEmpty()) {
            resultText.setText("Podaj poprawne wartości!");
            return;
        }

        double weight = Double.parseDouble(weightStr);
        double heightCm = Double.parseDouble(heightStr);

        if (weight <= 0 || heightCm <= 0) {
            resultText.setText("Waga i wzrost muszą być większe od zera!");
            return;
        }

        double height = heightCm / 100.0;
        double bmi = weight / (height * height);
        String status = getBMIStatus(bmi);

        resultText.setText(String.format("Twoje BMI: %.2f (%s)", bmi, status));
    }

    private String getBMIStatus(double bmi) {
        if (bmi < 18.5) return "Niedowaga";
        else if (bmi < 25) return "Optimum";
        else if (bmi < 30) return "Nadwaga";
        else return "Otyłość";
    }
}
