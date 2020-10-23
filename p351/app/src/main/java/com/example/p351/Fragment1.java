package com.example.p351;

import android.media.Image;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;


public class Fragment1 extends Fragment {

    ImageView img;
    Button button4;

    public Fragment1() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup viewGroup = null;
        viewGroup = (ViewGroup)inflater.inflate(R.layout.fragment_1, container,
                false);
        img=viewGroup.findViewById(R.id.img);
        button4 = viewGroup.findViewById(R.id.button4);

        //return inflater.inflate(R.layout.fragment_1, container, false);
    return viewGroup;
    }

    public void setImg(int setId) {
        img.setImageResource(setId);
    }
}