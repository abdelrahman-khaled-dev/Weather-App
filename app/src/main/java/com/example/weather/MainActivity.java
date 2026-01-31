package com.example.weather;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;


import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.weather.databinding.ActivityMainBinding;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private CityAdapter cityAdapter;
    private CityCardAdapter cityCardAdapter;
    private CityViewModel cityViewModel;
    private LocationManager locationManager;
    private MyLocationListener locationListener;
    private String geoUri;
    private double latitude;
    private double longitude;
    public static final int locationRequestCode = 1;
    private NetworkReceiver networkReceiver;

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

        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        locationListener = new MyLocationListener();

        binding.mapsFab.setOnClickListener(v -> {
            checkLocationPermission();
        });

        cityViewModel = new ViewModelProvider(this).get(CityViewModel.class);

        cityCardAdapter = new CityCardAdapter(new ArrayList<>());
        binding.selectedCitiesRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        binding.selectedCitiesRecyclerView.setAdapter(cityCardAdapter);

        cityViewModel.getSelectedCities().observe(this, cities -> {
            if (cities != null) {
                cityCardAdapter.updateWithNewCities(cities);
            }
        });

        ArrayList<City> citiesList = JsonClass.parseCitiesFromAssets(this);
        cityViewModel.setCityList(citiesList);

        binding.searchCityRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        binding.searchCityRecyclerView.setVisibility(View.GONE);

        ArrayList<City> searchList = cityViewModel.getCitiesList().getValue();
        if (searchList == null) searchList = new ArrayList<>();

        cityAdapter = new CityAdapter(searchList, city -> {
            cityViewModel.addSelectedCity(city);
            binding.searchCityRecyclerView.setVisibility(View.GONE);
            binding.searchView.setQuery("", false);
            binding.searchView.setIconifiedByDefault(false);
            binding.searchView.clearFocus();
        });

        binding.searchCityRecyclerView.setAdapter(cityAdapter);

        EditText searchEditText = binding.searchView.findViewById(androidx.appcompat.R.id.search_src_text);
        searchEditText.setHintTextColor(Color.WHITE);
        searchEditText.setTextColor(Color.WHITE);

        ImageView searchImageView = binding.searchView.findViewById(androidx.appcompat.R.id.search_mag_icon);
        searchImageView.setColorFilter(Color.WHITE);

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

    @Override
    protected void onStart() {
        super.onStart();
        networkReceiver = new NetworkReceiver();
        IntentFilter filter = new IntentFilter();
        filter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
        registerReceiver(networkReceiver, filter);
    }

    @Override
    protected void onStop() {
        super.onStop();
        unregisterReceiver(networkReceiver);
    }


    private boolean isGpsEnabled() {
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
    }

    private void openGpsSettings() {
        Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
        startActivity(intent);
    }

    private void requestLocationNow() {
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        if (!isGpsEnabled()) {
            Toast.makeText(this, "Please enable location services", Toast.LENGTH_LONG).show();
            openGpsSettings();
            return;
        }
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 5000, 1, locationListener);
    }

    private void checkLocationPermission() {
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED || ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION, android.Manifest.permission.ACCESS_COARSE_LOCATION}, locationRequestCode);
        } else {
            requestLocationNow();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == locationRequestCode) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                requestLocationNow();
            } else {
                Toast.makeText(this, "Location permission denied", Toast.LENGTH_SHORT).show();
            }
        }
    }

    class MyLocationListener implements LocationListener {
        @Override
        public void onLocationChanged(@NonNull Location location) {
            latitude = location.getLatitude();
            longitude = location.getLongitude();
            geoUri = "geo:" + latitude + "," + longitude + "?q=" + latitude + "," + longitude;
            Intent mapsIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(geoUri));
            mapsIntent.setPackage("com.google.android.apps.maps");
            if (mapsIntent.resolveActivity(getPackageManager()) != null) {
                try {
                    startActivity(mapsIntent);
                } catch (Exception e) {
                    Toast.makeText(MainActivity.this, "Cannot open Google Maps", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(MainActivity.this, "Google Maps not installed", Toast.LENGTH_SHORT).show();
            }
            locationManager.removeUpdates(locationListener);
        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {

        }

        @Override
        public void onProviderEnabled(@NonNull String provider) {
            Toast.makeText(MainActivity.this, "GPS Enabled", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onProviderDisabled(@NonNull String provider) {
            Toast.makeText(MainActivity.this, "GPS Disabled", Toast.LENGTH_SHORT).show();
        }

    }
}
