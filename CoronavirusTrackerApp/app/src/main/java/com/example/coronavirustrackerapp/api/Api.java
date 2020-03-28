package com.example.coronavirustrackerapp.api;

import com.example.coronavirustrackerapp.model.Countries;
import com.example.coronavirustrackerapp.model.Global;
import com.example.coronavirustrackerapp.model.States;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Api {

    String BASE_URL = "https://coronavirus-19-api.herokuapp.com/";   //Root url
    String STATES_API_BASE_URL = "https://script.googleusercontent.com/";
    String KEY = "wYx2JTqJKfZ_qzUI5kAf5iLzSBVQm0XiLoZUPRQBgicUajwxlvQHCTYkqYKL91SdZP3YSkiJX09K1tCGG-eKrW7ZklZ9Uspkm5_BxDlH2jW0nuo2oDemN9CCS2h10ox_1xSncGQajx_ryfhECjZEnKXFvsR88vL4WiBr168omFadgngDnj25DLpEvLRaiIpzZr1NvbW-Bo38vshdDBv10tpytj_A4aoE&lib=Mm1FD1HVuydJN5yAB3dc_e8h00DPSBbB3";

    @GET("all")
    Call<Global> getGlobalData();   //further address of the url

    @GET("countries")
    Call<List<Countries>> getCountriesData();

    @GET("macros/echo?user_content_key="+KEY)
    Call<States> getStatesDataList();

}
