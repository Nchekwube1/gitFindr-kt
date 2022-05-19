package com.xisco.weatherapp.data.remote
import com.xisco.weatherapp.data.remote.dto.GitUserDto
import retrofit2.http.GET
import retrofit2.http.Path

interface GithubApi {

            @GET("/{username}")
            suspend fun  getGithubUser(@Path("username") username:String):GitUserDto

}