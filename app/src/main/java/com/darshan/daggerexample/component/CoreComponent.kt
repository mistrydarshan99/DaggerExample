package com.darshan.daggerexample.component

import com.darshan.daggerexample.module.CoreDataModule
import com.google.gson.Gson
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import dagger.Component
import okhttp3.OkHttpClient
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Component(
  modules = [
    CoreDataModule::class
  ]
)
@Singleton
interface CoreComponent {

  fun provideOkHttpClient(): OkHttpClient
  fun provideGson(): Gson
  fun provideGsonConverterFactory(): GsonConverterFactory
  fun provideCallAdapterFactory(): CoroutineCallAdapterFactory

  @Component.Builder
  interface Builder {
    fun build(): CoreComponent
  }
}
