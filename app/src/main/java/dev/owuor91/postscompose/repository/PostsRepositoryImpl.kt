package dev.owuor91.postscompose.repository

import dev.owuor91.postscompose.api.ApiInterface
import dev.owuor91.postscompose.model.Comment
import dev.owuor91.postscompose.model.Post
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response

interface PostsRepository{
  suspend fun fetchPosts(): Response<List<Post>>
  
  suspend fun fetchPostById(postId: Int): Response<Post>
  
  suspend fun fetchPostComments(postId: Int): Response<List<Comment>>
  
}

class PostsRepositoryImpl(val retrofit: ApiInterface): PostsRepository {
  
  override suspend fun fetchPosts(): Response<List<Post>>{
    return withContext(Dispatchers.IO){
      retrofit.fetchPosts()
    }
  }
  
  override suspend fun fetchPostById(postId: Int): Response<Post>{
    return withContext(Dispatchers.IO){
      retrofit.fetchPostById(postId)
    }
  }
  
  override suspend fun fetchPostComments(postId: Int): Response<List<Comment>>{
    return withContext(Dispatchers.IO){
      retrofit.fetchPostComments(postId)
    }
  }
  
}