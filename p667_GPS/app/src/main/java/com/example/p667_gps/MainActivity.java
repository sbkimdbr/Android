package com.example.p667_gps;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    TextView textView;
    LocationManager locationManager; //permission이 ok되면 사용

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView=findViewById(R.id.textView);

        String [] permission = {
                Manifest.permission.ACCESS_FINE_LOCATION
        };                     //Permission 배열로 만든다

        ActivityCompat.requestPermissions(this,permission,101); // Permission 배열을 요청해서 물어
        if(checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_DENIED){
            Toast.makeText(this,"finish",Toast.LENGTH_LONG).show();
            finish();  //permission denied -> 어플리케이션 종료시킴
        }

        MyLocation myLocation = new MyLocation();

        locationManager = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
        locationManager.requestLocationUpdates(
                LocationManager.GPS_PROVIDER,
                1, // 몇초에 한번씩 새로운 정보를 받을 건지
                0, //얼마나 움직여야 새로운 정보를 받을 건지
                myLocation
        );
    }

    public void ck(View v){
        //클릭시 위치기반 서비스 출력함
        startMyLocation();

    }

    private void startMyLocation() {
     Location location = null;

     if(checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_DENIED){
            Toast.makeText(this,"finish",Toast.LENGTH_LONG).show();
            finish();  //permission denied -> 어플리케이션 종료시킴
        }


     location = locationManager.getLastKnownLocation(
             LocationManager.GPS_PROVIDER
     );


     if(location !=null){
            double lat = location.getLatitude();
            double lon = location.getLongitude();
            textView.setText(lat+""+lon);
        }


    }

    class MyLocation implements LocationListener{
        //요청 안해도 정보가 달라지면 알아서 받아옴

        @Override
        public void onLocationChanged(@NonNull Location location) {
            double lat = location.getLatitude();
            double lon = location.getLongitude();
            textView.setText(lat+" "+lon);
        }
    }
}


//AlertDialog.Builder builder = new AlertDialog.Builder(this);
//            builder.setTitle("USING GPS");
//            builder.setMessage("Sure to offer your location?");
//            builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
//                @Override
//                public void onClick(DialogInterface dialog, int which) {
//                    String message = "NOT USING GPS";
//                    textView.setText(message);
//                }
//            });
//            builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
//                @Override
//                public void onClick(DialogInterface dialog, int which) {
//                    String message = "USE GPS";
//                    textView.setText(message);
//                }
//            });

