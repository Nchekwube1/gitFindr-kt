package com.xisco.weatherapp.data.repository

import com.xisco.weatherapp.data.remote.GithubApi
import com.xisco.weatherapp.data.remote.dto.GitUserDto
import com.xisco.weatherapp.utils.Resource
import javax.inject.Inject

interface UserRepository   {
             suspend fun getUser(username:String): Resource<GitUserDto>

}