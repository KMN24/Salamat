package kg.sunrise.salamat.modules

import android.content.Context
import android.util.Log
import kg.sunrise.salamat.BuildConfig
import kg.sunrise.salamat.utils.preference.*
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import timber.log.Timber
import java.util.concurrent.TimeUnit

private val sLogLevel =
    if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE

private const val version = "v1"
private const val testUrl = "http://agakhan.sunrisetest.online/api/$version/"

const val baseUrl = "http://salamat.med.kg"
private const val baseBackUrl = "$baseUrl/api/$version/"
private const val baseRegistraturaUrl = "https://registratura.med.kg/api/$version/"
private const val baseTokenRegistratura = "btkP4FeYC9QwmZiM4qmpg9RvE19FG0RD_salamat"

private const val currentBack = testUrl
private const val currentRegistratura = baseRegistraturaUrl


fun createNetworkClient(context: Context) = retrofitClient(
    currentBack,
    okHttpClient(context)
)

fun createRegitrstraturaClient(context: Context) = retrofitRegistraturaClient(
    currentRegistratura,
    okRegistraturaHttpClient(context)
)

fun okRegistraturaHttpClient(context: Context) = OkHttpClient.Builder()
    .addInterceptor(getLogInterceptor()).apply {
        setTimeOutToOkHttpClient(
            this
        )
    }
    .addInterceptor(headersRegistraturaInterceptor(context)).build()


private fun retrofitRegistraturaClient(url: String, httpClient: OkHttpClient): Retrofit {
    return Retrofit.Builder()
        .baseUrl(url)
        .client(httpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}

private fun retrofitClient(url: String, httpClient: OkHttpClient): Retrofit {
    return Retrofit.Builder()
        .baseUrl(url)
        .client(httpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}


private fun getLogInterceptor() = HttpLoggingInterceptor().apply { level = sLogLevel }

private fun okHttpClient(context: Context) = OkHttpClient.Builder()
    .addInterceptor(getLogInterceptor()).apply {
        setTimeOutToOkHttpClient(
            this
        )
    }
    .addInterceptor(headersInterceptor(context)).build()

fun headersRegistraturaInterceptor(context: Context) = Interceptor { chain ->
    chain.proceed(
        chain.request().newBuilder()
            .addHeader("Content-Type", "application/json")
            .addHeader("Req-Token", baseTokenRegistratura)
            .also {
                val token = getRegistraturaToken(context)
                if (token != EMPTY_VALUE) {
                    it.addHeader(
                        "Authorization",
                        "Bearer $token"
                    )
                }
            }
            .build()
    )
}

fun headersInterceptor(context: Context) = Interceptor { chain ->
    chain.proceed(
        chain.request().newBuilder()
            .addHeader("Content-Type", "application/json")
            .also {
                val token = getAccessToken(context)
                if (token != EMPTY_VALUE) {
                    it.addHeader(
                        "Authorization",
                        "Token $token"
                    )
                }
                Timber.e(token)
                val lang = getLanguage(context)
                if (lang != EMPTY_VALUE) {
                    it.addHeader(
                        "Accept-Language",
                        if (lang == "ru") "ru-ru" else "kg-kg"
                    )
                }
            }
            .build()
    )
}

private fun setTimeOutToOkHttpClient(okHttpClientBuilder: OkHttpClient.Builder) =
    okHttpClientBuilder.apply {
        readTimeout(30L, TimeUnit.SECONDS)
        connectTimeout(30L, TimeUnit.SECONDS)
        writeTimeout(30L, TimeUnit.SECONDS)
    }