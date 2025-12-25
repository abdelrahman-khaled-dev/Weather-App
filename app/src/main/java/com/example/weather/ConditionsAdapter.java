package com.example.weather;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.weather.databinding.ItemHourlyConditionBinding;

import java.util.ArrayList;

public class ConditionsAdapter extends RecyclerView.Adapter<ConditionsViewHolder> {

    private ArrayList<HourlyConditions> conditionsArrayList;
    private Context context;

    public ConditionsAdapter(ArrayList<HourlyConditions> conditionsArrayList, Context context) {
        this.conditionsArrayList = conditionsArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public ConditionsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemHourlyConditionBinding itemHourlyConditionBinding = ItemHourlyConditionBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);
        return new ConditionsViewHolder(itemHourlyConditionBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull ConditionsViewHolder holder, int position) {
        HourlyConditions hourlyConditions = conditionsArrayList.get(position);
        holder.itemHourlyConditionBinding.conditionTime.setText(hourlyConditions.getHour());
        holder.itemHourlyConditionBinding.conditionImage.setImageResource(R.drawable.sun);
        holder.itemHourlyConditionBinding.conditionTemp.setText(hourlyConditions.getTemp());
    }

    @Override
    public int getItemCount() {
        return conditionsArrayList.size();
    }

    public void updateList(ArrayList<HourlyConditions> newList) {
        conditionsArrayList.clear();
        conditionsArrayList.addAll(newList);
        notifyDataSetChanged();
    }

}
