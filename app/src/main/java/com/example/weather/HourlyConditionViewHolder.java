package com.example.weather;

import android.content.Context;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.weather.databinding.ItemHourlyConditionBinding;
import com.example.weather.databinding.ItemHourlyConditionCardBinding;

import java.util.ArrayList;

public class HourlyConditionViewHolder extends RecyclerView.ViewHolder {
    ItemHourlyConditionCardBinding itemHourlyConditionCardBinding;
    private ConditionsAdapter conditionsAdapter;

    public HourlyConditionViewHolder(@NonNull ItemHourlyConditionCardBinding itemHourlyConditionCardBinding) {
        super(itemHourlyConditionCardBinding.getRoot());
        this.itemHourlyConditionCardBinding = itemHourlyConditionCardBinding;

        itemHourlyConditionCardBinding.hourlyConditionRecyclerView.setLayoutManager(new LinearLayoutManager(itemHourlyConditionCardBinding.getRoot().getContext(),LinearLayoutManager.HORIZONTAL,false));
        itemHourlyConditionCardBinding.hourlyConditionRecyclerView.setNestedScrollingEnabled(false);
    }

    public void bind(ArrayList<HourlyConditions> hourlyConditions, Context context){
        if (conditionsAdapter == null){
            conditionsAdapter = new ConditionsAdapter(hourlyConditions,context);
            itemHourlyConditionCardBinding.hourlyConditionRecyclerView.setAdapter(conditionsAdapter);
        }else {
            conditionsAdapter.updateList(hourlyConditions);
        }
    }
}
