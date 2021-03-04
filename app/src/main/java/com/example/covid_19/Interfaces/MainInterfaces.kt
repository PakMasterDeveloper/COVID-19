package com.example.covid_19.Interfaces

import com.example.covid_19.Model.CountriesModelClass
import com.example.covid_19.Model.MyModelClass
import retrofit2.Call
import retrofit2.http.GET

interface MainInterfaces {
    @GET("v3/covid-19/all")
    fun GetData(): Call<MyModelClass>

    @GET("/v3/covid-19/countries")
    fun getDataByCountry():Call<List<CountriesModelClass>>
}