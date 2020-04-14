package io.petros.movies.test.utils

import com.google.gson.Gson
import okhttp3.OkHttpClient
import okhttp3.mockwebserver.MockWebServer
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.InputStreamReader
import java.nio.charset.StandardCharsets
import java.util.concurrent.TimeUnit

const val MOCK_WEB_SERVER_URL = "localhost/"
const val TIMEOUT_MILLISECONDS = 10L

inline fun <reified T> api(server: MockWebServer): T {
    val client = OkHttpClient.Builder()
        .connectTimeout(TIMEOUT_MILLISECONDS, TimeUnit.MILLISECONDS)
        .readTimeout(TIMEOUT_MILLISECONDS, TimeUnit.MILLISECONDS)
        .writeTimeout(TIMEOUT_MILLISECONDS, TimeUnit.MILLISECONDS)
        .build()
    return Retrofit.Builder()
        .baseUrl(server.url(MOCK_WEB_SERVER_URL))
        .addConverterFactory(GsonConverterFactory.create(Gson()))
        .client(client)
        .build()
        .create(T::class.java)
}

fun Any.jsonFromFile(filePath: String): String {
    val resource = this::class.java.classLoader.getResource(filePath)
    if (resource != null) {
        val inputStream = resource.openStream()
        val stringBuilder = StringBuilder()
        val bufferedReader = InputStreamReader(inputStream, StandardCharsets.UTF_8)
        bufferedReader.use { stringBuilder.append(it.readText()) }
        return stringBuilder.toString()
    } else {
        throw IllegalArgumentException("File not found. [File Path: $filePath]")
    }
}
