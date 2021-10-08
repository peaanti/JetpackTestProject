package com.example.jetpacktestproject

import retrofit2.Call
import retrofit2.http.GET

interface ApiEndpoint {

    @GET("users/peaanti/repos")
    fun getRepos(): Call<List<Repo>>

}