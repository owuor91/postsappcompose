package dev.owuor91.postscompose

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.lifecycle.MutableLiveData
import androidx.test.ext.junit.runners.AndroidJUnit4
import dev.owuor91.postscompose.model.UIState
import dev.owuor91.postscompose.screens.PostsScreenImpl
import dev.owuor91.postscompose.viewmodel.PostsViewModel
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class PostsScreenTest {
  @get:Rule
  val composeTestRule = createComposeRule()
  
  lateinit var mockViewModel: PostsViewModel
  lateinit var mockOnClickPost: (Int) -> Unit
  
  @Before
  fun setup() {
    mockViewModel = mockk(relaxed = true)
    mockOnClickPost = mockk(relaxed = true)
  }
  
  @Test
  fun displaysProgressBarWhenLoading() {
    val uiState = UIState(isLoading = true)
    every { mockViewModel.uiState } returns MutableLiveData(uiState)
    every { mockViewModel.posts } returns MutableLiveData(emptyList())
    
    composeTestRule.setContent {
      PostsScreenImpl(
        onClickPost = mockOnClickPost,
        viewModel = mockViewModel
      )
    }
    
    composeTestRule.onNodeWithTag("progressBar")
      .assertExists()
      .assertIsDisplayed()
    
    verify { mockViewModel.fetchPosts() }
  }
  
  @Test
  fun testPostsListIsDisplayed() {
    val uiState = UIState(isLoading = false, success = "Fetched posts successfully")
    every { mockViewModel.posts } returns MutableLiveData(TestData.mockPosts)
    every { mockViewModel.uiState } returns MutableLiveData(uiState)
    
    composeTestRule.setContent {
      PostsScreenImpl(
        onClickPost = mockOnClickPost,
        viewModel = mockViewModel
      )
    }
    
    composeTestRule.onNodeWithTag("postsList")
      .assertExists()
      .assertIsDisplayed()
    
    composeTestRule.onNodeWithText("Title 1")
      .assertExists()
      .assertIsDisplayed()
  }
  
  @Test
  fun testDisplaysErrorMessage() {
    val uiState = UIState(error = "Failed to fetch posts", isLoading = false)
    every { mockViewModel.uiState } returns MutableLiveData(uiState)
    
    composeTestRule.setContent {
      PostsScreenImpl(onClickPost = mockOnClickPost, viewModel = mockViewModel)
    }
    
    composeTestRule.onNodeWithText("Failed to fetch posts")
      .assertExists()
      .assertIsDisplayed()
    
  }
  
  @Test
  fun testOnClickPostInvokesCallback() {
    val uiState = UIState(success = "Fetched posts successfully", isLoading = false)
    every { mockViewModel.uiState } returns MutableLiveData(uiState)
    every { mockViewModel.posts } returns MutableLiveData(listOf(TestData.mockPost))
    
    composeTestRule.setContent {
      PostsScreenImpl(
        onClickPost = mockOnClickPost,
        viewModel = mockViewModel
      )
    }
    
    composeTestRule.onNodeWithTag("clickablePost").performClick()
    
    verify { mockOnClickPost(1) }
  }
}