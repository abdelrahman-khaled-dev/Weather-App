package com.example.weather;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.weather.databinding.ItemWindAndPrecipitationCardBinding;

public class WindAndPrecipitationViewHolder extends RecyclerView.ViewHolder {

    ItemWindAndPrecipitationCardBinding itemWindAndPrecipitationCardBinding;

    public WindAndPrecipitationViewHolder(@NonNull ItemWindAndPrecipitationCardBinding itemWindAndPrecipitationCardBinding) {
        super(itemWindAndPrecipitationCardBinding.getRoot());
        this.itemWindAndPrecipitationCardBinding = itemWindAndPrecipitationCardBinding;
    }
}
