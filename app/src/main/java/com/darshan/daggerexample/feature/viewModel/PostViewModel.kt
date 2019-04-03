package com.darshan.daggerexample.feature.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.darshan.daggerexample.R
import com.darshan.daggerexample.api.Result
import com.darshan.daggerexample.response.Company
import com.darshan.daggerexample.response.User
import com.darshan.daggerexample.util.Event
import kotlinx.coroutines.launch
import javax.inject.Inject

class PostViewModel @Inject constructor(
  private val postRepository: PostRepository
) : ViewModel() {

  private val _shotUiModel = MutableLiveData<List<User>>()
  val shotUiModel: LiveData<List<User>>
    get() = _shotUiModel

  private val _uiState = MutableLiveData<PostListUiModel>()
  val uiState: LiveData<PostListUiModel>
    get() = _uiState

  fun getPostList() {
    viewModelScope.launch {
      showLoading()
      val result = postRepository.launchProductListScope()
      if (result is Result.Success) {
        val postList = result.data
        emitUiState(showSuccess = Event(PostListResultUiModel(postList)))
      } else {
        emitUiState(
          showError = Event(R.string.error),
          enableLoginButton = true
        )
      }
    }
  }

  fun displayCompnayList() {
    viewModelScope.launch {
      val result = postRepository.launchUserCompanyScope()
      if (result is Result.Success) {
        val companyList = result.data
        companyList.forEach {
          company: Company -> println("------------------------Company name is ${company.name}")
        }
      }
    }
  }

  private fun showLoading() {
    emitUiState(showProgress = true)
  }

  private fun emitUiState(
    showProgress: Boolean = false,
    showError: Event<Int>? = null,
    showSuccess: Event<PostListResultUiModel>? = null,
    enableLoginButton: Boolean = false
  ) {
    val uiModel = PostListUiModel(showProgress, showError, showSuccess, enableLoginButton)
    _uiState.value = uiModel
  }
}

/**
 * UI model for [PostListActivity]
 */
data class PostListUiModel(
  val showProgress: Boolean,
  val showError: Event<Int>?,
  val showSuccess: Event<PostListResultUiModel>?,
  val enableLoginButton: Boolean
)

/**
 * UI Model for postlist success
 */
data class PostListResultUiModel(
  val listPost: List<User>
)
