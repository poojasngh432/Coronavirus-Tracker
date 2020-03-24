package com.example.coronavirustrackerapp.api;

import com.example.coronavirustrackerapp.model.Countries;
import com.example.coronavirustrackerapp.model.Global;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Api {

    String BASE_URL = "https://coronavirus-19-api.herokuapp.com/";   //Root url

    @GET("all")
    Call<Global> getGlobalData();   //further address of the url

    @GET("countries")
    Call<List<Countries>> getCountriesData();

}
