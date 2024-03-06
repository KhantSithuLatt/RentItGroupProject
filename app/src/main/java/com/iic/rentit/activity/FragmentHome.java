// FragmentHome.java
package com.iic.rentit.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.iic.rentit.DetailActivity;
import com.iic.rentit.adapter.CarAdapter;
import com.iic.rentit.databinding.FragmentHomeBinding;
import com.iic.rentit.domain.carDomain;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

public class FragmentHome extends Fragment {
    private FragmentHomeBinding binding;
    private DatabaseHelper helper;
    private CarAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        helper = new DatabaseHelper(requireContext());
        ArrayList<carDomain> list = helper.getCarDomain();

        adapter = new CarAdapter(list, requireContext());

        // Set the click listener for the adapter
        adapter.setListener(new CarAdapter.onItemClickListener() {
            @Override
            public void onItemClick(carDomain car) {
                Intent intent = new Intent(getActivity(), CarDetialPage1.class);
                intent.putExtra("carId", car.getOrderno());
                intent.putExtra("carName", car.getTitle());
                intent.putExtra("carPrice", car.getPrice());
                intent.putExtra("carType", car.getType());
                intent.putExtra("carEngine", car.getEngine());
                intent.putExtra("carKilo", car.getKilo());
                intent.putExtra("carAcc", car.getAcc());
                intent.putExtra("carSpeed", car.getSpeed());
                intent.putExtra("carGear", car.getGear());

                //image
                // Convert Bitmap to byte array
                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                car.getImage().compress(Bitmap.CompressFormat.PNG, 100, stream);
                byte[] byteArray = stream.toByteArray();

// Pass the byte array to the Intent
                intent.putExtra("image", byteArray);
                startActivity(intent);




            }
        });

        binding.CarRecyclerView.setAdapter(adapter);

        LinearLayoutManager layoutManager = new LinearLayoutManager(requireContext());
        layoutManager.setReverseLayout(true);
        layoutManager.setStackFromEnd(true);
        binding.CarRecyclerView.setLayoutManager(layoutManager);

        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initRecyclerView();
    }

    private void initRecyclerView() {
        // You can add any additional setup for your RecyclerView here if needed.
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
