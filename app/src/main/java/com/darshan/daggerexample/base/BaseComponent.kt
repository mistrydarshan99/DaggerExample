package com.darshan.daggerexample.base

import android.app.Activity
import android.app.Service

interface BaseComponent<T> {

  fun inject(target: T)
}

/**
 * Base dagger component for use in activities.
 */
interface BaseActivityComponent<T : Activity> : BaseComponent<T>

/**
 * Base dagger components for use in services.
 */
interface BaseServiceComponent<T : Service> : BaseComponent<T>
