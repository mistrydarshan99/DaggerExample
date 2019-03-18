package com.darshan.daggerexample.feature.viewModel

import android.content.Context
import android.net.ConnectivityManager
import androidx.core.content.getSystemService
import androidx.lifecycle.ViewModelProviders
import com.darshan.daggerexample.api.ConnectivityChecker
import com.darshan.daggerexample.base.CoroutinesDispatcherProvider
import com.darshan.daggerexample.feature.PostListActivity
import com.darshan.daggerexample.module.PostDataModule
import dagger.Module
import dagger.Provides

@Module(
  includes = [PostDataModule::class]
)
class PostModule(private val activity: PostListActivity) {

  @Provides
  fun provideContext(): Context = activity

  @Provides
  fun shotViewModel(factory: PostViewModelFactory): PostViewModel {
    return ViewModelProviders.of(activity, factory).get(PostViewModel::class.java)
  }

  @Provides
  fun postViewModelFactory(
    postRepository: PostRepository,
    dispatcherProvider: CoroutinesDispatcherProvider
  ): PostViewModelFactory {
    return PostViewModelFactory(postRepository, dispatcherProvider)
  }

  @Provides
  fun connectivityChecker(): ConnectivityChecker? {
    val connectivityManager = activity.getSystemService<ConnectivityManager>()
    return if (connectivityManager != null) {
      ConnectivityChecker(connectivityManager)
    } else {
      null
    }
  }
}
