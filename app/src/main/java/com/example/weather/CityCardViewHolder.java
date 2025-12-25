package com.example.weather;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.weather.databinding.CityViewBinding;
import com.example.weather.databinding.ItemSelectedCityBinding;

public class CityCardViewHolder extends RecyclerView.ViewHolder {

    ItemSelectedCityBinding binding;

    public CityCardViewHolder(@NonNull ItemSelectedCityBinding binding) {
        super(binding.getRoot());
        this.binding = binding;
    }
}
