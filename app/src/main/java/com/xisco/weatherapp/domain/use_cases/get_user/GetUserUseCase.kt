package com.xisco.weatherapp.domain.use_cases.get_user

import com.xisco.weatherapp.data.remote.dto.toGitUser
import com.xisco.weatherapp.domain.model.GitUser
import com.xisco.weatherapp.domain.repository.UserRepository
import com.xisco.weatherapp.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetUserUseCase @Inject constructor(
            private val repository: UserRepository
) {
            operator fun invoke(username: String): Flow<Resource<GitUser>> = flow {
                        try {
                                    emit(Resource.Loading())
                                    val coins = repository.getUser(username).toGitUser()
                                    emit(Resource.Success(coins))
                        }
                        catch (e: HttpException) {
                                    emit(
                                                Resource.Error(
                                                            e.localizedMessage
                                                                        ?: "An unexpected error occurred"
                                                )
                                    )
                        }
                        catch (e: IOException) {
                                    emit(
                                                Resource.Error(
                                                             "Couldn't reach server. Check your internet connection"
                                                )
                                    )

                        }
            }
}