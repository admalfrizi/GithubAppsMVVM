package com.aplikasi.githubapiapps.di

import com.aplikasi.githubapiapps.app.Routes
import com.aplikasi.githubapiapps.data.RemoteDataSource
import com.aplikasi.githubapiapps.repository.AppRepository
import com.aplikasi.githubapiapps.util.Constants
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideInterceptor() : HttpLoggingInterceptor {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY

        return interceptor
    }

    @Singleton
    @Provides
    fun provideHttpClient(
        interceptor: HttpLoggingInterceptor
    ): OkHttpClient {
        return OkHttpClient
            .Builder()
            .addInterceptor(interceptor)
            .readTimeout(15, TimeUnit.SECONDS)
            .connectTimeout(15, TimeUnit.SECONDS)
            .build()
    }


    @Singleton
    @Provides
    fun provideBaseUrl(): String {
        return Constants.BASE_URL
    }

    @Singleton
    @Provides
    fun provideService(
        okHttpClient: OkHttpClient,
        baseUrl : String
    ): Routes {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().setLenient().create()))
            .client(okHttpClient)
            .build()
            .create(Routes::class.java)
    }

    @Provides
    fun provideRemoteData(routes: Routes): RemoteDataSource {
        return RemoteDataSource(routes)
    }

    @Provides
    fun provideUserRepo(remoteDataSource: RemoteDataSource): AppRepository {
        return AppRepository(remoteDataSource)
    }
}