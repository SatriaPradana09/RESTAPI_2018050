package com.example.populationapp;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Api {
    String BASE_URL = "https://datausa.io/api/data?drilldowns=Nation&measures=Population&year=latest";
    @GET("population?limit=10")
    Call<PopulationPojo> getPopulations();
}
