package com.darshan.daggerexample.feature.viewModel

import com.darshan.daggerexample.coreComponent
import com.darshan.daggerexample.feature.PostListActivity

fun inject(activity: PostListActivity) {

  DaggerPostComponent.builder()
    .coreComponent(activity.coreComponent())
    .postModule(PostModule(activity))
    .build()
    .inject(activity)

}
