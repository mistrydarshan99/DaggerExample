package com.darshan.daggerexample.feature.viewModel

import com.darshan.daggerexample.api.Result
import com.darshan.daggerexample.base.CoroutinesDispatcherProvider
import com.darshan.daggerexample.response.User
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class PostRepository constructor(
  private val remoteDataSource: PostRemoteDataSource,
  private val dispatcherProvider: CoroutinesDispatcherProvider
) {
  private val parentJob = Job()
  private val scope = CoroutineScope(dispatcherProvider.main + parentJob)

  private val shotCache = mutableMapOf<Long, User>()

  fun launchProductList(onResult: (Result<List<User>>) -> Unit) =
    scope.launch(dispatcherProvider.io) {
      val result = remoteDataSource.getPostList()
      if (result is Result.Success) {
        cache(result.data)
      }
      withContext(dispatcherProvider.main) { onResult(result) }
    }

  suspend fun launchProductListScope(): Result<List<User>> = withContext(Dispatchers.IO) {
    remoteDataSource.getPostList()
  }

  private fun cache(shots: List<User>) {
    shots.associateTo(shotCache) { it.id to it }
  }

  companion object {
    @Volatile
    private var INSTANCE: PostRepository? = null

    fun getInstance(
      remoteDataSource: PostRemoteDataSource,
      dispatcherProvider: CoroutinesDispatcherProvider
    ): PostRepository {
      return INSTANCE ?: synchronized(this) {
        INSTANCE ?: PostRepository(remoteDataSource, dispatcherProvider)
          .also { INSTANCE = it }
      }
    }
  }
}
