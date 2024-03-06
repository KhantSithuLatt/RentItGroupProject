package com.iic.rentit.activity;



import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;

import com.iic.rentit.R;
import com.iic.rentit.adapter.CarAdapter;
import com.iic.rentit.databinding.ActivityMainBinding;
import com.iic.rentit.domain.carDomain;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    ActivityMainBinding binding;

    private RecyclerView.Adapter adapterCourseLists;
    private RecyclerView recyclerViewCourse;

    @SuppressLint("NonConstantResourceId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        replaceFragment(new FragmentHome());


        binding.bottomNavigationView.setOnItemSelectedListener(item -> {

            int itemId = item.getItemId();

            if (itemId == R.id.bottomHome){
                replaceFragment(new FragmentHome());

            } else if (itemId == R.id.bottomFavourite) {
                replaceFragment(new FragmentFavourite());

            }else if(itemId == R.id.bottomProfile){
                replaceFragment(new FragmentProfile());

            }

            return true;
        });


    }



    private void replaceFragment(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout,fragment);
        fragmentTransaction.commit();

    }







}