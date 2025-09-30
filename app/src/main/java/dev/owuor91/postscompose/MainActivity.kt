package dev.owuor91.postscompose

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import dev.owuor91.postscompose.screens.AppNavigation
import dev.owuor91.postscompose.screens.Screen.Login
import dev.owuor91.postscompose.screens.Screen.Posts
import dev.owuor91.postscompose.ui.theme.PostsComposeTheme

class MainActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    enableEdgeToEdge()
    setContent {
      PostsComposeTheme {
        Surface(modifier = Modifier
          .fillMaxSize()
          .safeContentPadding()) {
          val prefs = getSharedPreferences("POSTSAPP_PREFS", Context.MODE_PRIVATE)
          val token = prefs.getString("ACCESS_TOKEN","") ?: ""
          var startDestination = Login.route
          if (token.isNotBlank()) {
            startDestination = Posts.route
          }
          AppNavigation(startDestination)
        }
      }
    }
  }
}

