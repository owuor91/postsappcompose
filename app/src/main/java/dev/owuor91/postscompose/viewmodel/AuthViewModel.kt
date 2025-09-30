package dev.owuor91.postscompose.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.owuor91.postscompose.model.LoginRequest
import dev.owuor91.postscompose.model.LoginResponse
import dev.owuor91.postscompose.repository.AuthRepository
import kotlinx.coroutines.launch

class AuthViewModel(val authRepository: AuthRepository): ViewModel() {
  val loginResponse = MutableLiveData<LoginResponse>()
  
  fun login(username: String, password: String){
    viewModelScope.launch {
      val resp = authRepository.login(LoginRequest(username, password))
      if (resp.isSuccessful){
        loginResponse.postValue(resp.body())
      }
    }
  }
}