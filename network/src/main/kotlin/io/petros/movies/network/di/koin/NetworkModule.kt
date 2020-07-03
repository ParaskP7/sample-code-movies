package io.petros.movies.network.di.koin

import com.google.gson.Gson
import io.petros.movies.network.WebService
import io.petros.movies.network.rest.RestApi
import io.petros.movies.network.rest.RestClient
import io.petros.movies.network.rest.THEMOVIEDB_API_KEY
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

private const val TIMEOUT_SECS = 10L

fun networkModule(
    isDebug: Boolean,
    baseUrl: String,
    themoviedbApiKey: String,
) = module {
    single { Gson() }
    single { httpLoggingInterceptor() }
    single { okHttpClient(isDebug, get()) }
    single { retrofit(baseUrl, get(), get()) }
    single { restApi(get()) }
    single<WebService> { RestClient(get()) }
    THEMOVIEDB_API_KEY = themoviedbApiKey
}

private fun httpLoggingInterceptor(): HttpLoggingInterceptor {
    val logging = HttpLoggingInterceptor()
    logging.level = HttpLoggingInterceptor.Level.BODY
    return logging
}

private fun okHttpClient(
    isDebug: Boolean,
    loggingInterceptor: HttpLoggingInterceptor,
): OkHttpClient {
    val okHttpBuilder = OkHttpClient.Builder()
        .connectTimeout(TIMEOUT_SECS, TimeUnit.SECONDS)
        .readTimeout(TIMEOUT_SECS, TimeUnit.SECONDS)
        .writeTimeout(TIMEOUT_SECS, TimeUnit.SECONDS)
    if (isDebug) okHttpBuilder.addInterceptor(loggingInterceptor)
    return okHttpBuilder.build()
}

private fun retrofit(
    baseUrl: String,
    gson: Gson,
    httpClient: OkHttpClient,
): Retrofit {
    return Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .client(httpClient)
        .build()
}

private fun restApi(retrofit: Retrofit): RestApi = retrofit.create(RestApi::class.java)
