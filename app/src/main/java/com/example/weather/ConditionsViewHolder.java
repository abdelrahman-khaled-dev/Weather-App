package com.example.weather;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.weather.databinding.ItemHourlyConditionBinding;

public class ConditionsViewHolder extends RecyclerView.ViewHolder {

    ItemHourlyConditionBinding itemHourlyConditionBinding;
    public ConditionsViewHolder(@NonNull ItemHourlyConditionBinding itemHourlyConditionBinding) {
        super(itemHourlyConditionBinding.getRoot());
        this.itemHourlyConditionBinding = itemHourlyConditionBinding;
    }

}
