package com.darshan.daggerexample.feature.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.darshan.daggerexample.api.Result
import com.darshan.daggerexample.base.CoroutinesDispatcherProvider
import com.darshan.daggerexample.response.User
import com.darshan.daggerexample.util.Event
import javax.inject.Inject

class PostViewModel @Inject constructor(
  postRepository: PostRepository,
  private val dispatcherProvider: CoroutinesDispatcherProvider
) : ViewModel() {

  private val _shotUiModel = MutableLiveData<List<User>>()
  val shotUiModel: LiveData<List<User>>
    get() = _shotUiModel

  private val _uiState = MutableLiveData<PostListUiModel>()
  val uiState: LiveData<PostListUiModel>
    get() = _uiState

  init {
    val result = postRepository.launchProductList { result ->
      if (result is Result.Success<*>) {
        _shotUiModel.value = (result as Result.Success<List<User>>).data
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
