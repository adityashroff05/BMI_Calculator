package com.example.bmicalculator;

import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    EditText edtweight, edtheightft, edtheightin;
    Button btn;
    TextView txtResult;
    LinearLayout main;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        // Linking XML views
        edtweight = findViewById(R.id.edtweight);
        edtheightft = findViewById(R.id.edtft);
        edtheightin = findViewById(R.id.edtheightIn);
        btn = findViewById(R.id.btn);
        txtResult = findViewById(R.id.txtResult);
        main = findViewById(R.id.main);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Validation
                if (TextUtils.isEmpty(edtweight.getText().toString())) {
                    edtweight.setError("Enter weight");
                    return;
                }
                if (TextUtils.isEmpty(edtheightft.getText().toString())) {
                    edtheightft.setError("Enter height (feet)");
                    return;
                }
                if (TextUtils.isEmpty(edtheightin.getText().toString())) {
                    edtheightin.setError("Enter height (inch)");
                    return;
                }

                // Get values
                int wt = Integer.parseInt(edtweight.getText().toString());
                int htft = Integer.parseInt(edtheightft.getText().toString());
                int htin = Integer.parseInt(edtheightin.getText().toString());

                // Convert height to meters
                int totalInches = htft * 12 + htin;
                double totalCm = totalInches * 2.54;
                double totalMeters = totalCm / 100;

                // Calculate BMI
                double bmi = wt / (totalMeters * totalMeters);

                String message;

                if (bmi > 25) {
                    message = "You are Overweight\nBMI: " + String.format("%.2f", bmi);
                    main.setBackgroundColor(getResources().getColor(R.color.colorOw));
                } else if (bmi < 18.5) {
                    message = "You are Underweight\nBMI: " + String.format("%.2f", bmi);
                    main.setBackgroundColor(getResources().getColor(R.color.colorUw));
                } else {
                    message = "You are Healthy\nBMI: " + String.format("%.2f", bmi);
                    main.setBackgroundColor(getResources().getColor(R.color.colorH));
                }

                txtResult.setText(message);
            }
        });
    }
}