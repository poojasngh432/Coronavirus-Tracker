package com.example.coronavirustrackerapp;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.coronavirustrackerapp.adapter.CountryAdapter;
import com.example.coronavirustrackerapp.model.Countries;

import java.util.List;

public class CountriesFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private List<Countries> myDataFromActivity;
    private RecyclerView recyclerView = null;
    private CountryAdapter countryAdapter;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public CountriesFragment() {
        // Required empty public constructor
    }

    public static CountriesFragment newInstance(String param1, String param2) {
        Log.d("Hi","14");
        CountriesFragment fragment = new CountriesFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        Log.d("Hi","12");
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
        MainActivity activity = (MainActivity) getActivity();
        myDataFromActivity = activity.setCountriesData();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_countries, container, false);

        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        Log.d("Hi","17");
        countryAdapter = null;
        countryAdapter = new CountryAdapter(myDataFromActivity, R.layout.cardview_layout, getActivity());
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(countryAdapter);
        Log.d("Hi","11");
        countryAdapter.notifyDataSetChanged();
        Toast.makeText(getActivity(), "Success", Toast.LENGTH_SHORT).show();

        return view;
    }
}
