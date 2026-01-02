package com.example.weather;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;

public class CityViewModel extends ViewModel {
    private final MutableLiveData<ArrayList<City>> citiesList = new MutableLiveData<>();
    private final MutableLiveData<ArrayList<City>> selectedCities = new MutableLiveData<>(new ArrayList<>());

    public LiveData<ArrayList<City>> getCitiesList() {
        return citiesList;
    }

    public void setCityList(ArrayList<City> list) {
        citiesList.setValue(list);
    }
    public LiveData<ArrayList<City>> getSelectedCities() {
        return selectedCities;
    }

    public void addSelectedCity(City city) {
        ArrayList<City> current = selectedCities.getValue();
        if (current == null) current = new ArrayList<>();

        if (!current.contains(city)) {
            current.add(city);
            selectedCities.setValue(current);
        }
    }
}
