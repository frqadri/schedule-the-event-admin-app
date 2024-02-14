package com.example.halladmin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

public class Manage_Messages_Activity extends AppCompatActivity {

    ImageButton btnGoHome;
    ImageView imgBackIcon;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_messages);

        btnGoHome = findViewById(R.id.btn_go_to_home);
        imgBackIcon = findViewById(R.id.img_back_icon);


        btnGoHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), DashboardActivity.class));
                finish();
            }
        });
        imgBackIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
                finishFromChild(Manage_Messages_Activity.this);
            }
        });

    }
}