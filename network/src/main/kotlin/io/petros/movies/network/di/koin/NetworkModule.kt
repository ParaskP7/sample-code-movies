package io.petros.movies.network.di.koin

import com.google.gson.Gson
import io.petros.movies.network.WebService
import io.petros.movies.network.rest.RestApi
import io.petros.movies.network.rest.RestClient
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

const val TIMEOUT_SECS: Int = 10
const val THEMOVIEDB_URL = "https://api.themoviedb.org/"

val networkModule = module {
    single { Gson() }
    single { httpLoggingInterceptor() }
    single { okHttpClient(get()) }
    single { retrofit(get(), get()) }
    single { restApi(get()) }
    single<WebService> { RestClient(get()) }
}

private fun httpLoggingInterceptor(): HttpLoggingInterceptor {
    val logging = HttpLoggingInterceptor()
    logging.level = HttpLoggingInterceptor.Level.BODY
    return logging
}

private fun okHttpClient(
    loggingInterceptor: HttpLoggingInterceptor
): OkHttpClient {
    return OkHttpClient.Builder()
        .connectTimeout(toMillis(TIMEOUT_SECS), TimeUnit.MILLISECONDS)
        .addInterceptor(loggingInterceptor) // TODO: Think about how to add this `if (BuildConfig.DEBUG)` logic back
        .build()
}

private fun retrofit(
    gson: Gson,
    httpClient: OkHttpClient
): Retrofit {
    return Retrofit.Builder()
        .baseUrl(THEMOVIEDB_URL)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .client(httpClient)
        .build()
}

private fun restApi(retrofit: Retrofit): RestApi = retrofit.create(RestApi::class.java)

// TODO: Move constant and function below to `utils` module

private const val ONE_SEC_IN_MILLS: Long = 1000L

fun toMillis(seconds: Int) = seconds * ONE_SEC_IN_MILLS
