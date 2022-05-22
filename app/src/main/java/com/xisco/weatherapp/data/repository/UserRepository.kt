package com.xisco.weatherapp.data.repository

import com.xisco.weatherapp.data.remote.dto.GitUserDto
import com.xisco.weatherapp.utils.Resource

interface UserRepository   {
             suspend fun getUser(username:String): Resource<GitUserDto>

}