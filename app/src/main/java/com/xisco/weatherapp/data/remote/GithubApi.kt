package com.xisco.weatherapp.data.remote

import retrofit2.http.GET

interface GithubApi {

            @GET("/username")
            suspend fun  getGithubUser(){

            }

}