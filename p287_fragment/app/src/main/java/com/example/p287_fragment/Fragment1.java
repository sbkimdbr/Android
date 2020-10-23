package com.example.p287_fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class Fragment1 extends Fragment {
    TextView textView;
    EditText editText;
    Button button;

public Fragment1(){}



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
    ViewGroup viewGroup =null;
    viewGroup =(ViewGroup)inflater.inflate(
            R.layout.fragment_1,container,false);
        textView = viewGroup.findViewById(R.id.textView);
        editText = viewGroup.findViewById(R.id.textView);
        textView = viewGroup.findViewById(R.id.textView);


    return inflater.inflate(R.layout.fragment_1,container,false);


    }
}
