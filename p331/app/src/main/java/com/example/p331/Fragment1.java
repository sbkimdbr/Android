package com.example.p331;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;


public class Fragment1 extends Fragment {
Button button;
MainActivity m;



    public Fragment1(MainActivity m) {

         this.m=m;        // Required empty public constructor
    }


    @Override
    public View onCreateView(@Nullable LayoutInflater inflater, @Nullable ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ViewGroup viewGroup = null;
        viewGroup = (ViewGroup)inflater.inflate(
                R.layout.fragment_1, container,false);
        button = viewGroup.findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Toast.makeText(m,"aaa",Toast.LENGTH_LONG).show();
            }
        });

        return viewGroup;


    }
}
