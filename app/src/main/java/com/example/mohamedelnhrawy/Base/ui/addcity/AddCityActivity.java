package com.example.mohamedelnhrawy.Base.ui.addcity;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Location;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.mohamedelnhrawy.Base.R;
import com.example.mohamedelnhrawy.Base.ui.base.BaseActivity;
import com.example.mohamedelnhrawy.Base.ui.main.MainActivity;
import com.example.mohamedelnhrawy.Base.util.MyLocation;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.patloew.rxlocation.RxLocation;

import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import butterknife.ButterKnife;

public class AddCityActivity extends BaseActivity implements OnMapReadyCallback, AddCityMvpView, GoogleMap.OnMapClickListener {

    @Inject
    AddCityPresenter<AddCityMvpView> mPresenter;

    RxLocation rxLocation;

    private GoogleMap mMap;

    public static Intent getStartIntent(Context context) {
        Intent intent = new Intent(context, AddCityActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_city);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        setUp();
    }

    @Override
    protected void setUp() {
        getActivityComponent().inject(this);
        setUnBinder(ButterKnife.bind(this));
        mPresenter.onAttach(AddCityActivity.this);


    }

    @Override
    protected void onResume() {
        super.onResume();
        checkPlayServicesAvailable();

    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.setOnMapClickListener(this);

        // Add a marker in Sydney and move the camera

    }


    @Override
    public void onLocationUpdate(Location location) {
         Log.e("location",""+location.getLatitude());

    }

    @Override
    public void onAddressUpdate(Address address) {
        if (address != null){
            mPresenter.stopUpdateLocation();
            drawCurrentLocation(address);
        }
    }

    @Override
    public void onLocationSettingsUnsuccessful() {
        Log.e("permission","permission");
    }

    @Override
    public void drawCurrentLocation(Address address) {
        LatLng current_Locatin = new LatLng(address.getLatitude(), address.getLongitude());
        mMap.addMarker(new MarkerOptions().position(current_Locatin).title(address.getFeatureName()));
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(current_Locatin, 14.0f));

    }

    @Override
    public void drawClickedLocation(Location location) {
        LatLng current_Locatin = new LatLng(location.getLatitude(), location.getLongitude());
        mMap.addMarker(new MarkerOptions().position(current_Locatin).title(getResources().getString(R.string.current_location)));
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(current_Locatin, 14.0f));

    }

    @Override
    public void clearMap() {
        mMap.clear();
    }




    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

    }

    @Override
    public void onMapClick(LatLng latLng) {

        Location location = new Location("");
        location.setLatitude(latLng.latitude);
        location.setLongitude(latLng.longitude);
        clearMap();
        drawClickedLocation(location);
        // lovation model
        Log.e("latlng",""+location.getLatitude()+""+location.getLongitude());
        com.example.mohamedelnhrawy.Base.data.db.model.Location model=new com.example.mohamedelnhrawy.Base.data.db.model.Location();
        model.setLocation_latitude(location.getLatitude());
        model.setLocation_longitude(location.getLongitude());
        mPresenter.addLocationToDB(model);

    }

    @Override
    public void showAddedSnackBar(int message) {
        showSnackBar(message);

    }
}
