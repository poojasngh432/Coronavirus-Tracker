package com.myapp.coronavirusapp;

import android.os.Bundle;

import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.myapp.coronavirusapp.adapter.CountryAdapter;
import com.myapp.coronavirusapp.model.Countries;

import java.util.ArrayList;
import java.util.List;

public class CountriesFragment extends Fragment implements SearchView.OnQueryTextListener {
    // TODO: Rename parameter arguments, choose names that match

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private List<Countries> myDataFromActivity;
    private RecyclerView recyclerView = null;
    private CountryAdapter countryAdapter;
    private MenuItem itemSearch;
    private SearchView searchView;
    private String searchText = "";

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
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
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
        countryAdapter = null;
        countryAdapter = new CountryAdapter(myDataFromActivity, R.layout.cardview_layout, getActivity());
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(countryAdapter);
        countryAdapter.notifyDataSetChanged();
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        if (!searchText.equals("")) {
            onQueryTextChange(searchText);
        }
    }

    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_search_bar, menu);
        itemSearch = menu.findItem(R.id.action_search);

            itemSearch.setVisible(true);
            searchView = (SearchView) itemSearch.getActionView();
            searchView.setOnQueryTextListener(this);
            itemSearch.setOnActionExpandListener(new MenuItem.OnActionExpandListener() {
                @Override
                public boolean onMenuItemActionExpand(MenuItem menuItem) {
                    return true;
                }

                @Override
                public boolean onMenuItemActionCollapse(MenuItem menuItem) {
                    searchView.setQuery("", false);
                    return true;
                }
            });
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onQueryTextSubmit(String s) {
        return true;
    }

    @Override
    public boolean onQueryTextChange(String s) {
        searchText = s;
        countryAdapter.setSearchText(s != null ? s : "");
        List<Countries> filteredCountries = new ArrayList<>();
        filteredCountries = getCountriesListAfterSearch(s);
        countryAdapter.setCountriesDataList(filteredCountries);
        countryAdapter.notifyDataSetChanged();
        return true;
    }

    private List<Countries> getCountriesListAfterSearch(String s){
        if (!s.equals("")) {
            List<Countries> filteredCountriesList = new ArrayList<>();
            for (Countries country : myDataFromActivity) {
                if (country.getCountry().toLowerCase().contains(s.toLowerCase())) {
                    filteredCountriesList.add(country);
                }
            }
            return filteredCountriesList;
        } else {
            return myDataFromActivity;
        }
    }
}
