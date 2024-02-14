package com.example.halladmin;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.compose.ui.text.android.TextLayout;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.halladmin.adapters.Images_Adapter_Hall_Info;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;


public class Hall_Information_Activity extends AppCompatActivity implements Images_Adapter_Hall_Info.itemclickListener {

    private String[] categoryitemlist = {"Banquet Halls", "Conference Halls", "Exhibition Centers", "Concert Venues", "Outdoor Event", "Sports Events"};
    private AutoCompleteTextView autoCompleteTextView;
    private ArrayAdapter<String> adapterCategoryItems;
    //    protected TextInputLayout tfHallName, tfHallDescription, tfHallCategory, tfAddress, tfCity, tfDivision, tfPhone, tfHallCapacity, tfHallParking;
    protected EditText etHallName, etHallDescription, etHallCategory, etAddress, etCity, etDivision, etPhone, etHallCapacity, etHallParking;
    private ImageButton btnGoHome;
    private Button btnSubmit, btnUpdate, btnSelectImage;
    private ImageView imgBackIcon;
    private RecyclerView recyclerView;
    Images_Adapter_Hall_Info imagesAdapterHallInfo;
    ArrayList<Uri> uri = new ArrayList<>();
    private static final int Read_Permission = 101;
    private static final int PICK_IMAGE = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hall_information);

        autoCompleteTextView = findViewById(R.id.et_hall_category);
        adapterCategoryItems = new ArrayAdapter<String>(this, R.layout.hall_category_dropdown_list_view, categoryitemlist);
        autoCompleteTextView.setAdapter(adapterCategoryItems);
        btnGoHome = findViewById(R.id.btn_go_to_home);
        imgBackIcon = findViewById(R.id.img_back_icon);
        btnSubmit = findViewById(R.id.btn_submit_hall_info);
        btnUpdate = findViewById(R.id.btn_update_hall_info);
        btnSelectImage = findViewById(R.id.btn_Select_images);
        recyclerView = findViewById(R.id.recycleView_images);
        imagesAdapterHallInfo = new Images_Adapter_Hall_Info(uri, getApplicationContext(),this);

        etHallName = findViewById(R.id.et_hall_name);
        etHallDescription = findViewById(R.id.et_description);
        etHallCategory = findViewById(R.id.et_hall_category);
        etAddress = findViewById(R.id.et_address);
        etCity = findViewById(R.id.et_city);
        etDivision = findViewById(R.id.et_divisions);
        etPhone = findViewById(R.id.et_phone);
        etHallCapacity = findViewById(R.id.et_hall_capacity);
        etHallParking = findViewById(R.id.et_parking_capacity);


        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false));
        recyclerView.setAdapter(imagesAdapterHallInfo);


        btnSelectImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ContextCompat.checkSelfPermission(Hall_Information_Activity.this, Manifest.permission.READ_EXTERNAL_STORAGE)
                        != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(Hall_Information_Activity.this,
                            new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, Read_Permission);

                }
                Intent intent = new Intent();
                intent.setType("image/*");
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2) {
                    intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
                }
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE);
            }
        });

        autoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String item = adapterView.getItemAtPosition(i).toString();
                Toast.makeText(Hall_Information_Activity.this, item + " Selected", Toast.LENGTH_SHORT).show();
            }
        });

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(Hall_Information_Activity.this, "Submit Button Clicked!!", Toast.LENGTH_SHORT).show();
            }
        });
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(Hall_Information_Activity.this, "Update Button Clicked!!", Toast.LENGTH_SHORT).show();
            }
        });
        btnGoHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
                finishFromChild(Hall_Information_Activity.this);

            }
        });
        imgBackIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), DashboardActivity.class));
                finish();
            }
        });

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Validation();
            }
        });


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE && resultCode == RESULT_OK && null != data) {
            if (data.getClipData() != null) {

                int countOfImages = data.getClipData().getItemCount();
                for (int i = 0; i < countOfImages; i++) {
                    if(uri.size()<11){
                        Uri imageUri = data.getClipData().getItemAt(i).getUri();
                        uri.add(imageUri);
                    }else {
                        Toast.makeText(this, "Not allowed to select more than 11 images!", Toast.LENGTH_SHORT).show();
                    }

                }
                imagesAdapterHallInfo.notifyDataSetChanged();
            } else {
                if(uri.size()<11){
                    Uri imageUri = data.getData();
                    uri.add(imageUri);
                }else {
                    Toast.makeText(this, "Not allowed to select more than 11 images!", Toast.LENGTH_SHORT).show();
                }

            }
            imagesAdapterHallInfo.notifyDataSetChanged();
        } else {
            Toast.makeText(this, "You havn't select any image", Toast.LENGTH_LONG).show();
        }
    }

    private void Validation() {
        if (etHallName.getText().toString().isEmpty()) {
            etHallName.setError("Hall name is required!!");
            etHallName.requestFocus();
        } else if (etHallDescription.getText().toString().isEmpty()) {
            etHallDescription.setError("Description is required!!");
            etHallDescription.requestFocus();
        } else if (etHallCategory.getText().toString().isEmpty()) {
            etHallCategory.setError("Category is required!!");
            etHallCategory.requestFocus();
        } else if (etAddress.getText().toString().isEmpty()) {
            etAddress.setError("Address is required!!");
            etAddress.requestFocus();
        } else if (etCity.getText().toString().isEmpty()) {
            etCity.setError("City is required!!");
            etCity.requestFocus();
        } else if (etDivision.getText().toString().isEmpty()) {
            etDivision.setError("Division is required!!");
            etDivision.requestFocus();
        } else if (etPhone.getText().toString().length() < 10) {
            etPhone.setError("Number format is wrong!!");
            etPhone.requestFocus();
        } else if (etPhone.getText().toString().isEmpty()) {
            etPhone.setError("Phone Number is required!!");
            etPhone.requestFocus();
        } else if (etHallCapacity.getText().toString().isEmpty()) {
            etHallCapacity.setError("Hall Capacity is required!!");
            etHallCapacity.requestFocus();
        } else if (etHallParking.getText().toString().isEmpty()) {
            etHallParking.setError("Parking Space is required!!");
            etHallParking.requestFocus();
        } else {
            Toast.makeText(this, "Verification Successfully!!", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void itemClick(int position) {
        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.custom_dialog_zoom);

        TextView textView = dialog.findViewById(R.id.txt_view_custom_dialog);
        ImageView imageView = dialog.findViewById(R.id.image_view_dialog);
        Button closeButton = dialog.findViewById(R.id.btn_close_custom_dialog);

        textView.setText("Quick View");
        imageView.setImageURI(uri.get(position));
        closeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }
}