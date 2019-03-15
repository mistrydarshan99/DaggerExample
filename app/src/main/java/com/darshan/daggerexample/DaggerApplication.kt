package com.darshan.daggerexample

import android.app.Activity
import android.app.Application
import android.content.Context
import com.darshan.daggerexample.component.CoreComponent
import com.darshan.daggerexample.component.DaggerCoreComponent

class DaggerApplication : Application() {

  private val coreComponent: CoreComponent by lazy {
    DaggerCoreComponent.builder().build()
  }

  companion object {
    @JvmStatic
    fun coreComponent(context: Context) =
      (context.applicationContext as DaggerApplication).coreComponent
  }

  fun Activity.coreComponent() = DaggerApplication.coreComponent(this)
}
