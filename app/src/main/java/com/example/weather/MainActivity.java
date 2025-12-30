package com.example.weather;

import static android.view.View.LAYOUT_DIRECTION_RTL;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.ImageView;


import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.weather.databinding.ActivityMainBinding;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private ArrayList<City> citiesList;
    private ArrayList<City> selectedCitiesList;
    private CityAdapter cityAdapter;
    private CityCardAdapter cityCardAdapter;
    private CityViewModel cityViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            Window window = getWindow();
            window.setStatusBarColor(ContextCompat.getColor(this, R.color.black));
            window.getDecorView().setSystemUiVisibility(0);
        }


        binding.mapsFab.setOnClickListener(v -> {
            Intent mapsIntent = new Intent(Intent.ACTION_VIEW);
            mapsIntent.setData(Uri.parse("https://www.google.com/maps/place/Egypt"));
            startActivity(mapsIntent);
        });

        cityViewModel = new ViewModelProvider(this).get(CityViewModel.class);

        cityViewModel.getSelectedCity().observe(this,city -> {
            if (city == null) return;
            cityCardAdapter.addCity(city);
        });

        cityViewModel.getCitiesList().observe(this, cities -> {
            cityAdapter.submitList(cities);
        });

        binding.selectedCitiesRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        selectedCitiesList = new ArrayList<>();
        cityCardAdapter = new CityCardAdapter(selectedCitiesList);
        binding.selectedCitiesRecyclerView.setAdapter(cityCardAdapter);

        citiesList = JsonClass.parseCitiesFromAssets(this);
        cityViewModel.setCityList(citiesList);

        binding.searchCityRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        binding.searchCityRecyclerView.setVisibility(View.GONE);

        cityAdapter = new CityAdapter(cityViewModel.getCitiesList().getValue(), city -> {
            cityCardAdapter.addCity(city);
            binding.searchCityRecyclerView.setVisibility(View.GONE);
            binding.searchView.setQuery("", false);
            binding.searchView.setIconifiedByDefault(false);
            binding.searchView.clearFocus();
        });

        EditText searchEditText = binding.searchView.findViewById(androidx.appcompat.R.id.search_src_text);
        searchEditText.setHintTextColor(Color.WHITE);
        searchEditText.setTextColor(Color.WHITE);

        ImageView searchImageView = binding.searchView.findViewById(androidx.appcompat.R.id.search_mag_icon);
        searchImageView.setColorFilter(Color.WHITE);

        binding.searchCityRecyclerView.setAdapter(cityAdapter);

        binding.searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                cityAdapter.getFilter().filter(query);
                binding.searchCityRecyclerView.setVisibility(View.VISIBLE);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                cityAdapter.getFilter().filter(newText);
                if (newText.isEmpty()) {
                    binding.searchCityRecyclerView.setVisibility(View.GONE);
                } else {
                    binding.searchCityRecyclerView.setVisibility(View.VISIBLE);
                }
                return false;
            }
        });
        setSupportActionBar(binding.toolBar);

    }
}
