package com.darshan.daggerexample.feature.viewModel

import android.content.Context
import androidx.lifecycle.ViewModelProviders
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

  fun postViewModelFactory(
    postRepository: PostRepository,
    dispatcherProvider: CoroutinesDispatcherProvider
  ): PostViewModelFactory {
    return PostViewModelFactory(postRepository, dispatcherProvider)
  }
}
