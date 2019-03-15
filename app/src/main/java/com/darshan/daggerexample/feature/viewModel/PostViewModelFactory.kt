package com.darshan.daggerexample.feature.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.darshan.daggerexample.base.CoroutinesDispatcherProvider
import javax.inject.Inject

class PostViewModelFactory @Inject constructor(
  private val postRepository: PostRepository,
  private val dispatcherProvider: CoroutinesDispatcherProvider
) : ViewModelProvider.Factory {

  @Suppress("UNCHECKED_CAST")
  override fun <T : ViewModel?> create(modelClass: Class<T>): T {
    if (modelClass != PostViewModel::class.java) {
      throw IllegalArgumentException("Unknown ViewModel class")
    }

    return PostViewModel(postRepository, dispatcherProvider) as T
  }

}
