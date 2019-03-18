package com.darshan.daggerexample.feature.viewModel

import com.darshan.daggerexample.coreComponent
import com.darshan.daggerexample.feature.PostListActivity
import com.darshan.daggerexample.module.SharedPreferencesModule

fun inject(activity: PostListActivity) {

  DaggerPostComponent.builder()
    .coreComponent(activity.coreComponent())
    .postModule(PostModule(activity))
    .sharePrefModule(SharedPreferencesModule(activity, "PostDemo"))
    .build()
    .inject(activity)

}
