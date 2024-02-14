package com.example.halladmin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Objects;

public class LoginActivity extends AppCompatActivity {

    private EditText etMail, etPassword;
    private Button btnLogin;
    private TextView txtSignup, txtForgotPass;
    private ProgressBar progressBar;
    private FirebaseAuth auth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etMail = findViewById(R.id.et_mail);
        etPassword = findViewById(R.id.et_password);
        btnLogin = findViewById(R.id.btn_login);
        txtSignup = findViewById(R.id.txt_signup);
        txtForgotPass = findViewById(R.id.txt_forgot_pass);
        progressBar = findViewById(R.id.progressbar_login);
        auth = FirebaseAuth.getInstance();

        txtSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), SignupActivity.class);
                startActivity(intent);
                finish();
            }
        });

        txtForgotPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), ForgotPasswordActivity.class));
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validation();
//                    Toast.makeText(LoginActivity.this, "Login Successfully.", Toast.LENGTH_SHORT).show();
//                    Intent intent = new Intent(getApplicationContext(), DashboardActivity.class);
//                    startActivity(intent);
//                    finish();

            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        if(auth.getCurrentUser() == null){
            Toast.makeText(this, "Welcome", Toast.LENGTH_SHORT).show();
        } else if (auth.getCurrentUser() != null){
            startActivity(new Intent(getApplicationContext(),DashboardActivity.class));
            finish();
        }
    }

    private void validation() {
        String email, password;
        email = etMail.getText().toString();
        password = etPassword.getText().toString();

        if (email.isEmpty()) {
            etMail.setError("Email is Required!!");
        } else if (password.isEmpty()) {
            etPassword.setError("Password is Required!!");
        } else if (password.length() < 8) {
            etPassword.setError("Password Must be greater than 8 character!");
        } else {
            loginUser();
        }
    }

    private void loginUser() {
        progressBar.setVisibility(View.VISIBLE);
        btnLogin.setVisibility(View.INVISIBLE);
        auth.signInWithEmailAndPassword(etMail.getText().toString(), etPassword.getText().toString())
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            if (FirebaseAuth.getInstance().getCurrentUser().isEmailVerified()) {
                                Toast.makeText(LoginActivity.this, "Login Successful", Toast.LENGTH_SHORT).show();
                                progressBar.setVisibility(View.GONE);
                                btnLogin.setVisibility(View.VISIBLE);
                                startActivity(new Intent(getApplicationContext(), DashboardActivity.class));
                                finish();
                            } else {
                                progressBar.setVisibility(View.GONE);
                                btnLogin.setVisibility(View.VISIBLE);
                                Toast.makeText(LoginActivity.this, "Please Verify your email address!", Toast.LENGTH_LONG).show();
                            }

                        } else {
                            progressBar.setVisibility(View.GONE);
                            btnLogin.setVisibility(View.VISIBLE);
                            Toast.makeText(LoginActivity.this, "Error: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}