package dev.owuor91.postscompose.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import dev.owuor91.postscompose.viewmodel.PostsViewModel

@Composable
fun ViewPostScreen(
  postId: Int,
  postsViewModel: PostsViewModel = viewModel()
) {
  LaunchedEffect(Unit) {
    postsViewModel.fetchPostById(postId)
    postsViewModel.fetchPostComments(postId)
  }
  val post by postsViewModel.post.observeAsState()
  val comments by postsViewModel.comments.observeAsState()
  
  Column(Modifier
    .fillMaxSize()
    .padding(16.dp)) {
    post?.let {
      Text(text = it.title, fontWeight = FontWeight.Bold)
      Spacer(Modifier.height(8.dp))
      Text(text = it.body)
    }
    comments?.let {
      Text(text = "COMMENTS", fontWeight = FontWeight.Bold)
      //Display comments list here
    }
    
  }
}