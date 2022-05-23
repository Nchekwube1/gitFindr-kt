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
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

//            @Provides
//            fun getClient(
//
//            ): OkHttpClient = OkHttpClient.Builder()
//                        .addInterceptor {
//                                    var request = it.request()
//
//                                    request = request.newBuilder().addHeader(
//                                                "Authentication", "Bearer 12355"
//                                    ).build()
//
//
//                                    return@addInterceptor  it.proceed(request)
//                        }
//                        .build()

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