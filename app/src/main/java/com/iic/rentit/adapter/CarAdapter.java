// CarAdapter.java
package com.iic.rentit.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.iic.rentit.R;
import com.iic.rentit.domain.carDomain;

import java.util.ArrayList;

public class CarAdapter extends RecyclerView.Adapter<CarAdapter.viewholder> {

    private ArrayList<carDomain> items;
    private Context context;
    private onItemClickListener listener;

    public void setListener(onItemClickListener listener) {
        this.listener = listener;
    }

    public interface onItemClickListener {
        void onItemClick(carDomain car);
    }

    public CarAdapter(ArrayList<carDomain> items, Context context) {
        this.items = items;
        this.context = context;
    }

    @NonNull
    @Override
    public CarAdapter.viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflater = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_viewholder_list, parent, false);
        context = parent.getContext();
        return new viewholder(inflater);
    }

    @Override
    public void onBindViewHolder(@NonNull CarAdapter.viewholder holder, int position) {
        carDomain currentItem = items.get(position);

        holder.image.setImageBitmap(currentItem.getImage());
        holder.title.setText(currentItem.getTitle());
        holder.description.setText(currentItem.getDescription());
        holder.price.setText("$" + currentItem.getPrice() + "/day");

        // Set background based on position
        switch (position) {
            case 0:
                holder.layout.setBackgroundResource(R.drawable.list_background_1);
                break;
            case 1:
                holder.layout.setBackgroundResource(R.drawable.list_background_2);
                break;
            case 2:
                holder.layout.setBackgroundResource(R.drawable.list_background_3);
                break;
            case 3:
                holder.layout.setBackgroundResource(R.drawable.list_background_4);
                break;
            case 4:
                holder.layout.setBackgroundResource(R.drawable.list_background_5);
                break;
        }

        // Set click listener for the item view
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.onItemClick(currentItem);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class viewholder extends RecyclerView.ViewHolder {
        TextView title, description;
        ImageView image;
        ConstraintLayout layout;
        TextView price;

        public viewholder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.car_namesTxt);
            price = itemView.findViewById(R.id.priceTxt);
            description = itemView.findViewById(R.id.descriptionTxt);
            layout = itemView.findViewById(R.id.main_layout);
            image = itemView.findViewById(R.id.pic);
        }
    }
}
