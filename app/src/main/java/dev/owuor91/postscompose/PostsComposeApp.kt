package dev.owuor91.postscompose

import android.app.Application
import dev.owuor91.postscompose.di.appModules
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class PostsComposeApp: Application() {
  
  override fun onCreate() {
    super.onCreate()
    startKoin {
      androidContext(this@PostsComposeApp)
      modules(appModules)
    }
  }
}