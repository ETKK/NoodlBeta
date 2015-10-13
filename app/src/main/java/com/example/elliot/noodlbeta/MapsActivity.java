package com.example.elliot.noodlbeta;

import android.location.Location;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends AppCompatActivity implements OnMapReadyCallback, GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {

    private GoogleMap mMap;
    GoogleApiClient user;
    Double userLat, userLong;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        buildGoogleApiClient();
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    protected synchronized void buildGoogleApiClient() {
        user = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        buildGoogleApiClient();

        mMap = googleMap;

        Location LastLocation = LocationServices.FusedLocationApi.getLastLocation(user);
        if (LastLocation != null) {
            userLat = (LastLocation.getLatitude());
            userLong = (LastLocation.getLongitude());
        }
        // Add a marker to your current location
        if (userLat != null && userLong != null) {
            LatLng current = new LatLng(userLat, userLong);
            mMap.addMarker(new MarkerOptions().position(current).title("Current Location"));
            mMap.moveCamera(CameraUpdateFactory.newLatLng(current));
        }
        else {
            userLat = 35.3025823;
            userLong = -120.6641805;
            LatLng alt = new LatLng(userLat, userLong);
            mMap.addMarker(new MarkerOptions().position(alt).title("Alternate Location"));
            mMap.moveCamera(CameraUpdateFactory.newLatLng(alt));
        }
    }

    @Override
    public void onConnected(Bundle connectionHint) {

    }
    //Changes
    @Override
    public void onConnectionSuspended(int i) {
        //Some changes
    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {

    }
}
