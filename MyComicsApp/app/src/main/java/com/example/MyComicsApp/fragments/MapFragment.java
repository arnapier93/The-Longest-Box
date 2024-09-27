package com.example.MyComicsApp.fragments;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.MyComicsApp.R;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnMyLocationButtonClickListener;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.libraries.places.api.Places;
import com.google.android.libraries.places.api.model.CircularBounds;
import com.google.android.libraries.places.api.model.Place;
import com.google.android.libraries.places.api.net.PlacesClient;
import com.google.android.libraries.places.api.net.SearchByTextRequest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class MapFragment extends Fragment {

    private FusedLocationProviderClient fusedLocationClient;
    public LatLng myLocation;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return null;
        }

        LocationManager locationManager = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);
        Criteria criteria = new Criteria();
        criteria.setAccuracy(Criteria.ACCURACY_FINE);
        criteria.setCostAllowed(false);

        String providerName = locationManager.getBestProvider(criteria, true);
        Location lastKnownLocation = locationManager.getLastKnownLocation(providerName);
        myLocation = new LatLng(lastKnownLocation.getLatitude(), lastKnownLocation.getLongitude());

        // Initialize view
        View view = inflater.inflate(R.layout.fragment_map, container, false);

        // Initialize map fragment
        SupportMapFragment supportMapFragment = (SupportMapFragment)
                getChildFragmentManager().findFragmentById(R.id.google_map);

        // Initialize the SDK
        Places.initializeWithNewPlacesApiEnabled(getActivity(), "AIzaSyDnvwL-cz38tOI6a1wuRw_Wa_LEjp00Nl8");

        List<Marker> markerList = new ArrayList<Marker>();

        // Async map
        supportMapFragment.getMapAsync(new OnMapReadyCallback() {
            private GoogleMap mMap;

            @Override
            public void onMapReady(GoogleMap googleMap) {

                mMap = googleMap;
                if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    //    ActivityCompat#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for ActivityCompat#requestPermissions for more details.
                    return;
                }
                mMap.setMyLocationEnabled(true);
                mMap.setOnMyLocationButtonClickListener(new OnMyLocationButtonClickListener() {
                    @Override
                    public boolean onMyLocationButtonClick() {
                        if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                            // TODO: Consider calling
                            //    ActivityCompat#requestPermissions
                            // here to request the missing permissions, and then overriding
                            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                            //                                          int[] grantResults)
                            // to handle the case where the user grants the permission. See the documentation
                            // for ActivityCompat#requestPermissions for more details.
                            return false;
                        }
                        Location lastKnownLocation = locationManager.getLastKnownLocation(providerName);
                        myLocation = new LatLng(lastKnownLocation.getLatitude(), lastKnownLocation.getLongitude());
                        dropPins(myLocation);
                        Toast.makeText(getActivity(), "you pressed the button!", Toast.LENGTH_SHORT).show();
                        return false;
                    }
                });
                mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
                    @Override
                    public boolean onMarkerClick(@NonNull Marker marker) {
                        //get more accurate directions and send to FAB and remove the mapToolBar
                        return false;
                    }
                });
                mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
                    @Override
                    public void onMapClick(@NonNull LatLng latLng) {
                        mMap.animateCamera(CameraUpdateFactory.newLatLng(latLng));
                        dropPins(latLng);
                        Toast.makeText(getActivity(), "you pressed the map", Toast.LENGTH_SHORT).show();
                    }
                });

                // Define latitude and longitude coordinates of the center of the search area.
                mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(myLocation, 10.0f));
                dropPins(myLocation);

            }

            public void dropPins(LatLng searchCenterPoint){
                mMap.clear();
                markerList.clear();

                // Specify the list of fields to return.
                final List<Place.Field> placeFields = Arrays.asList(Place.Field.ID, Place.Field.NAME, Place.Field.LAT_LNG);
                // Use the builder to create a SearchByTextRequest object.
                final SearchByTextRequest searchByTextRequest = SearchByTextRequest.builder("comic book store", placeFields)
                        .setLocationBias(CircularBounds.newInstance(searchCenterPoint, 40233.6)).build();

                // Call PlacesClient.searchByText() to perform the search.
                // Define a response handler to process the returned List of Place objects.
                PlacesClient placesClient = Places.createClient(getActivity());
                placesClient.searchByText(searchByTextRequest)
                        .addOnSuccessListener(response -> {
                            List<Place> places = response.getPlaces();
                            for(int i = 0; i < places.size(); i++){
                                Marker marker = mMap.addMarker(new MarkerOptions()
                                        .position(places.get(i).getLatLng())
                                        .title(places.get(i).getName())
                                        .snippet(places.get(i).getAddress()));
                                markerList.add(marker);
                            }
                        });
            }

        });
        // Return view
        return view;
    }


}