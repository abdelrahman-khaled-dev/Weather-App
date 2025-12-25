package com.example.weather;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.weather.databinding.CityViewBinding;

public class CityViewHolder extends RecyclerView.ViewHolder {
     CityViewBinding binding;

    public CityViewHolder(@NonNull CityViewBinding binding) {
        super(binding.getRoot());
        this.binding = binding;
    }
}
