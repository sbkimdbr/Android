package com.example.framelayout;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    ImageView imageView, imageView2, imageView3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageView = findViewById(R.id.imageView);
        imageView2=findViewById(R.id.imageView2);

    }
    public void clickB(View v){
        if(v.getId() == R.id.button){
            imageView.setVisibility(View.VISIBLE);
            imageView2.setVisibility(View.INVISIBLE);
        }else if(v.getId()==R.id.button2){
            imageView.setVisibility(View.INVISIBLE);
            imageView2.setVisibility(View.VISIBLE);

        }
    }
}
//    public void onButton10(View v){
//        Toast.makeText(this,"button click", Toast.LENGTH_LONG).show();
//    }
//}

//        imageView= findViewById(R.id.imageView);
//        imageView2 = findViewById(R.id.imageView);
//        imageView3 = findViewById(R.id.imageView);
//    }
//    public void click(View v){
//        if(v.getId() == R.id.button){
//            imageView = setVisibility(View.VISIBLE);
//            imageView2 = setVisiblitity(View.INVISIBLE);
//            imageView3 = setVisiblitity(View.INVISIBLE);
//        }else if(v.getId() == R.id.button2){
//            imageView = setVisibility(View.INVISIBLE);
//            imageView2 = setVisiblitity(View.VISIBLE);
//            imageView3 = setVisiblitity(View.VISIBLE);
//
//        }else if(v.getId() == R.id.button3){
//            imageView = setVisibility(View.INVISIBLE);
//            imageView2 = setVisiblitity(View.VISIBLE);
//            imageView3 = setVisiblitity(View.VISIBLE);
//        }
//    }
