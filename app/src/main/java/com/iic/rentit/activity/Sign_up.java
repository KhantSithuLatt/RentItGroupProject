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

public class Sign_up extends AppCompatActivity {

    EditText username, password, re_password;
    TextView LogIn_back;
    Button Sign_up_butt;
    DatabaseHelper DB = new DatabaseHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        username = findViewById(R.id.username_input);
        password = findViewById(R.id.password_input);
        re_password = findViewById(R.id.re_password_input);

        Sign_up_butt = findViewById(R.id.Sign_up_butt);
        LogIn_back = findViewById(R.id.LogIn_back);

        Sign_up_butt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = username.getText().toString();
                String pass = password.getText().toString();
                String re_pass = re_password.getText().toString();

                if(user.equals("") || pass.equals("") || re_pass.equals("")){
                    Toast.makeText(Sign_up.this, "Please enter all the fields", Toast.LENGTH_SHORT).show();
                } else {
                    if(pass.equals(re_pass)){
                        boolean checkuser = DB.checkUserName(user);
                        if(checkuser == false){
                            boolean insert = DB.insertDataUser(user, pass);
                            if(insert == true){
                                Toast.makeText(Sign_up.this, "Registerd Successfully", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplicationContext(), user_login.class);
                                startActivity(intent);
                            }
                            else{
                                Toast.makeText(Sign_up.this, "Registration Failed", Toast.LENGTH_SHORT).show();
                            }
                        }else{
                            Toast.makeText(Sign_up.this, "User Already Existed", Toast.LENGTH_SHORT).show();
                        }
                    }else{
                        Toast.makeText(Sign_up.this, "Password are mismatched", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        LogIn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), user_login.class);
                startActivity(intent);
            }

        });
    }
    public void Open_user_profile(){
        Intent intent = new Intent(this, User_profile.class);
        startActivity(intent);
    }
}