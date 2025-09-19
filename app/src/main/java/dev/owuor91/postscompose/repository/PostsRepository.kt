package dev.owuor91.postscompose.repository

import dev.owuor91.postscompose.api.ApiClient
import dev.owuor91.postscompose.api.ApiInterface
import dev.owuor91.postscompose.model.Comment
import dev.owuor91.postscompose.model.Post
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response

class PostsRepository {
  val retrofit = ApiClient.buildApiClient(ApiInterface::class.java)
  
  suspend fun fetchPosts(): Response<List<Post>>{
    return withContext(Dispatchers.IO){
      retrofit.fetchPosts()
    }
  }
  
  suspend fun fetchPostById(postId: Int): Response<Post>{
    return withContext(Dispatchers.IO){
      retrofit.fetchPostById(postId)
    }
  }
  
  suspend fun fetchPostComments(postId: Int): Response<List<Comment>>{
    return withContext(Dispatchers.IO){
      retrofit.fetchPostComments(postId)
    }
  }
}