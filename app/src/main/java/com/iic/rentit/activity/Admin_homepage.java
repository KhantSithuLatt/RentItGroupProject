package com.iic.rentit.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.iic.rentit.R;
import com.iic.rentit.activity.user_login;

public class Admin_homepage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_homepage);

        Button AddNewAdmin = findViewById(R.id.AddNewAdmin);
        Button AddNewCar = findViewById(R.id.addCarAdminBtn);

        AddNewAdmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), sign_up_admin.class);
                startActivity(intent);

            }
        });
        AddNewCar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), AddingCar.class);
                startActivity(intent);

            }
        });
    }
}