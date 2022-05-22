package com.xisco.weatherapp.domain.repository

import com.xisco.weatherapp.data.remote.dto.GitUserDto
import retrofit2.Response

interface UserRepository {
            suspend fun getUser(username:String): Response<GitUserDto>
}