package dev.owuor91.postscompose.api

import dev.owuor91.postscompose.model.Comment
import dev.owuor91.postscompose.model.Post
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiInterface {
  @GET("/posts")
  suspend fun fetchPosts(): Response<List<Post>>
  
  @GET("/posts/{postId}")
  suspend fun fetchPostById(@Path("postId") postId: Int): Response<Post>
  
  @GET("/posts/{postId}/comments")
  suspend fun fetchPostComments(@Path("postId") postId: Int): Response<List<Comment>>
}