package com.lentimosystems.licio.covid19.Model;

public class CovidModel {
    public String country;
    public Integer cases;
    public Integer todayCases;
    public Integer deaths;
    public Integer todayDeaths;
    public Integer recovered;
    public Integer critical;
    public Integer casesPerOneMillion;

    public CovidModel() {
    }

    public CovidModel(String country, Integer totalCases, Integer todayCases, Integer totalDeaths, Integer todayDeaths, Integer recovered, Integer critical, Integer casesPerOneMillion) {
        this.country = country;
        this.cases = totalCases;
        this.todayCases = todayCases;
        this.deaths = totalDeaths;
        this.todayDeaths = todayDeaths;
        this.recovered = recovered;
        this.critical = critical;
        this.casesPerOneMillion = casesPerOneMillion;
    }

    public String getCountry() {
        return country;
    }

    public Integer getTotalCases() {
        return cases;
    }

    public Integer getTodayCases() {
        return todayCases;
    }

    public Integer getTotalDeaths() {
        return deaths;
    }

    public Integer getTodayDeaths() {
        return todayDeaths;
    }

    public Integer getRecovered() {
        return recovered;
    }

    public Integer getCritical() {
        return critical;
    }

    public Integer getCasesPerOneMillion() {
        return casesPerOneMillion;
    }
}


