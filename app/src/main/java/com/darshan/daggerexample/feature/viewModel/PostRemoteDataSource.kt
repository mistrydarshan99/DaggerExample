package com.darshan.daggerexample.feature.viewModel

import com.darshan.daggerexample.api.Result
import com.darshan.daggerexample.api.WebServices
import com.darshan.daggerexample.response.Company
import com.darshan.daggerexample.response.User
import com.darshan.daggerexample.util.safeApiCall
import java.io.IOException
import javax.inject.Inject

class PostRemoteDataSource @Inject constructor(private val service: WebServices) {

  suspend fun getPostList() = safeApiCall(
    call = { requestPostList() },
    errorMessage = "Error getting post list"
  )

  suspend fun getCompanyList() = safeApiCall(
    call = { requestUserCompanyList() },
    errorMessage = "Error getting post list"
  )

  private suspend fun requestPostList(): Result<List<User>> {
    val response = service.getPostList().await()
    if (response.isSuccessful) {
      val body = response.body()
      if (body != null) {
        return Result.Success(body)
      }
    }
    return Result.Error(
      IOException("Error getting Dribbble data ${response.code()} ${response.message()}")
    )
  }

  private suspend fun requestUserCompanyList(): Result<List<Company>> {
    val response = service.getUserCompanyList().await()
    if (response.isSuccessful) {
      val body = response.body()
      if (body != null) {
        return Result.Success(body)
      }
    }
    return Result.Error(
      IOException("Error getting Dribbble data ${response.code()} ${response.message()}")
    )
  }
}
