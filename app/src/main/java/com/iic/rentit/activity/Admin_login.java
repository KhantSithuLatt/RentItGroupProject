package com.iic.rentit.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.iic.rentit.R;

public class Admin_login extends AppCompatActivity {

    EditText username1, password1;
    DatabaseHelper DB = new DatabaseHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_login);

        Button Log_in_butt = findViewById(R.id.Log_in_button_admin);


        username1 = findViewById(R.id.username_admin);
        password1 = findViewById(R.id.password_admin);


        DB.insertDataAdmin("khant", "524");
        DB.insertDataAdmin("thet", "moe");
        Log_in_butt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = username1.getText().toString().trim();
                String pass = password1.getText().toString().trim();

                if(username.isEmpty() || pass.isEmpty()){
                    Toast.makeText(Admin_login.this, "Please enter required data", Toast.LENGTH_SHORT).show();
                }else{
                    boolean checkuserpass = DB.checkUserNamePasswordAdmin(username, pass);
                    if(checkuserpass){
                        Toast.makeText(Admin_login.this, "Log in successful", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(Admin_login.this, Admin_homepage.class);
                        startActivity(intent);
                        finish(); // Finish this activity to prevent going back to it when pressing back button
                    }else{
                        Toast.makeText(Admin_login.this, "Username or password is incorrect", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }


}