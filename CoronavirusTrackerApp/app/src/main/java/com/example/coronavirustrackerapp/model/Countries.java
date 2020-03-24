package com.example.coronavirustrackerapp.model;

import com.google.gson.annotations.SerializedName;

public class Countries {

    @SerializedName("country")
    private String country;
    @SerializedName("cases")
    private Integer cases;
    @SerializedName("todayCases")
    private Integer todayCases;
    @SerializedName("deaths")
    private Integer deaths;
    @SerializedName("todayDeaths")
    private Integer todayDeaths;
    @SerializedName("recovered")
    private Integer recovered;
    @SerializedName("active")
    private Integer active;
    @SerializedName("critical")
    private Integer critical;
    @SerializedName("casesPerOneMillion")
    private Integer casesPerOneMillion;

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Integer getCases() {
        return cases;
    }

    public void setCases(Integer cases) {
        this.cases = cases;
    }

    public Integer getTodayCases() {
        return todayCases;
    }

    public void setTodayCases(Integer todayCases) {
        this.todayCases = todayCases;
    }

    public Integer getDeaths() {
        return deaths;
    }

    public void setDeaths(Integer deaths) {
        this.deaths = deaths;
    }

    public Integer getTodayDeaths() {
        return todayDeaths;
    }

    public void setTodayDeaths(Integer todayDeaths) {
        this.todayDeaths = todayDeaths;
    }

    public Integer getRecovered() {
        return recovered;
    }

    public void setRecovered(Integer recovered) {
        this.recovered = recovered;
    }

    public Integer getActive() {
        return active;
    }

    public void setActive(Integer active) {
        this.active = active;
    }

    public Integer getCritical() {
        return critical;
    }

    public void setCritical(Integer critical) {
        this.critical = critical;
    }

    public Integer getCasesPerOneMillion() {
        return casesPerOneMillion;
    }

    public void setCasesPerOneMillion(Integer casesPerOneMillion) {
        this.casesPerOneMillion = casesPerOneMillion;
    }

}
