package com.iic.rentit.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Toast;

import com.iic.rentit.databinding.ActivityAddingCarBinding;

import java.io.ByteArrayOutputStream;

public class AddingCar extends AppCompatActivity {
    private ActivityAddingCarBinding binding;
    private Bitmap imageToStore;
    private Uri imageFilePath;
    private static final int PICK_IMAGE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddingCarBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        final DatabaseHelper helper = new DatabaseHelper(this);

        binding.adminCarAddImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gallery = new Intent();
                gallery.setType("image/*");
                gallery.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(gallery, "Select Picture"), PICK_IMAGE);
            }
        });

        binding.adminCarAddBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (imageToStore != null) {
                    String name = binding.adminCarName.getText().toString();
                    String description = binding.adminCarDescription.getText().toString();
                    double price = Double.parseDouble(binding.adminCarAddPrice.getText().toString());
                    String engine = binding.adminCarAddEngine.getText().toString();
                    String type = binding.adminCarAddType.getText().toString();
                    double Kilo = Double.parseDouble(binding.adminCarAddKilo.getText().toString());
                    double Acce = Double.parseDouble(binding.adminCarAddAcc.getText().toString());
                    double Speed = Double.parseDouble(binding.adminCarAddSpeed.getText().toString());
                    String Gear = binding.adminCarAddGear.getText().toString();

                    boolean valueInserted = helper.insertDetails(
                            imageToStore,
                            name,
                            description,
                            price,
                            engine,
                            type,
                            Kilo,
                            Acce,
                            Speed,
                            Gear
                    );

                    if (valueInserted) {
                        Toast.makeText(AddingCar.this, "Data inserted Successfully", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(AddingCar.this, "Error occurred", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(AddingCar.this, "Please select an image", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @NonNull Intent data) {
        try {
            super.onActivityResult(requestCode, resultCode, data);
            if (requestCode == PICK_IMAGE && resultCode == RESULT_OK && data.getData() != null) {
                imageFilePath = data.getData();
                imageToStore = MediaStore.Images.Media.getBitmap(getContentResolver(), imageFilePath);
            }
        } catch (Exception e) {
            Toast.makeText(this, "" + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
}
