package com.darshan.daggerexample.feature.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.darshan.daggerexample.api.Result
import com.darshan.daggerexample.base.CoroutinesDispatcherProvider
import com.darshan.daggerexample.response.User
import javax.inject.Inject

class PostViewModel @Inject constructor(
  postRepository: PostRepository,
  private val dispatcherProvider: CoroutinesDispatcherProvider
) : ViewModel() {

  private val _shotUiModel = MutableLiveData<List<User>>()
  val shotUiModel: LiveData<List<User>>
    get() = _shotUiModel

  init {
    val result = postRepository.launchProductList { result ->
      if (result is Result.Success<*>) {
        _shotUiModel.value = (result as Result.Success<List<User>>).data
      }
    }

  }
}
