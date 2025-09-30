package dev.owuor91.postscompose

import dev.owuor91.postscompose.model.Comment
import dev.owuor91.postscompose.model.Post
import okhttp3.ResponseBody.Companion.toResponseBody
import retrofit2.Response

object TestData {
  val mockPosts = listOf(
    Post(
      userId = 1,
      id = 1,
      title = "Title 1",
      body = "Body 1"
    ),
    Post(
      userId = 2,
      id = 2,
      title = "Title 2",
      body = "Body 2"
    )
  )
  
  val mockPost =  Post(
    userId = 1,
    id = 1,
    title = "Title 1",
    body = "Body 1"
  )
  
  val mockComments = listOf(
    Comment(
      id = 1,
      postId = 1,
      name = "Abed",
      email = "abed@tomcat.com",
      body = "Comment body 1"
    ),
    Comment(
      id = 2,
      postId = 1,
      name = "Rafaela",
      email = "rafaela2027@gmail.com",
      body = "Comment body 2"
    )
  )
  
  val errorResponse = Response.error<List<Post>>(
    404,
    "{\"error\": \"Not found\"}".toResponseBody()
  )
}