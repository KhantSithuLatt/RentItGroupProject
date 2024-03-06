package com.iic.rentit.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.iic.rentit.R;
import com.iic.rentit.domain.UserDomain;
import com.iic.rentit.domain.carDomain;

import java.util.ArrayList;

public class CarDetialPage1 extends AppCompatActivity {

    Button RentNowBut;
    DatabaseHelper DB = new DatabaseHelper(this);



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_detial_page1);

        // Retrieve data from Intent extras
        Intent intent = getIntent();
        if (intent != null) {
            String carId = intent.getStringExtra("carId");
            String carName = intent.getStringExtra("carName");
            String carPrice = intent.getStringExtra("carPrice");
            String carEngine = intent.getStringExtra("carEngine");
            String carType = intent.getStringExtra("carType");
            String carGear = intent.getStringExtra("carGear");
            String carAcce = intent.getStringExtra("carAcc");
            String carSpeed = intent.getStringExtra("carSpeed");
            String carKilo = intent.getStringExtra("carKilo");

            // Now you have the data, you can use it as needed
            // For example, set TextViews to display the data
            //TextView carIdTextView = findViewById(R.id.car_id_text_view);
            TextView carNameTextView = findViewById(R.id.car_name_text_view);
            TextView carPriceTextView = findViewById(R.id.car_price_text_view);
            TextView carEngineTextView = findViewById(R.id.car_engine_text_view);
            TextView carTypeTextView = findViewById(R.id.car_type_text_view);
            TextView carAcceTextView = findViewById(R.id.car_acceleration_text_view);
            TextView carSpeedTextView = findViewById(R.id.car_speed_text_view);
            TextView carKiloTextView = findViewById(R.id.car_kilo_text_view);
            TextView carGearTextView = findViewById(R.id.car_gear_text_view);
            ImageView CarImg = findViewById(R.id.CarDetailImg);
            RentNowBut = findViewById(R.id.Book);

            //carIdTextView.setText(carId);
            carNameTextView.setText(carName);
            carPriceTextView.setText("$" +carPrice + "/day");
            carEngineTextView.setText(carEngine);
            carTypeTextView.setText(carType);
            carAcceTextView.setText(carAcce + " m/s");
            carSpeedTextView.setText(carSpeed + " km/h");
            carKiloTextView.setText(carKilo);
            carGearTextView.setText(carGear);

            //image
            // Retrieve the byte array from the Intent
            byte[] byteArray = getIntent().getByteArrayExtra("image");

            // Convert byte array back to Bitmap
            Bitmap bitmap = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);

            // Display the image
            CarImg.setImageBitmap(bitmap);

            // Rent Now button click listener
            RentNowBut.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Get user details from SharedPreferences or wherever you have them
                    SharedPreferences sharedPreferences = getSharedPreferences("UserProfile", Context.MODE_PRIVATE);
                    int userId = sharedPreferences.getInt("userId", -1);
                    String username = sharedPreferences.getString("username", "");

                    int CarId = Integer.parseInt(carId);
                    double CarPrice = Double.parseDouble(carPrice);


                    Log.d("CarDetialPage1", "userId: " + userId + ", username: " + username + ", carName: " + carName);

                    // Insert order into database
                    if (userId != 0 && !username.isEmpty() && !carName.isEmpty() ) {
                        DB.addOrder(userId, username, CarId, carName, CarPrice);
                        Toast.makeText(CarDetialPage1.this, "Order added successfully", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(CarDetialPage1.this, "Error: Missing data", Toast.LENGTH_SHORT).show();
                    }
                }
            });





        }
    }
}