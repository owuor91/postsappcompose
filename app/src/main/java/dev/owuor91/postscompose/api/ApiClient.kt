package dev.owuor91.postscompose.api

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {
  val logging = HttpLoggingInterceptor()
    .apply { level = HttpLoggingInterceptor.Level.BODY }
  
  val okHttpClient = OkHttpClient
    .Builder()
    .addInterceptor(logging).build()
  
  val retrofit = Retrofit.Builder()
    .baseUrl("https://jsonplaceholder.typicode.com")
    .addConverterFactory(GsonConverterFactory.create())
    .client(okHttpClient)
    .build()
  
  fun <T> buildApiClient(apiInterface: Class<T>): T{
    return retrofit.create(apiInterface)
  }
}