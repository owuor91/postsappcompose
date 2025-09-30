package dev.owuor91.postscompose.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.owuor91.postscompose.model.Post
import dev.owuor91.postscompose.model.UIState
import dev.owuor91.postscompose.viewmodel.PostsViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun PostsScreenImpl(
  onClickPost: (Int) -> Unit,
  viewModel: PostsViewModel = koinViewModel()
) {
  LaunchedEffect(Unit) {
    viewModel.fetchPosts()
  }
  val posts by viewModel.posts.observeAsState()
  val uiState by viewModel.uiState.observeAsState(UIState())
  
  
  Column(
    modifier = Modifier
      .fillMaxSize()
      .padding(16.dp)
  ) {
    
    when {
      uiState.isLoading -> {
        Box(
          Modifier.fillMaxSize(),
          contentAlignment = Alignment.Center
        ) {
          CircularProgressIndicator(modifier = Modifier.testTag("progressBar"))
        }
      }
      
      uiState.success != null -> {
        if (posts != null) {
          LazyColumn(verticalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier.testTag("postsList")) {
            items(posts!!) { post -> PostCard(post, onClickPost) }
          }
        }
      }
      
      uiState.error != null -> {
        Box(
          modifier = Modifier.fillMaxSize(),
          contentAlignment = Alignment.Center
        ) {
          Text(text = uiState.error.toString())
        }
      }
    }
  }
}

@Composable
fun PostCard(post: Post, onClickPost: (Int) -> Unit) {
  Card(
    onClick = { onClickPost(post.id) },
    modifier = Modifier.fillMaxWidth().testTag("clickablePost"),
    shape = RoundedCornerShape(8.dp),
    colors = CardDefaults.cardColors(containerColor = Color.White)
  ) {
    Column(modifier = Modifier.padding(8.dp, 4.dp)) {
      Text(text = post.title, fontWeight = FontWeight.Bold)
      Spacer(Modifier.height(4.dp))
      Text(text = post.body)
    }
  }
}

@Preview(showBackground = true)
@Composable
fun PostsScreenPreview() {
  val post = Post(
    userId = 1,
    id = 2,
    title = "ea molestias quasi exercitationem repellat qui ipsa sit aut",
    body = "et iusto sed quo iure\\nvoluptatem occaecati omnis eligendi aut ad\\nvoluptatem doloribus vel accusantium quis pariatur\\nmolestiae porro eius odio et labore et velit aut"
  )
  
  PostCard(post, {})
}