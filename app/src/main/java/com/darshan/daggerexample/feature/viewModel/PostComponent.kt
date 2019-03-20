package com.darshan.daggerexample.feature.viewModel

import com.darshan.daggerexample.base.BaseActivityComponent
import com.darshan.daggerexample.component.CoreComponent
import com.darshan.daggerexample.feature.PostListActivity
import com.darshan.daggerexample.module.PostDataModule
import com.darshan.daggerexample.module.SharedPreferencesModule
import com.darshan.daggerexample.scope.FeatureScope
import dagger.Component

/**
 * Dagger component for [PostListActivity].
 */
@Component(
  modules = [PostModule::class, PostDataModule::class, SharedPreferencesModule::class],
  dependencies = [CoreComponent::class]
)
@FeatureScope
interface PostComponent : BaseActivityComponent<PostListActivity> {

  @Component.Builder
  interface Builder {
    fun build(): PostComponent
    fun coreComponent(coreComponent: CoreComponent): Builder
    fun postModule(module: PostModule): Builder
    fun sharePrefModule(module: SharedPreferencesModule) : Builder
  }
}
