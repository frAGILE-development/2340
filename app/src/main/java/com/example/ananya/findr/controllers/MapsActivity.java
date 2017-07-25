package com.example.ananya.findr.controllers;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.GoogleMap.OnInfoWindowClickListener;

import java.util.ArrayList;
import java.util.List;

import com.example.ananya.findr.R;

import model.maps.Location;
import model.maps.Report;
import model.maps.ModelFacade;
import model.*;


/**
 * Controller for the maps functionality
 */
public class MapsActivity extends FragmentActivity implements OnMapReadyCallback, GoogleMap.OnMarkerClickListener,
        GoogleMap.OnMapClickListener {

    private GoogleMap mMap;
    private ModelFacade mFacade;
    private Marker mSelectedMarker;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        mFacade = ModelFacade.getInstance();
        Model model = Model.getInstance();

//        for (Marker f : model.getMarkers()) {
//                if (f != null) {
//                    // Creating a marker
//                    MarkerOptions markerOptions = new MarkerOptions();
//                    // Setting the position for the marker
//                    markerOptions.position(f.getPosition());
//                    // Clears the previously touched position
//                    //mMap.clear();
//                    mFacade.addReport(f.getName(), f.getDescription(), marker.getPosition());
//                    // Setting the title for the marker.
//                    // This will be displayed on taping the marker
//                    markerOptions.title(mFacade.getLastReport().getName());
//                    markerOptions.snippet(mFacade.getLastReport().getName());
//
//                    // Animating to the touched position
//                    mMap.animateCamera(CameraUpdateFactory.newLatLng(marker.getPosition()));
//                    // Placing a marker on the touched position
//                    mMap.addMarker(markerOptions);
//                }
//            }
//        }

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
        // Setting a click event handler for the map
        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {

            @Override
            public void onMapClick(LatLng latLng) {

                // Creating a marker
                MarkerOptions markerOptions = new MarkerOptions();
                // Setting the position for the marker
                markerOptions.position(latLng);
                // Clears the previously touched position
                //mMap.clear();
                mFacade.addReport(new Location(latLng.latitude, latLng.longitude));
                // Setting the title for the marker.
                // This will be displayed on taping the marker
                markerOptions.title(mFacade.getLastReport().getName());
                markerOptions.snippet(mFacade.getLastReport().getDescription());

                // Animating to the touched position
                mMap.animateCamera(CameraUpdateFactory.newLatLng(latLng));
                // Placing a marker on the touched position
                mMap.addMarker(markerOptions);
            }
        });

        mMap.setOnMarkerClickListener(this);

        mMap.setOnInfoWindowClickListener(new OnInfoWindowClickListener() {

            @Override
            public void onInfoWindowClick(Marker marker) {
                LatLng position = marker.getPosition();
                Model model = Model.getInstance();
                model.setCurrentLocation(position);
                Intent intent = new Intent(MapsActivity.this, AddItemFromMap.class);
                startActivity(intent);
            }
        });


        List<Report> reportList = mFacade.getReports();
        for (Report r : reportList) {
            LatLng loc = new LatLng(r.getLatitude(), r.getLongitude());
            mMap.addMarker(new MarkerOptions().position(loc).title(r.getName()).snippet(r.getDescription()));
            mMap.moveCamera(CameraUpdateFactory.newLatLng(loc));
        }

        mMap.setOnMarkerClickListener(this);

    }

    class CustomInfoWindowAdapter implements GoogleMap.InfoWindowAdapter {

        private final View myContentsView;

        @SuppressLint("InflateParams")
        CustomInfoWindowAdapter() {
            myContentsView = getLayoutInflater().inflate(R.layout.custom_info_contents, null);
        }

        @Override
        public View getInfoContents(Marker marker) {
            Model model = Model.getInstance();
            TextView tvTitle = ((TextView) myContentsView.findViewById(R.id.title));
            tvTitle.setText(model.getCurrentItem().getName());
            TextView tvSnippet = ((TextView) myContentsView.findViewById(R.id.snippet));
            tvSnippet.setText(model.getCurrentItem().getDescription());

            return myContentsView;
        }


        @Override
        public View getInfoWindow(Marker marker) {
            return null;
        }

    }

    @Override
    public void onMapClick(final LatLng point) {
        // Any showing info window closes when the map is clicked.
        // Clear the currently selected marker.
        mSelectedMarker = null;
    }

    @Override
    public boolean onMarkerClick(final Marker marker) {
        // The user has re-tapped on the marker which was already showing an info window.
        if (marker.equals(mSelectedMarker)) {
            // The showing info window has already been closed - that's the first thing to happen
            // when any marker is clicked.
            // Return true to indicate we have consumed the event and that we do not want the
            // the default behavior to occur (which is for the camera to move such that the
            // marker is centered and for the marker's info window to open, if it has one).
            mSelectedMarker = null;
            return true;
        }

        mSelectedMarker = marker;

        // Return false to indicate that we have not consumed the event and that we wish
        // for the default behavior to occur.
        return false;
    }

}
