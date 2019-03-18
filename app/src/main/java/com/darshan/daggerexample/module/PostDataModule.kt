package com.darshan.daggerexample.module

import com.darshan.daggerexample.api.WebServices
import com.darshan.daggerexample.base.CoroutinesDispatcherProvider
import com.darshan.daggerexample.feature.viewModel.PostRemoteDataSource
import com.darshan.daggerexample.feature.viewModel.PostRepository
import com.google.gson.Gson
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import dagger.Lazy
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
class PostDataModule {

  @Provides
  fun provideShotsRepository(
    remoteDataSource: PostRemoteDataSource,
    dispatcherProvider: CoroutinesDispatcherProvider
  ) = PostRepository.getInstance(remoteDataSource, dispatcherProvider)

  @Provides
  fun provideApiService(
    client: Lazy<OkHttpClient>,
    callAdapterFactory: CoroutineCallAdapterFactory,
    gson: Gson
  ): WebServices =
    Retrofit.Builder()
      .baseUrl(WebServices.ENDPOINT)
      .callFactory { client.get().newCall(it) }
      .addCallAdapterFactory(callAdapterFactory)
      .addConverterFactory(GsonConverterFactory.create(gson))
      .build()
      .create(WebServices::class.java)
}
