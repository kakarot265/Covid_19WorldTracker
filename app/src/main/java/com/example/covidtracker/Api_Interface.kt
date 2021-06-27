package com.example.covidtracker

import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface Api_Interface {

 @GET("countries")

 suspend fun getCountryData(): Response<List<CountryData>>

 @GET("countries/{country}")

 suspend fun searchCountry(@Path("country")country:String): Response<CountryData>







}