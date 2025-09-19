package dev.owuor91.postscompose.screens

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dev.owuor91.postscompose.screens.Screen.Posts
import dev.owuor91.postscompose.screens.Screen.ViewPost

sealed class Screen(val route: String) {
  object Posts : Screen("posts")
  object ViewPost : Screen("viewPost")
}

@Composable
fun AppNavigation() {
  val navController = rememberNavController()
  NavHost(
    navController = navController,
    startDestination = Posts.route
  ) {
    composable(Posts.route) {
      PostsScreenImpl(
        onClickPost = {postId-> navController.navigate("${ViewPost.route}/$postId")}
      )
    }
    composable("${ViewPost.route}/{postId}") { navBackStackEntry ->
      val postId = navBackStackEntry.arguments?.getString("postId")
      if (postId != null) {
        ViewPostScreen(postId = postId.toInt())
      }
      
    }
  }
}