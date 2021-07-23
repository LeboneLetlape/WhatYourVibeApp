package za.co.whatyourvibe.PresentationLayer.Activities.EventActivities.EventLocationMVP;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.libraries.places.api.Places;
import com.google.android.libraries.places.api.model.Place;
import com.google.android.libraries.places.api.net.PlacesClient;
import com.google.android.libraries.places.widget.Autocomplete;
import com.google.android.libraries.places.widget.AutocompleteSupportFragment;
import com.google.android.libraries.places.widget.listener.PlaceSelectionListener;
import com.google.android.libraries.places.widget.model.AutocompleteActivityMode;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import za.co.whatyourvibe.LogicLayer.Models.ApplicationModel;
import za.co.whatyourvibe.R;
import za.co.whatyourvibe.databinding.ActivityEventLocationBinding;

public class EventLocationActivity extends FragmentActivity implements OnMapReadyCallback, GoogleMap.OnMapClickListener, View.OnClickListener, LocationListener {

    private GoogleMap mMap;
    private Geocoder geocoder;
    private List<Address> addresses;
    ActivityEventLocationBinding activityEventLocationBinding;
    String _StringAddress;
    Double _DoubleLat, _DoubleLong;
    boolean _IsFirstLoad,_NewLocationOnClick;


    protected LocationManager locationManager;
    protected LocationListener locationListener;
    protected Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityEventLocationBinding = DataBindingUtil.setContentView(this, R.layout.activity_event_location);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        geocoder = new Geocoder(this, Locale.getDefault());
        mapFragment.getMapAsync(this);
        if (!Places.isInitialized()) {
            Places.initialize(getApplicationContext(), getString("AIzaSyBMBGHH-0oJaKQ-4epk-3kU9g3LR1d_190"), Locale.US);
        }

        _IsFirstLoad = true;
        activityEventLocationBinding.mButtonDone.setOnClickListener(this);
        activityEventLocationBinding.mButtonMyLocation.setOnClickListener(this);
        activityEventLocationBinding.mEditTextSearch.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                List<Place.Field> fields = Arrays.asList(Place.Field.ID, Place.Field.NAME, Place.Field.ADDRESS, Place.Field.LAT_LNG);
                // Start the autocomplete intent.
                Intent intent = new Autocomplete.IntentBuilder(
                        AutocompleteActivityMode.FULLSCREEN, fields).setCountry("NG") //NIGERIA
                        .build(view.getContext());
                startActivityForResult(intent, 1);
            }
        });



        PlacesClient placesClient = Places.createClient(this);


        //txtLat = (TextView) findViewById(R.id.textview1);

        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, new android.location.LocationListener() {
            @Override
            public void onLocationChanged(@NonNull Location location) {
                if(!_NewLocationOnClick)
                    GetLocation(new LatLng(location.getLatitude(),location.getLongitude()));
            }
        });
    }

    private String getString(String s) {
        return s.toString();
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
    }

    @Override
    public void onMapClick(LatLng latLng) {

        _NewLocationOnClick = true;
        GetLocation(latLng);
    }


    private void GetLocation(LatLng latLng)
    {
        try {
            addresses = geocoder.getFromLocation(latLng.latitude,latLng.longitude,1);

            if(addresses.size() > 0)
            {
                _StringAddress = addresses.get(0).getAddressLine(0);
                _DoubleLat = latLng.latitude;
                _DoubleLong = latLng.longitude;

                activityEventLocationBinding.mTextViewAddressLine1.setText(_StringAddress);
                activityEventLocationBinding.mTextViewLat.setText("Lat:" +_DoubleLat.toString());
                activityEventLocationBinding.mTextViewLong.setText("Lng:" +_DoubleLong.toString());

                mMap.clear();
                mMap.addMarker(new MarkerOptions().position(latLng).title("Event Location"));

                if(_IsFirstLoad)
                {
                    mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng,14));
                    _IsFirstLoad = false;
                }

            }

        } catch (Exception e) {
            Toast.makeText(this, e.toString(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onClick(View view) {

        if(view.getId() == activityEventLocationBinding.mButtonDone.getId())
        {
            try {
                if (!_StringAddress.isEmpty())
                {

                    ApplicationModel.event.setLocation(_StringAddress);
                    ApplicationModel.event.setLat(_DoubleLat);
                    ApplicationModel.event.setLng(_DoubleLong);

                    ApplicationModel.getEventValidation().IsLocationValid = true;
                    finish();
                }
                else 
                {
                    Toast.makeText(this, "Your Address is invalid", Toast.LENGTH_SHORT).show();
                }
            } catch (Exception e) {
                Toast.makeText(this, e.toString(), Toast.LENGTH_SHORT).show();
            }
        }


        if(view.getId() == activityEventLocationBinding.mButtonMyLocation.getId())
        {
            _IsFirstLoad = true;_NewLocationOnClick = false;
        }

    }

    @Override
    public void onLocationChanged(Location location) {

    }
}