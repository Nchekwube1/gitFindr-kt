package com.xisco.weatherapp.di

import com.xisco.weatherapp.data.remote.GithubApi
import com.xisco.weatherapp.data.repository.UserRepositoryImpl
import com.xisco.weatherapp.domain.repository.UserRepository
import com.xisco.weatherapp.utils.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
            @Provides
            @Singleton
            fun provideGithubApi(): GithubApi{
                        return  Retrofit.Builder()
                                    .baseUrl(Constants.GIT_URL)
                                    .addConverterFactory(GsonConverterFactory.create())
                                    .build()
                                    .create(GithubApi::class.java)

            }

            @Provides
            @Singleton
            fun provideGithubRepository(api: GithubApi): UserRepository{
                        return  UserRepositoryImpl(api)
            }

}