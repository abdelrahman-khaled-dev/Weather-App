package com.example.weather;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.weather.databinding.ActivityWeatherBinding;

import java.util.ArrayList;

public class WeatherActivity extends AppCompatActivity {

    private ActivityWeatherBinding binding;
    private WeatherDetailsAdapter detailsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivityWeatherBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        Intent weatherIntent = getIntent();
        int cityId = weatherIntent.getIntExtra("cityId", -1);

        ArrayList<City> cityArrayList = JsonClass.parseCitiesFromAssets(this);
        City selectedCity = null;
        for (City city : cityArrayList) {
            if (city.getId() == cityId) {
                selectedCity = city;
                break;
            }
        }
        if (selectedCity != null) {
            binding.cityName.setText(String.valueOf(selectedCity.getName()));
            binding.tempDeg.setText(String.valueOf(selectedCity.getCurrentTemp().getTemp()));
            binding.weatherCondition.setText(String.valueOf(selectedCity.getWeather().get(0).getDescription()));
            binding.maxTemp.setText(String.valueOf(selectedCity.getForecast().get(0).getMaxTemp()));
            binding.minTemp.setText(String.valueOf(selectedCity.getForecast().get(0).getMinTemp()));
        }
        binding.cardsRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        detailsAdapter = new WeatherDetailsAdapter(selectedCity);
        binding.cardsRecyclerView.setAdapter(detailsAdapter);

        binding.backBtn.setOnClickListener(v -> {
            finish();
        });
    }

}