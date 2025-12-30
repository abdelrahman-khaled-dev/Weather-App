package com.example.weather;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.weather.databinding.CityViewBinding;

import java.util.ArrayList;
public class CityAdapter extends RecyclerView.Adapter<CityViewHolder> implements Filterable {

    private ArrayList<City> citiesList;
    private ArrayList<City> filteredCitiesList;
    private OnCityClickListener listener;

    public CityAdapter(ArrayList<City> citiesList, OnCityClickListener listener) {
        this.citiesList = citiesList;
        this.filteredCitiesList = new ArrayList<>(citiesList);
        this.listener = listener;
    }

    public void submitList (ArrayList<City> list){
        citiesList = list;
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public CityViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        CityViewBinding binding = CityViewBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new CityViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull CityViewHolder holder, int position) {
        City city = filteredCitiesList.get(position);
        holder.binding.cityName.setText(city.getName());
        holder.binding.getRoot().setOnClickListener(v -> {
            listener.onCityClick(city);
        });
    }

    @Override
    public int getItemCount() {
        return filteredCitiesList.size();
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                ArrayList<City> filtered = new ArrayList<>();
                String query = constraint.toString().toLowerCase().trim();

                if (query.isEmpty()){
                    filtered.addAll(citiesList);
                }else {
                    for (City city : citiesList){
                        if (city.getName().toLowerCase().contains(query)){
                            filtered.add(city);
                        }
                    }
                }
                FilterResults results = new FilterResults();
                results.values = filtered;
                return results;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                filteredCitiesList.clear();
                filteredCitiesList.addAll((ArrayList<City>) results.values);
                notifyDataSetChanged();
            }
        };
    }

    public interface OnCityClickListener {
        void onCityClick(City city);
    }
    }
