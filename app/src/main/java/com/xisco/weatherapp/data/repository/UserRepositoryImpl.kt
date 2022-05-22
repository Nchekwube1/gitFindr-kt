package com.xisco.weatherapp.data.repository

import com.xisco.weatherapp.data.remote.GithubApi
import com.xisco.weatherapp.data.remote.dto.GitUserDto
import com.xisco.weatherapp.domain.repository.UserRepository
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
            private val api: GithubApi
) : UserRepository  {
            override suspend fun getUser(username: String) =
                         api.getGithubUser(username)

}