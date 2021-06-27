package com.example.covidtracker

import com.google.gson.FieldNamingPolicy
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiUtilities {

    val gson = GsonBuilder().
            setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
        .create()

    private val retrofit = Retrofit.Builder()
        .baseUrl("https://corona.lmao.ninja/v2/")
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()

    val api = retrofit.create(Api_Interface::class.java)
}