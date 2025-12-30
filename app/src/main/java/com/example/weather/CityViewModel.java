package com.example.weather;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;

public class CityViewModel extends ViewModel {
    private MutableLiveData<City> selectedCity = new MutableLiveData<>();
    private MutableLiveData<ArrayList<City>> citiesList = new MutableLiveData<>();

    public LiveData<City> getSelectedCity() {
        return selectedCity;
    }

    public LiveData<ArrayList<City>> getCitiesList() {
        return citiesList;
    }

    public void setSelectedCity(City city) {
        selectedCity.setValue(city);
    }

    public void setCityList(ArrayList<City> list) {
        citiesList.setValue(list);
    }
}
