package com.darshan.daggerexample.feature.viewModel

import com.darshan.daggerexample.base.BaseActivityComponent
import com.darshan.daggerexample.component.CoreComponent
import com.darshan.daggerexample.feature.PostListActivity
import com.darshan.daggerexample.module.CoreDataModule
import com.darshan.daggerexample.module.PostDataModule
import dagger.Component

/**
 * Dagger component for [PostListActivity].
 */
@Component(
  modules = [PostModule::class, PostDataModule::class],
  dependencies = [CoreComponent::class]
)
interface PostComponent : BaseActivityComponent<PostListActivity> {

  @Component.Builder
  interface Builder {
    fun build(): PostComponent
    fun coreComponent(coreComponent: CoreComponent): Builder
    fun coreDataModule(module: CoreDataModule): Builder
    fun postModule(module: PostModule): Builder
  }
}
