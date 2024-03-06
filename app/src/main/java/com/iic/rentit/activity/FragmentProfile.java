package com.iic.rentit.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.iic.rentit.R;
import com.iic.rentit.domain.UserDomain;

public class FragmentProfile extends Fragment {

    private DatabaseHelper databaseHelper;
    private String username;

    public FragmentProfile() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_profile, container, false);
        Button updateProfileButton = rootView.findViewById(R.id.UpdateUserProfileBut);

        // Retrieve data from SharedPreferences
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("UserProfile", Context.MODE_PRIVATE);
        username = sharedPreferences.getString("username", "");

        // Initialize DatabaseHelper
        databaseHelper = new DatabaseHelper(getActivity());

        // Find your TextViews
        TextView usernameTextView = rootView.findViewById(R.id.UserProfileDisUsername);
        TextView phoneNumberTextView = rootView.findViewById(R.id.UserProfileDisPhoneNo);
        TextView addressTextView = rootView.findViewById(R.id.UserProfileDisAddress);
        TextView nrcTextView = rootView.findViewById(R.id.UserProfileDisNRC);
        TextView licenseTextView = rootView.findViewById(R.id.UserProfileDisLicense);

        // Retrieve user details from the database
        UserDomain user = DatabaseHelper.getUserDetailsByUsername(getContext(), username);
        if (user != null) {
            // Set user details to TextViews
            usernameTextView.setText("Username: " + user.getUsername());
            phoneNumberTextView.setText("Phone Number: " + user.getPhoneNumber());
            addressTextView.setText("Address: " + user.getAddress());
            nrcTextView.setText("NRC Number: " + user.getNrcNumber());
            licenseTextView.setText("License Number: " + user.getLiscenNumber());
        }

        // Set onClickListener for the Update Profile button
        updateProfileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), User_profile.class);
                startActivity(intent);
            }
        });

        return rootView;
    }
}
