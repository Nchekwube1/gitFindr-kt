package com.xisco.weatherapp.domain.repository

import com.xisco.weatherapp.data.remote.dto.GitUserDto

interface UserRepository {
            suspend fun getUser(username:String): GitUserDto
}