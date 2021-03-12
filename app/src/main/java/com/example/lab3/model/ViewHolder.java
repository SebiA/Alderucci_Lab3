package com.example.lab3.model;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import com.example.lab3.R;

import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    private String TAG = "VIEW_HOLDER";
    private Bundle bundle;
    public TextView name;
    public TextView capital;
    public TextView region;
    public TextView subRegion;

    public ViewHolder(View itemView) {
        super(itemView);
        itemView.setOnClickListener(this);
        name = itemView.findViewById(R.id.text_name);
        capital = itemView.findViewById(R.id.text_capital);
        region = itemView.findViewById(R.id.text_region);
        subRegion = itemView.findViewById(R.id.text_subRegion);
    }

    @Override
    public void onClick(View view) {
        Log.i(TAG, "clicked" + view.toString());
        bundle = new Bundle();
        bundle.putInt("card", getAdapterPosition());
        Navigation.findNavController(itemView).navigate(R.id.action_navigation_notifications_to_displayFragment);
    }
}
