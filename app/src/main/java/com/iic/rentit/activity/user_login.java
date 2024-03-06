package com.iic.rentit.activity;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.iic.rentit.R;
import com.iic.rentit.domain.UserDomain;

public class user_login extends AppCompatActivity {

    DatabaseHelper DB = new DatabaseHelper(this);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_login);


        EditText username1, password1;

        Button Log_in_butt = findViewById(R.id.Log_in_button);
        Button Sign_up_activity_button = findViewById(R.id.Sign_up_button);


        username1 = findViewById(R.id.username1);
        password1 = findViewById(R.id.password1);

        Sign_up_activity_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Open_Sign_up_page();
            }
        });

        Log_in_butt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = username1.getText().toString();
                String pass = password1.getText().toString();

                if (username.equals("") || pass.equals("")) {
                    Toast.makeText(user_login.this, "Please enter required data", Toast.LENGTH_SHORT).show();
                } else {
                    boolean checkUserpass = DB.checkUserNamePassword(username, pass);

                    if (checkUserpass) {
                        // Retrieve userId from the database
                        DatabaseHelper databaseHelper = new DatabaseHelper(getApplicationContext());
                        UserDomain users = databaseHelper.getUserDetailsByUsername(getApplicationContext(), username);
                        int userId = users != null ? users.getUserid() : -1;

                        // Save userId into SharedPreferences
                        SharedPreferences sharedPreferences = getSharedPreferences("UserProfile", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putInt("userId", userId);
                        editor.putString("username", username);
                        editor.apply();
                        Toast.makeText(user_login.this, "Log in successful", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(user_login.this, "Username or password is incorrect", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

    }

    /*public void Home_Page(){
        Intent intent = new Intent(this, HomePage.class);
        startActivity(intent);
    }*/
    public void Open_Sign_up_page() {
        Intent intent = new Intent(this, Sign_up.class);
        startActivity(intent);
    }

}