package com.example.coronavirustrackerapp.model;

import com.google.gson.annotations.SerializedName;

public class Global {

    @SerializedName("cases")
    private Integer cases;
    @SerializedName("deaths")
    private Integer deaths;
    @SerializedName("recovered")
    private Integer recovered;

    public Integer getCases() {
        return cases;
    }

    public void setCases(Integer cases) {
        this.cases = cases;
    }

    public Integer getDeaths() {
        return deaths;
    }

    public void setDeaths(Integer deaths) {
        this.deaths = deaths;
    }

    public Integer getRecovered() {
        return recovered;
    }

    public void setRecovered(Integer recovered) {
        this.recovered = recovered;
    }

}
