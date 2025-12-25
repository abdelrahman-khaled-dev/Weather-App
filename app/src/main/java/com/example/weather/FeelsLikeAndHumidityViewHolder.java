package com.example.weather;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.weather.databinding.ItemFeelslikeAndHumidityCardBinding;

public class FeelsLikeAndHumidityViewHolder extends RecyclerView.ViewHolder {
    ItemFeelslikeAndHumidityCardBinding itemFeelslikeAndHumidityCardBinding;
    public FeelsLikeAndHumidityViewHolder(@NonNull ItemFeelslikeAndHumidityCardBinding itemFeelslikeAndHumidityCardBinding) {
        super(itemFeelslikeAndHumidityCardBinding.getRoot());
        this.itemFeelslikeAndHumidityCardBinding = itemFeelslikeAndHumidityCardBinding;
    }
}
