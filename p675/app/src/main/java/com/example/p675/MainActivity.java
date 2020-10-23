package com.example.p675;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MainActivity extends AppCompatActivity {

    //상속안받고 지도 뿌리기
    SupportMapFragment supportMapFragment;
    GoogleMap gmap;

    TextView textView;
    LocationManager locationManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String [] permission = {
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION
        };                     //Permission 배열로 만든다

        ActivityCompat.requestPermissions(this, permission, 101);

        supportMapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        supportMapFragment.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap googleMap) {
                gmap = googleMap; //gmap이라고 선언한 곳에 googlemap넣어줌
                //gmap.setMyLocationEnabled(true);//지도에 나의 위치 표시
                //gmap.setMyLocationEnabled(true);
                if (checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_DENIED
                        || checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_DENIED
                ) {
                    return;
                }
                gmap.setMyLocationEnabled(true);
                LatLng latlng = new LatLng(37.452925, -126.884153); //경도와 위도를 먼저 잡아서 맨 처음에 이 위치로 지도 옮겨줌

                gmap.addMarker(
                  new MarkerOptions().position(latlng).title("newyork").snippet("hahaha")
                          //.icon BitmapDescriptorFactory(R.).mipmap.ic_launcher_round)
                );
                gmap.animateCamera(CameraUpdateFactory.newLatLngZoom(latlng,10));
            }
       }); // supportmapfragment에 지도를 뿌림 textView=findViewById(R.id.textView);

                textView = findViewById(R.id.textView);
//        String [] permission = {
//                Manifest.permission.ACCESS_FINE_LOCATION,
//                Manifest.permission.ACCESS_COARSE_LOCATION
//        };                     //Permission 배열로 만든다
//
//        ActivityCompat.requestPermissions(this,permission,101); // Permission 배열을 요청해서 물어
                if (checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_DENIED
                        || checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_DENIED
                ) {
                    return;
                    //Toast.makeText(this,"finish",Toast.LENGTH_LONG).show();
                    //finish();  //permission denied -> 어플리케이션 종료시킴
                }

                MyLocation myLocation = new MyLocation();


                locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
                locationManager.requestLocationUpdates(
                        LocationManager.GPS_PROVIDER,
                        1, // 몇초에 한번씩 새로운 정보를 받을 건지
                        0, //얼마나 움직여야 새로운 정보를 받을 건지
                        myLocation
                );

                //gmap.setMyLocationEnabled(true);

            }

            public void ck1(View v) {
                LatLng latlng = new LatLng(40.693148, -74.104557);
                gmap.addMarker(
                        new MarkerOptions().position(latlng).title("newyork").snippet("hahaha")
                        //.icon BitmapDescriptorFactory(R.).mipmap.ic_launcher_round)
                );
                gmap.animateCamera(CameraUpdateFactory.newLatLngZoom(latlng, 10));

            }

            public void ck2(View v) {
                LatLng latlng = new LatLng(40.693148, -74.104557);
                gmap.addMarker(
                        new MarkerOptions().position(latlng).title("newyork").snippet("hahaha")
                        //.icon BitmapDescriptorFactory(R.).mipmap.ic_launcher_round)
                );
                gmap.animateCamera(CameraUpdateFactory.newLatLngZoom(latlng, 10));


            }

            class MyLocation implements LocationListener {
                //요청 안해도 정보가 달라지면 알아서 받아옴

                @Override
                public void onLocationChanged(@NonNull Location location) {
                    double lat = location.getLatitude();
                    double lon = location.getLongitude();
                    textView.setText(lat + " " + lon);
                    LatLng latlng = new LatLng(lat, lon);
                    gmap.addMarker(
                            new MarkerOptions().position(latlng).title("myposition").snippet("position")
                    );

                    gmap.animateCamera(CameraUpdateFactory.newLatLngZoom(latlng, 10));
                }
            }

    @SuppressLint("MissingPermission")
    @Override
    protected void onResume() {
        super.onResume();
        if(gmap != null){

            gmap.setMyLocationEnabled(true);
        }
    }

    @SuppressLint("MissingPermission")
    @Override
    protected void onPause() {
        super.onPause();
        if(gmap != null){
            gmap.setMyLocationEnabled(false);
        }
            }

        }
