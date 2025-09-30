package dev.owuor91.postscompose.repository

import dev.owuor91.postscompose.api.AuthApiInterface
import dev.owuor91.postscompose.model.LoginRequest
import dev.owuor91.postscompose.model.LoginResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response

interface AuthRepository {
  suspend fun login(loginRequest: LoginRequest): Response<LoginResponse>
}

class AuthRepositoryImpl(val apiInterface: AuthApiInterface): AuthRepository{
  override suspend fun login(loginRequest: LoginRequest): Response<LoginResponse> {
    return withContext(Dispatchers.IO){
      apiInterface.login(loginRequest)
    }
  }
}