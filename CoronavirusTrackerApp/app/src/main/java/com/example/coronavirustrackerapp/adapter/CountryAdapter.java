package com.example.coronavirustrackerapp.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.coronavirustrackerapp.R;
import com.example.coronavirustrackerapp.model.Countries;

import java.util.List;

public class CountryAdapter extends RecyclerView.Adapter<CountryAdapter.CountryViewHolder> {
private List<Countries> countriesList;
private int rowLayout;
private Context context;

public CountryAdapter(List<Countries> countriesList, int rowLayout, Context context) {
        this.countriesList = countriesList;
        this.rowLayout = rowLayout;
        this.context = context;
        }

//A view holder inner class where we get reference to the views in the layout using their ID
public static class CountryViewHolder extends RecyclerView.ViewHolder {
    TextView country;
    TextView cases;
    TextView casesToday;
    TextView deaths;
    TextView deathsToday;
    TextView recovered;

    public CountryViewHolder(View v) {
        super(v);
        Log.d("Hi","15");
        country = (TextView) v.findViewById(R.id.country);
        cases = (TextView) v.findViewById(R.id.cases);
        casesToday = (TextView) v.findViewById(R.id.cases_today);
        deaths = (TextView) v.findViewById(R.id.deaths);
        deathsToday = (TextView) v.findViewById(R.id.deaths_today);
        recovered = (TextView) v.findViewById(R.id.recovered);
    }
}
    @Override
    public CountryAdapter.CountryViewHolder onCreateViewHolder(ViewGroup parent,
                                                               int viewType) {
        Log.d("Hi","16");
        View view = LayoutInflater.from(parent.getContext()).inflate(rowLayout, parent, false);
        return new CountryViewHolder(view);
    }
    @Override
    public void onBindViewHolder(CountryViewHolder holder, final int position) {
        Log.d("Hi","17");
        holder.country.setText(countriesList.get(position).getCountry());
        holder.cases.setText(countriesList.get(position).getCases().toString());
        holder.casesToday.setText(countriesList.get(position).getTodayCases().toString());
        holder.deaths.setText(countriesList.get(position).getDeaths().toString());
        holder.deathsToday.setText(countriesList.get(position).getTodayDeaths().toString());
        holder.recovered.setText(countriesList.get(position).getRecovered().toString());
    }

    @Override
    public int getItemCount() {
        Log.d("Hi","18");
        return countriesList.size();
    }
}
