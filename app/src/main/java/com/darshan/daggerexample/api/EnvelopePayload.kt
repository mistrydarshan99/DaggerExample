package com.darshan.daggerexample.api

import kotlin.annotation.AnnotationRetention.RUNTIME

@Target(AnnotationTarget.FUNCTION)
@Retention(RUNTIME)
annotation class EnvelopePayload(val value: String = "")
