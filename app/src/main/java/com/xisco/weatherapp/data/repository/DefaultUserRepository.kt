package com.xisco.weatherapp.data.repository

import com.xisco.weatherapp.data.remote.GithubApi
import com.xisco.weatherapp.data.remote.dto.GitUserDto
import com.xisco.weatherapp.utils.Resource
import java.lang.Exception
import javax.inject.Inject

class DefaultUserRepository @Inject constructor(private val api: GithubApi) : UserRepository {
            override suspend fun getUser(username: String): Resource<GitUserDto> {
                        return try {
                                    val response = api.getGithubUser(username)
                                    val result = response.body()
                                    if(response.isSuccessful && result != null){
                                                Resource.Success(result)
                                    }else{
                                                Resource.Error(response.message())
                                    }
                        }
                        catch (e: Exception) {
                                    Resource.Error(e.message ?: "An error occurred")
                        }
            }
}