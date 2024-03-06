package com.iic.rentit.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.iic.rentit.R;

public class sign_up_admin extends AppCompatActivity {

    EditText username, password, re_password;
    TextView LogIn_back;
    Button Sign_up_butt;
    DatabaseHelper DB = new DatabaseHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_admin);

        username = findViewById(R.id.username_input_admin);
        password = findViewById(R.id.password_input_admin);
        re_password = findViewById(R.id.re_password_input_admin);

        Sign_up_butt = findViewById(R.id.Sign_up_butt_admin);
        LogIn_back = findViewById(R.id.LogIn_back_admin);

        Sign_up_butt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = username.getText().toString();
                String pass = password.getText().toString();
                String re_pass = re_password.getText().toString();




                if(user.equals("") ||  pass.equals("") || re_pass.equals("")){
                    Toast.makeText(sign_up_admin.this, "Please enter all the fields", Toast.LENGTH_SHORT).show();
                } else {
                    if(pass.equals(re_pass)){
                        boolean checkuser = DB.checkUserNameAdmin(user);
                        if(checkuser == false){
                            boolean insert = DB.insertDataAdmin(user, pass);
                            if(insert == true){
                                Toast.makeText(sign_up_admin.this, "Registerd Successfully", Toast.LENGTH_SHORT).show();
                                Toast.makeText(sign_up_admin.this, "New Admin Added Successfully", Toast.LENGTH_SHORT).show();
                            }
                            else{
                                Toast.makeText(sign_up_admin.this, "Registration Failed", Toast.LENGTH_SHORT).show();
                            }
                        }else{
                            Toast.makeText(sign_up_admin.this, "User Already Existed", Toast.LENGTH_SHORT).show();
                        }
                    }else{
                        Toast.makeText(sign_up_admin.this, "Password are mismatched", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });


        /*LogIn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(sign_up_admin.this, Admin_login.class);
                startActivity(intent);
                finish(); // Finish this activity to prevent going back to it when pressing back button
            }
        });*/
    }
}