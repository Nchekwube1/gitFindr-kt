package com.xisco.weatherapp.di

import com.xisco.weatherapp.data.remote.GithubApi
import com.xisco.weatherapp.data.repository.DefaultUserRepository
import com.xisco.weatherapp.data.repository.UserRepository
import com.xisco.weatherapp.utils.Constants
import com.xisco.weatherapp.utils.DispatcherProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
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
            fun provideGithubRepository(api: GithubApi): UserRepository= DefaultUserRepository(api)

            @Provides
            @Singleton
            fun provideDispatchers(): DispatcherProvider= object  : DispatcherProvider{
                        override val main: CoroutineDispatcher
                                    get() = Dispatchers.Main
                        override val io: CoroutineDispatcher
                                    get() = Dispatchers.IO
                        override val default: CoroutineDispatcher
                                    get() = Dispatchers.Default
                        override val unconfined: CoroutineDispatcher
                                    get() = Dispatchers.Unconfined
            }

}