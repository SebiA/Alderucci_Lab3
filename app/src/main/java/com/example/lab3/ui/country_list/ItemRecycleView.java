package com.example.lab3.ui.country_list;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lab3.model.Country;
import com.example.lab3.model.ViewHolder;

import java.util.ArrayList;

public class ItemRecycleView extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private int layout_id;
    private ArrayList<Country> countryList;

    public ItemRecycleView(int layout_id, ArrayList<Country> countryList) {
        this.layout_id = layout_id;
        this.countryList = countryList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(layout_id,parent,false);
        ViewHolder myViewHolder = new ViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        TextView name = ((ViewHolder) holder).name;
        TextView capital = ((ViewHolder) holder).capital;
        TextView region = ((ViewHolder) holder).region;
        TextView subRegion = ((ViewHolder) holder).subRegion;
        name.setText(countryList.get(position).name);
        capital.setText(countryList.get(position).capital);
        region.setText(countryList.get(position).region);
        subRegion.setText(countryList.get(position).subRegion);
    }

    @Override
    public int getItemCount() {
        return countryList.size();
    }
}
