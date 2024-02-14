package com.example.halladmin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

//import com.example.halladmin.fragments.FragmentAvailability;
//import com.example.halladmin.fragments.FragmentBooking;
//import com.example.halladmin.fragments.FragmentChat;
//import com.example.halladmin.fragments.FragmentHome;
//import com.example.halladmin.fragments.FragmentMenu;
//import com.google.android.material.bottomnavigation.BottomNavigationView;
//import com.google.android.material.navigation.NavigationBarView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Objects;

public class DashboardActivity extends AppCompatActivity {

//    private BottomNavigationView navigationView;
    TextView name;
    private ImageButton logout;
    Button btn_info_update, btn_edit_menu,btn_bookings,btn_chat;
    private FirebaseAuth auth;
    private FirebaseUser user;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

//        navigationView = findViewById(R.id.bottom_navigation);
        name = findViewById(R.id.person_name);
        logout = findViewById(R.id.btn_logout);
        btn_info_update = findViewById(R.id.btn_hall_info_update);
        btn_edit_menu = findViewById(R.id.btn_menu_edit);
        btn_bookings = findViewById(R.id.btn_manage_booking);
        btn_chat = findViewById(R.id.btn_chat);

        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();

        if(user != null){
            name.setText(user.getDisplayName());

        }
        btn_info_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),Hall_Information_Activity.class);
                startActivity(intent);
            }
        });
        btn_edit_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),Create_Edit_Menu_Activity.class);
                startActivity(intent);
            }
        });
        btn_bookings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),Manage_Bookings_Activity.class);
                startActivity(intent);
            }
        });
        btn_chat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),Manage_Messages_Activity.class);
                startActivity(intent);
            }
        });




        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                auth.signOut();
                startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                finish();
            }
        });

    }

//    @Override
//    protected void onStart() {
//        super.onStart();
//        if(user == null){
//            startActivity(new Intent(getApplicationContext(),LoginActivity.class));
//            finish();
//        }
//    }

//    private void loadFragment(Fragment fragment){
//        FragmentManager fm = getSupportFragmentManager();
//        fm.beginTransaction().replace(R.id.frameLayout,fragment).commit();
//    }
}