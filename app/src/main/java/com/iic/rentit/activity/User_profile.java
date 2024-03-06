package com.iic.rentit.activity;

import static java.security.AccessController.getContext;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.iic.rentit.R;

public class User_profile extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);
        EditText UsernameUser, PhoneNumberUser, AddressUser, NRCNumberUser, LiscenNumberUser;
        Button UserProfileFinsihed_but;

        //UsernameUser = findViewById(R.id.usernameUser);
        PhoneNumberUser = findViewById(R.id.phoneNumberUser);
        AddressUser = findViewById(R.id.AddressUser);
        NRCNumberUser = findViewById(R.id.NRCNumberUser);
        LiscenNumberUser = findViewById(R.id.LiscenNumberUser);
        UserProfileFinsihed_but = findViewById(R.id.UserProfile_finished_but);
        DatabaseHelper DB = new DatabaseHelper(this);

        UserProfileFinsihed_but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //String Username = UsernameUser.getText().toString();
                String Address = AddressUser.getText().toString();
                String phoneNumber = PhoneNumberUser.getText().toString();
                String NRCNumber = NRCNumberUser.getText().toString();
                String LiscenNumber = LiscenNumberUser.getText().toString();

                SharedPreferences sharedPreferences = getSharedPreferences("UserProfile", Context.MODE_PRIVATE);


                if(Address.equals("") || NRCNumber.equals("") || LiscenNumber.equals("")){
                    Toast.makeText(User_profile.this, "Please enter all the fields", Toast.LENGTH_SHORT).show();
                } else {
                    if (!isValidPhoneNumber(String.valueOf(phoneNumber))) {
                        Toast.makeText(User_profile.this, "Please enter a valid phone number", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    String username = sharedPreferences.getString("username", "");
                    boolean checkuserExists = DB.checkUserExists(username);
                    if(checkuserExists == true){
                        boolean insert = DB.insertUserProfileData(username, phoneNumber, Address, NRCNumber, LiscenNumber);
                        if(insert == false){
                            Toast.makeText(User_profile.this, "User Data Updated Failed", Toast.LENGTH_SHORT).show();
                        } else {
                            // Save the updated data to SharedPreferences
                            SharedPreferences.Editor editor = sharedPreferences.edit();
                            //editor.putString("username", Username);
                            editor.putString("phoneNumber", phoneNumber);
                            editor.putString("address", Address);
                            editor.putString("nrcNumber", NRCNumber);
                            editor.putString("licenseNumber", LiscenNumber);
                            editor.apply();
                            // Create an intent to navigate back to the FragmentProfile
                            Intent intent = new Intent(User_profile.this, MainActivity.class); // Replace YourMainActivity with the actual main activity class
                            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                            startActivity(intent);
                        }
                    } else {
                        Toast.makeText(User_profile.this, "User does not Exist. Please Sign Up again.", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    private boolean isValidPhoneNumber(String phoneNumber) {
        // Regular expression to match a 10-digit phone number
        String regex = "\\d{11}";
        return phoneNumber.matches(regex);
    }
}
