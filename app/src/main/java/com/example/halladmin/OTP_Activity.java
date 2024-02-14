package com.example.halladmin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class OTP_Activity extends AppCompatActivity {

    EditText inputotp1, inputotp2, inputotp3, inputotp4, inputotp5, inputotp6;
    TextView emailShow;
    Button submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp);

        inputotp1 = findViewById(R.id.input_otp_1);
        inputotp2 = findViewById(R.id.input_otp_2);
        inputotp3 = findViewById(R.id.input_otp_3);
        inputotp4 = findViewById(R.id.input_otp_4);
        inputotp5 = findViewById(R.id.input_otp_5);
        inputotp6 = findViewById(R.id.input_otp_6);
        submit = findViewById(R.id.btn_otp_submit);
        emailShow = findViewById(R.id.text_email_show);

        emailShow.setText(getIntent().getStringExtra("signup_data"));


        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (validation()) {
                    Toast.makeText(getApplicationContext(), "Verify Successfully!", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(), DashboardActivity.class);
                    startActivity(intent);
                    finish();
                }

            }
        });

        numberForward();
    }

    private boolean validation() {
        String number1, number2, number3, number4, number5, number6;
        number1 = inputotp1.getText().toString();
        number2 = inputotp2.getText().toString();
        number3 = inputotp3.getText().toString();
        number4 = inputotp4.getText().toString();
        number5 = inputotp5.getText().toString();
        number6 = inputotp6.getText().toString();

        if (number1.isEmpty()) {
            inputotp1.setError("Please enter value here!!");
            return false;
        } else if (number2.isEmpty()) {
            inputotp2.setError("Please enter value here!!");
            return false;
        } else if (number3.isEmpty()) {
            inputotp3.setError("Please enter value here!!");
            return false;
        } else if (number4.isEmpty()) {
            inputotp4.setError("Please enter value here!!");
            return false;
        } else if (number5.isEmpty()) {
            inputotp5.setError("Please enter value here!!");
            return false;
        } else if (number6.isEmpty()) {
            inputotp6.setError("Please enter value here!!");
            return false;
        }

        return true;
    }

    private void numberForward() {
        inputotp1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence number, int i, int i1, int i2) {
                if (!number.toString().trim().isEmpty()) {
                    inputotp2.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        inputotp2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence number, int i, int i1, int i2) {
                if (!number.toString().trim().isEmpty()) {
                    inputotp3.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        inputotp3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence number, int i, int i1, int i2) {
                if (!number.toString().trim().isEmpty()) {
                    inputotp4.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        inputotp4.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence number, int i, int i1, int i2) {
                if (!number.toString().trim().isEmpty()) {
                    inputotp5.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        inputotp5.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence number, int i, int i1, int i2) {
                if (!number.toString().trim().isEmpty()) {
                    inputotp6.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

}