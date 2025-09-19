package dev.owuor91.postscompose.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.owuor91.postscompose.model.Comment
import dev.owuor91.postscompose.model.Post
import dev.owuor91.postscompose.model.UIState
import dev.owuor91.postscompose.repository.PostsRepository
import kotlinx.coroutines.launch

class PostsViewModel: ViewModel() {
  val postsRepository = PostsRepository()
  val posts = MutableLiveData<List<Post>>()
  val uiState = MutableLiveData(UIState())
  val post = MutableLiveData<Post>()
  val comments = MutableLiveData<List<Comment>>()
  
  fun fetchPosts(){
    viewModelScope.launch {
      uiState.value = uiState.value?.copy(isLoading = true)
      val response = postsRepository.fetchPosts()
      if(response.isSuccessful){
        uiState.value = uiState.value?.copy(success = "Fetched posts successfully",
          isLoading = false)
        posts.postValue(response.body())
      }else{
        uiState.value = uiState.value?.copy(error = response.errorBody()?.string(),
          isLoading = false)
      }
    }
    
  }
  
  fun fetchPostById(postId: Int){
    viewModelScope.launch {
      uiState.value = uiState.value?.copy(isLoading = true)
      val response = postsRepository.fetchPostById(postId)
      if (response.isSuccessful){
        uiState.value = uiState.value?.copy(isLoading = false,
          success = "Fetched post successfully")
        post.postValue(response.body())
      }else{
        uiState.value = uiState.value?.copy(isLoading = false,
          error = response.errorBody()?.string())
      }
    }
  }
  
  fun fetchPostComments(postId: Int){
    viewModelScope.launch {
      uiState.value = uiState.value?.copy(isLoading = true)
      val response = postsRepository.fetchPostComments(postId)
      if(response.isSuccessful){
        uiState.value = uiState.value.copy(isLoading = false,
          success = "Fetched comments")
        comments.postValue(response.body())
      }else{
        uiState.value = uiState.value?.copy(isLoading = false,
          error = response.errorBody()?.string())
      }
    }
  }
  
}