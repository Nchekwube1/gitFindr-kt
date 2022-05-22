package com.xisco.weatherapp.data.remote
import com.xisco.weatherapp.data.remote.dto.GitUserDto
import com.xisco.weatherapp.utils.Constants
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface GithubApi {

            @GET("/${Constants.GIT_USER}")
//            suspend fun  getGithubUser(@Path("username") username:String):Response<GitUserDto>
            suspend fun  getGithubUser():Response<GitUserDto>

}