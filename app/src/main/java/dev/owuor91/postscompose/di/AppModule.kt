package dev.owuor91.postscompose.di

import dev.owuor91.postscompose.api.ApiInterface
import dev.owuor91.postscompose.repository.PostsRepository
import dev.owuor91.postscompose.repository.PostsRepositoryImpl
import dev.owuor91.postscompose.viewmodel.PostsViewModel
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val networkModule = module {
  single {
    HttpLoggingInterceptor().apply {
      level = HttpLoggingInterceptor.Level.BODY
    }
  }
  
  single {
    OkHttpClient.Builder()
      .addInterceptor(get<HttpLoggingInterceptor>())
      .build()
  }
  
  single {
    Retrofit.Builder()
      .baseUrl("https://jsonplaceholder.typicode.com")
      .addConverterFactory(GsonConverterFactory.create())
      .client(get())
      .build()
  }
  
  single<ApiInterface> {
    get<Retrofit>().create(ApiInterface::class.java)
  }
}


val repositoryModule = module {
  single<PostsRepository> {
    PostsRepositoryImpl(get())
  }
}

val viewModelModule = module {
  single {
    PostsViewModel(get())
  }
}

val appModules = listOf(networkModule, repositoryModule, viewModelModule)