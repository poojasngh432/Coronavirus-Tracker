package com.myapp.coronavirusapp;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.myapp.coronavirusapp.model.Countries;
import com.myapp.coronavirusapp.model.Global;

import java.util.List;

public class GlobeFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    TextView TVglobalData1, TVglobalData2, TVglobalData3;
    private Global myDataFromActivity;
    TextView country;
    TextView cases;
    TextView casesToday;
    TextView deaths;
    TextView deathsToday;
    TextView recovered;
    private List<Countries> countriesList;

    private String mParam1;
    private String mParam2;

    public GlobeFragment() {
        // Required empty public constructor
    }

    public static GlobeFragment newInstance(String param1, String param2) {
        GlobeFragment fragment = new GlobeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        MainActivity activity = (MainActivity) getActivity();
        myDataFromActivity = activity.setGlobalData();

        countriesList = activity.setCountriesData();

        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_globe, container, false);

        TVglobalData1 = (TextView) view.findViewById(R.id.global_data1);
        TVglobalData2 = (TextView) view.findViewById(R.id.global_data2);
        TVglobalData3 = (TextView) view.findViewById(R.id.global_data3);
        country = (TextView) view.findViewById(R.id.country);
        cases = (TextView) view.findViewById(R.id.cases);
        casesToday = (TextView) view.findViewById(R.id.cases_today);
        deaths = (TextView) view.findViewById(R.id.deaths);
        deathsToday = (TextView) view.findViewById(R.id.deaths_today);
        recovered = (TextView) view.findViewById(R.id.recovered);
        setTextValues();
        return view;
    }

    private void setTextValues() {
        if(myDataFromActivity != null){
            int position = 0;
            TVglobalData1.setText(myDataFromActivity.getCases().toString());
            TVglobalData2.setText(myDataFromActivity.getDeaths().toString());
            TVglobalData3.setText(myDataFromActivity.getRecovered().toString());

            for(Countries country: countriesList){
                if(country.getCountry().equals("India"))
                    position = countriesList.indexOf(country);
            }
            country.setText(countriesList.get(position).getCountry());
            cases.setText(countriesList.get(position).getCases().toString());
            casesToday.setText(countriesList.get(position).getTodayCases().toString());
            deaths.setText(countriesList.get(position).getDeaths().toString());
            deathsToday.setText(countriesList.get(position).getTodayDeaths().toString());
            recovered.setText(countriesList.get(position).getRecovered().toString());
        }
    }
}
