package com.example.p287_fragment;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class Fragment2 extends Fragment {


    public Fragment2() {
    }

    MapView gmap;
    TextView textView2;
    SupportMapFragment supportMapFragment;
    LinearLayout container;



    @Nullable
    @Override
    //fragment실행되는 부
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_2, container, false);
        gmap = (MapView) v.findViewById(R.id.map);
        gmap.onCreate(savedInstanceState);
        gmap.onResume();

        MapsInitializer.initialize(getActivity().getApplicationContext());//초기화 해줌

        gmap.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap googleMap) {
                LatLng latlng = new LatLng(40.693148, -74.104557);
                if (ActivityCompat.checkSelfPermission(getContext(),
                        Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                        && ActivityCompat.checkSelfPermission(getContext(),
                        Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    return;
                }
                googleMap.setMyLocationEnabled(true);
                //latlng = new LatLng(40.693148, -74.104557);

                googleMap.addMarker(
                        new MarkerOptions().position(latlng).title("newyork").snippet("hahaha")
                );
                googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latlng,10));
            }
        });

      return v;
    }
}











//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//
//
//        String [] permission = {
//                Manifest.permission.ACCESS_FINE_LOCATION,
//                Manifest.permission.ACCESS_COARSE_LOCATION
//
//        };                     //Permission 배열로 만든다setContentView(R.layout.activity_main);






