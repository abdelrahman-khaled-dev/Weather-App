package com.example.weather;

import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkCapabilities;
import android.widget.Toast;

import androidx.annotation.RequiresPermission;

public class NetworkReceiver extends BroadcastReceiver {

    @RequiresPermission(Manifest.permission.ACCESS_NETWORK_STATE)
    @Override
    public void onReceive(Context context, Intent intent) {
        ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkCapabilities networkCapabilities = manager.getNetworkCapabilities(manager.getActiveNetwork());
        boolean isConnected = networkCapabilities != null && networkCapabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET);
        if (!isConnected){
            Toast.makeText(context, "Network disconnected", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Network connected", Toast.LENGTH_SHORT).show();
        }
    }
}