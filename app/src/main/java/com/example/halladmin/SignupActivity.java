package com.example.halladmin;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.Firebase;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.auth.UserProfileChangeRequest;

import java.io.IOException;
import java.util.Objects;



public class SignupActivity extends AppCompatActivity {

//    private static final String BASE_URL = "http://192.168.100.5/schedule_the_event/db_insert.php";
//    private final OkHttpClient client = new OkHttpClient();
    private EditText etName, etEmail, etPassword, etCPassword;
    private TextInputLayout tfName, tfEmail, tfPassword, tfCPassword;
    private Button btnSignup;
    private TextView txtLogin;
    private ProgressBar progressBar;
    private FirebaseAuth auth;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        etName = findViewById(R.id.et_name);
        etEmail = findViewById(R.id.et_mail);
        etPassword = findViewById(R.id.et_password);
        etCPassword = findViewById(R.id.et_c_password);
        btnSignup = findViewById(R.id.btn_signup);
        txtLogin = findViewById(R.id.txt_login);
        progressBar = findViewById(R.id.progressbar_signup);
        auth = FirebaseAuth.getInstance();

        txtLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });

        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validation();
            }
        });

    }

//    @Override
//    protected void onStart() {
//        super.onStart();
//        if (auth.getCurrentUser() != null) {
//            startActivity(new Intent(getApplicationContext(), DashboardActivity.class));
//            finish();
//        }
//    }

    private void validation() {
        String name, email, phoneNumber, password, cPassword;
        name = etName.getText().toString();
        email = etEmail.getText().toString();
        password = etPassword.getText().toString();
        cPassword = etCPassword.getText().toString();

        if (name.isEmpty()) {
            etName.setError("Name is Required!!");
        } else if (email.isEmpty()) {
            etEmail.setError("Email is Required!!");
        } else if (password.isEmpty()) {
            etPassword.setError("Password is Required!!");
        } else if (password.length() < 8) {
            etPassword.setError("Password Must be greater than 8 character!");
        } else if (cPassword.isEmpty()) {
            etCPassword.setError("Confirm Password is Required!!");
        } else if (!cPassword.equals(password)) {
            etCPassword.setError("Password dose not match!!");
        } else {
            regeisterUser();

        }
    }

    private void regeisterUser() {
        progressBar.setVisibility(View.VISIBLE);
        btnSignup.setVisibility(View.INVISIBLE);
        auth.createUserWithEmailAndPassword(etEmail.getText().toString(), etPassword.getText().toString())
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Objects.requireNonNull(FirebaseAuth.getInstance().getCurrentUser()).sendEmailVerification();
                            Toast.makeText(SignupActivity.this, "Signup Successfully! Please Verify your email address", Toast.LENGTH_SHORT).show();
                            updateuser();
                        } else {
                            progressBar.setVisibility(View.GONE);
                            btnSignup.setVisibility(View.VISIBLE);
                            Toast.makeText(SignupActivity.this, "Error: "+ Objects.requireNonNull(task.getException()).toString(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }



    private void updateuser() {
        UserProfileChangeRequest changeRequest = new UserProfileChangeRequest.Builder()
                .setDisplayName(etName.getText().toString())
                .build();
        Objects.requireNonNull(auth.getCurrentUser()).updateProfile(changeRequest);
        progressBar.setVisibility(View.GONE);
        btnSignup.setVisibility(View.VISIBLE);

        auth.signOut();
        
        openLogin();
    }

    private void openLogin() {
        startActivity(new Intent(getApplicationContext(), LoginActivity.class));
        finish();

    }
//    private void savedata(String name,String email, String password) {
//        RequestBody formdata = new FormBody.Builder()
//                .add("name",name)
//                .add("email",email)
//                .add("password",password)
//                .build();
//        Request request = new Request.Builder().url(BASE_URL).post(formdata).build();
//
//        Call call = client.newCall(request);
//
//        call.enqueue(new Callback() {
//            @Override
//            public void onFailure(@NonNull Call call, @NonNull IOException e) {
//
//            }
//
//            @Override
//            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
//
//            }
//        });
//    }
}