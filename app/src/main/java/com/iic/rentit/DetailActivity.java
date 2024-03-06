// DetailActivity.java
package com.iic.rentit;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.iic.rentit.activity.DatabaseHelper;
import com.iic.rentit.domain.carDomain;

public class DetailActivity extends AppCompatActivity {

    private int COLUMN_ORDER_NO;
    private DatabaseHelper DB;
    private carDomain selectedCar;

    // Assume these are your UI elements, replace them with your actual UI elements
    private ImageView detailCarImg;
    private TextView detailCarName;
    private TextView detailCarDes;
    private TextView detailCarPrice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        // Initialize your UI elements
        detailCarImg = findViewById(R.id.detailCarImg);
        detailCarName = findViewById(R.id.detailCarName);
        detailCarDes = findViewById(R.id.detailCarDes);
        detailCarPrice = findViewById(R.id.detailCarPrice);

        DB = new DatabaseHelper(this);
            if (selectedCar != null) {
                // Use selectedCar to update UI elements
                detailCarImg.setImageBitmap(selectedCar.getImage());
                detailCarName.setText(selectedCar.getTitle());
                detailCarDes.setText(selectedCar.getDescription());
                detailCarPrice.setText(selectedCar.getPrice());
            } else {
                Toast.makeText(this, "No record found", Toast.LENGTH_SHORT).show();
            }
        }

}
