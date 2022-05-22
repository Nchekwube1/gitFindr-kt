package com.xisco.weatherapp.data.repository

import com.xisco.weatherapp.data.remote.GithubApi
import javax.inject.Inject

class UserRepository @Inject constructor(
            private val api: GithubApi
)   {
             suspend fun getUser() =
                         api.getGithubUser()

}