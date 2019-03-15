package com.darshan.daggerexample.component

import com.darshan.daggerexample.module.CoreDataModule
import com.darshan.daggerexample.module.SharedPreferencesModule
import dagger.Component

@Component(
  modules = [
    CoreDataModule::class,
    SharedPreferencesModule::class
  ]
)
interface CoreComponent {

  @Component.Builder
  interface Builder {
    fun build(): CoreComponent
    fun sharedPreferencesModuleModule(module: SharedPreferencesModule): Builder
  }
}
