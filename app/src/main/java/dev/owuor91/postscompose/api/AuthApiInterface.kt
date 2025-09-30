package dev.owuor91.postscompose.api

import dev.owuor91.postscompose.model.LoginRequest
import dev.owuor91.postscompose.model.LoginResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthApiInterface {
  @POST("/auth/login")
  suspend fun login(@Body loginRequest: LoginRequest): Response<LoginResponse>
}