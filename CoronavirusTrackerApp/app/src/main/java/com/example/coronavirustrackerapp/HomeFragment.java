package com.example.coronavirustrackerapp;

import android.os.Bundle;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class HomeFragment extends Fragment implements View.OnClickListener{

    ImageView img1, img2, img3, img4, img5, img6, img7, img8;


    public HomeFragment() {
        // Required empty public constructor
    }

    public static HomeFragment newInstance() {
        HomeFragment fragment = new HomeFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        img1 = (ImageView) view.findViewById(R.id.imageview1);
        img2 = (ImageView) view.findViewById(R.id.imageview2);
        img3 = (ImageView) view.findViewById(R.id.imageview3);
        img4 = (ImageView) view.findViewById(R.id.imageview4);
        img5 = (ImageView) view.findViewById(R.id.imageview5);
        img6 = (ImageView) view.findViewById(R.id.imageview6);
        img7 = (ImageView) view.findViewById(R.id.imageview7);
        img8 = (ImageView) view.findViewById(R.id.imageview8);

        return view;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.imageview1:
               // onBrandClick();
                break;

            case R.id.imageview2:

                break;

            case R.id.imageview3:

                break;
            case R.id.imageview4:

                break;
            case R.id.imageview5:

                break;
            case R.id.imageview6:

                break;
            case R.id.imageview7:

                break;
            case R.id.imageview8:

                break;
        }
    }
}
