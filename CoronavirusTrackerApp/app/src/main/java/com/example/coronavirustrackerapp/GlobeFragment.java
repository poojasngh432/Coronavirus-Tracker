package com.example.coronavirustrackerapp;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.coronavirustrackerapp.adapter.CountryAdapter;
import com.example.coronavirustrackerapp.api.Api;
import com.example.coronavirustrackerapp.model.Countries;
import com.example.coronavirustrackerapp.model.Global;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

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
        Log.d("Hi","3");
        GlobeFragment fragment = new GlobeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        Log.d("Hi","4");
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
        Log.d("Hi","5");
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
