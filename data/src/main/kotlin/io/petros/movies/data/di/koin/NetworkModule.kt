package io.petros.movies.data.di.koin

import android.content.Context
import com.google.gson.Gson
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import io.petros.movies.data.BuildConfig
import io.petros.movies.data.R
import io.petros.movies.data.getLong
import io.petros.movies.data.network.WebService
import io.petros.movies.data.network.rest.RestApi
import io.petros.movies.data.network.rest.RestClient
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val networkModule = module {
    single { Gson() }
    single { httpLoggingInterceptor() }
    single { okHttpClient(get(), get()) }
    single { retrofit(get(), get(), get()) }
    single { restApi(get()) }
    single<WebService> { RestClient(get(), get()) }
}

private fun httpLoggingInterceptor(): HttpLoggingInterceptor {
    val logging = HttpLoggingInterceptor()
    logging.level = HttpLoggingInterceptor.Level.BODY
    return logging
}

private fun okHttpClient(
    context: Context,
    loggingInterceptor: HttpLoggingInterceptor
): OkHttpClient {
    val okHttpBuilder = OkHttpClient.Builder()
        .connectTimeout(context.getLong(R.integer.network_timeout), TimeUnit.MILLISECONDS)
    if (BuildConfig.DEBUG) okHttpBuilder.addInterceptor(loggingInterceptor)
    return okHttpBuilder.build()
}

private fun retrofit(
    context: Context,
    gson: Gson,
    httpClient: OkHttpClient
): Retrofit {
    return Retrofit.Builder()
        .baseUrl(context.getString(R.string.rest_themoviedb_url))
        .addConverterFactory(GsonConverterFactory.create(gson))
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .client(httpClient)
        .build()
}

private fun restApi(retrofit: Retrofit): RestApi = retrofit.create(RestApi::class.java)
