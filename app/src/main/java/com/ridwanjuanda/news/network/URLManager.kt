package com.ridwanjuanda.news.network

import android.content.Context
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.ridwanjuanda.news.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*
import java.util.concurrent.TimeUnit

/**
 * @author Ridwan Juanda
 * @date 04/10/20
 */

@Deprecated(message = "Deprecated change to MVVM")
class URLManager {

    companion object {
        private const val TIMEOUT: Long = 60
        private var singleton: Retrofit? = null
        private var singletonRewards: Retrofit? = null
        private var singletonList: WeakHashMap<String, Retrofit>? = null
        var gson: Gson = GsonBuilder().setLenient().create()

        fun getRetrofit(): Retrofit {
            if (singleton == null) {
                synchronized(this) {
                    singleton = Retrofit.Builder()
                        .baseUrl( URLConfig.SERVICES_URL)
                        .addConverterFactory(GsonConverterFactory.create(gson))
                        .client(getClient())
                        .build()
                }
            }
            return singleton!!
        }

        private fun getClient(context: Context? = null): OkHttpClient {
            val logging = HttpLoggingInterceptor()
            logging.level = if (BuildConfig.DEBUG) {
                HttpLoggingInterceptor.Level.BODY
            } else {
                HttpLoggingInterceptor.Level.NONE
            }

            val builder = OkHttpClient().newBuilder()
                .connectTimeout(TIMEOUT, TimeUnit.SECONDS)
                .writeTimeout(TIMEOUT, TimeUnit.SECONDS)
                .readTimeout(TIMEOUT, TimeUnit.SECONDS)
                .cache(null)
                .addInterceptor(logging)
            return builder.build()
        }
    }
}