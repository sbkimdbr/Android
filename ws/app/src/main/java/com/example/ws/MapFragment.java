package com.example.ws;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapFragment extends AppCompatActivity {

    GoogleMap gmap2;
    TextView textView;
    LocationManager locationManager;
    SupportMapFragment supportMapFragment;
    BitmapDescriptor bitmapDescriptor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mapfragment);

        String [] permission = {
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION
        };                     //Permission 배열로 만든다

        ActivityCompat.requestPermissions(this, permission, 101);
        supportMapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        supportMapFragment.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap googleMap) {

                gmap2 = googleMap; //gmap이라고 선언한 곳에 googlemap넣어줌
                //gmap.setMyLocationEnabled(true);//지도에 나의 위치 표시
                //gmap.setMyLocationEnabled(true);
                if(checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_DENIED
                        || checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_DENIED
                ) {
                    return;
                }
//                gmap2.setMyLocationEnabled(true);
//                LatLng latlng = new LatLng(37.572533, 126.975764); //경도와 위도를 먼저 잡아서 맨 처음에 이 위치로 지도 옮겨줌

//                gmap2.addMarker(
//                        new MarkerOptions().position(latlng).title("Seoul").snippet("Enjoy show!")
//
//                );
//                gmap2.animateCamera(CameraUpdateFactory.newLatLngZoom(latlng,18));
            }
        });

        if (checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_DENIED
                || checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_DENIED
        ) {
            return;
            //Toast.makeText(this,"finish",Toast.LENGTH_LONG).show();
            //finish();  //permission denied -> 어플리케이션 종료시킴
        }

        MyLocation myLocation= new MyLocation();
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        locationManager.requestLocationUpdates(
                LocationManager.GPS_PROVIDER,
                1, // 몇초에 한번씩 새로운 정보를 받을 건지
                0, //얼마나 움직여야 새로운 정보를 받을 건지
                myLocation
        );

    }

    class MyLocation implements LocationListener{

        @Override
        public void onLocationChanged(@NonNull Location location) {
            double lat = location.getLatitude();
            double lon = location.getLongitude();
            LatLng latlng = new LatLng(lat, lon);

            latlng = new LatLng(37.572533, 126.975764);
            gmap2.addMarker(
                    new MarkerOptions().position(latlng).title("Musical").snippet("광화문로")
           );

            gmap2.animateCamera(CameraUpdateFactory.newLatLngZoom(latlng, 18));
        }
        }

    @SuppressLint("MissingPermission")
    @Override
    protected void onResume() {
        super.onResume();
        if(gmap2 != null){

            gmap2.setMyLocationEnabled(true);
        }
    }

    @SuppressLint("MissingPermission")
    @Override
    protected void onPause() {
        super.onPause();
        if(gmap2 != null){
            gmap2.setMyLocationEnabled(false);
        }
    }
    }


