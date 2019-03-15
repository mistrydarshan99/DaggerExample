package com.darshan.daggerexample.feature.viewModel

import android.content.Context
import com.darshan.daggerexample.module.SharedPreferencesModule
import dagger.Module

@Module
class PostSharedPrefModule(context: Context) : SharedPreferencesModule(context, "Post")
