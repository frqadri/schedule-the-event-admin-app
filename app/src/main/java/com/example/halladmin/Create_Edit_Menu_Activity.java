package com.example.halladmin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class Create_Edit_Menu_Activity extends AppCompatActivity {

    ImageButton btnGoHome;
    FloatingActionButton btnaddmenu;
    ImageView imgBackIcon;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_edit_menu);

        btnGoHome = findViewById(R.id.btn_go_to_home);
        imgBackIcon = findViewById(R.id.img_back_icon);
        btnaddmenu = findViewById(R.id.btn_create_menu);

        btnaddmenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), Add_Menu_Item_Activity.class));
            }
        });
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
                finishFromChild(Create_Edit_Menu_Activity.this);
            }
        });

    }
}