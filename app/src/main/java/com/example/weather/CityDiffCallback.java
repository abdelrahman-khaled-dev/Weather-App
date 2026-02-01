package com.example.weather;

import androidx.recyclerview.widget.DiffUtil;

import java.util.ArrayList;

public class CityDiffCallback extends DiffUtil.Callback {

    private final ArrayList<City> oldlist;
    private final ArrayList<City> newlist;

    public CityDiffCallback(ArrayList<City> oldlist, ArrayList<City> newlist) {
        this.oldlist = oldlist;
        this.newlist = newlist;
    }

    @Override
    public int getOldListSize() {
        return oldlist.size();
    }

    @Override
    public int getNewListSize() {
        return newlist.size();
    }

    @Override
    public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
        return oldlist.get(oldItemPosition).getId() ==newlist.get(newItemPosition).getId();
    }

    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        City oldCity = oldlist.get(oldItemPosition);
        City newCity = newlist.get(newItemPosition);
        return oldCity.getName().equals(newCity.getName());
    }
}
