package com.lentimosystems.licio.covid19.Api;

import com.lentimosystems.licio.covid19.Model.CovidModel;

import java.util.List;


import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.GET;

public interface CovidApi {
    @GET("countries")
    Observable<List<CovidModel>> getCovidModel();

}
