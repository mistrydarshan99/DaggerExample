package com.darshan.daggerexample.feature.viewModel

import com.darshan.daggerexample.api.Result
import com.darshan.daggerexample.base.CoroutinesDispatcherProvider
import com.darshan.daggerexample.response.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class PostRepository constructor(
  private val remoteDataSource: PostRemoteDataSource
) {

  suspend fun launchProductListScope(): Result<List<User>> = withContext(Dispatchers.IO) {
    remoteDataSource.getPostList()
  }

  companion object {
    @Volatile
    private var INSTANCE: PostRepository? = null

    fun getInstance(
      remoteDataSource: PostRemoteDataSource,
      dispatcherProvider: CoroutinesDispatcherProvider
    ): PostRepository {
      return INSTANCE ?: synchronized(this) {
        INSTANCE ?: PostRepository(remoteDataSource)
          .also { INSTANCE = it }
      }
    }
  }
}
