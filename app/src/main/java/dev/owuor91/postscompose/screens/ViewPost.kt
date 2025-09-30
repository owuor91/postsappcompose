package dev.owuor91.postscompose.screens

import android.content.Context
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import dev.owuor91.postscompose.model.Comment
import dev.owuor91.postscompose.viewmodel.PostsViewModel
import org.koin.androidx.compose.koinViewModel
import androidx.core.content.edit

@Composable
fun ViewPostScreen(
  postId: Int,
  postsViewModel: PostsViewModel = koinViewModel()
) {
  val context = LocalContext.current
  LaunchedEffect(Unit) {
    postsViewModel.fetchPostById(postId)
    postsViewModel.fetchPostComments(postId)
  }
  val post by postsViewModel.post.observeAsState()
  val comments by postsViewModel.comments.observeAsState()
  
  Column(Modifier
    .fillMaxSize()
    .padding(16.dp)) {
    
    Text(text = "LOG OUT", modifier = Modifier.clickable {
      val prefs = context.getSharedPreferences("POSTSAPP_PREFS",Context.MODE_PRIVATE)
      prefs.edit { remove("ACCESS_TOKEN") }
    })
    
    post?.let {
      Text(text = it.title, fontWeight = FontWeight.Bold)
      Spacer(Modifier.height(8.dp))
      Text(text = it.body)
    }
    Spacer(Modifier.height(8.dp))
    comments?.let {
      Text(text = "COMMENTS", fontWeight = FontWeight.Bold)
      LazyColumn { items(it){item-> CommentCard(item) } }
    }
    
  }
}

@Composable
fun CommentCard(comment: Comment){
    Column(Modifier.fillMaxWidth().padding(16.dp, 8.dp)) {
      Text(text = comment.name)
      Text(text = comment.email)
      Text(text = comment.body)
      HorizontalDivider(thickness = 1.dp, color = Color.Black)
    }
}