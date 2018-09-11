package io.petros.movies.data.di.dagger

import android.content.Context
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import io.petros.movies.data.BuildConfig
import io.petros.movies.data.R
import io.petros.movies.data.getLong
import io.petros.movies.data.network.WebService
import io.petros.movies.data.network.rest.RestApi
import io.petros.movies.data.network.rest.RestClient
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
class NetworkModule {

    @Provides
    @Singleton
    fun providesGson() = Gson()

    @Provides
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY
        return logging
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(
        context: Context,
        loggingInterceptor: HttpLoggingInterceptor
    ): OkHttpClient {
        val okHttpBuilder = OkHttpClient.Builder()
            .connectTimeout(context.getLong(R.integer.network_timeout), TimeUnit.MILLISECONDS)
        if (BuildConfig.DEBUG) okHttpBuilder.addInterceptor(loggingInterceptor)
        return okHttpBuilder.build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(context: Context, gson: Gson, httpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(context.getString(R.string.rest_themoviedb_url))
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(httpClient)
            .build()
    }

    @Provides
    @Singleton
    fun provideRestApi(retrofit: Retrofit): RestApi = retrofit.create(RestApi::class.java)

    @Provides
    @Singleton
    fun provideRestClient(restApi: RestApi): RestClient = RestClient(restApi)

    @Provides
    @Singleton
    fun provideWebService(restClient: RestClient): WebService = restClient

}
