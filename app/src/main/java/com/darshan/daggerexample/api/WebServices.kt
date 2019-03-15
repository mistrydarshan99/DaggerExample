package com.darshan.daggerexample.api

import com.darshan.daggerexample.response.User
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET

interface WebServices {

  @GET("posts")
  fun getPostList(): Deferred<Response<List<User>>>

  companion object {
    const val ENDPOINT = "https://jsonplaceholder.typicode.com/"
  }
}
