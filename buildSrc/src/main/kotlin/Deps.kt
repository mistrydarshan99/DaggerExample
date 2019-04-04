private const val daggerVersion = "2.19"
private const val kotlinVersion = "1.3.10"
private const val gradleVersion = "3.3.2"
private const val appCompactVersion = "1.1.0-alpha04"
private const val coreKtxVersion = "1.1.0-alpha05"
private const val constraintLayoutVersion = "2.0.0-alpha3"
private const val retrofit = "2.5.0"
private const val okhttp = "3.14.0"
private const val coroutine = "1.1.1"
private const val coroutineAdapterVersion = "0.9.2"
private const val gson = "2.8.5"
private const val lifecycle = "2.1.0-alpha04"
private const val material = "1.1.0-alpha05"

object Deps {

  object Android {
    val compileSdk = 28
    val minSdk = 23
    val targetSdk = 28
    val applicationId = "com.darshan.daggerexample"
    val versionCode = 1
    val versionName = "1.0"
  }

  object Libs {

    //AppCoreLibs
    val constraintLayout = "androidx.constraintlayout:constraintlayout:$constraintLayoutVersion"
    val androidXCore = "androidx.core:core-ktx:$coreKtxVersion"
    val appCompact = "androidx.appcompat:appcompat:$appCompactVersion"
    val kotlinStdLib = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:$kotlinVersion"

    //Material
    val materialComponet = "com.google.android.material:material:$material"

    //Dagger
    val dagger_compiler = "com.google.dagger:dagger-compiler:$daggerVersion"
    val dagger = "com.google.dagger:dagger:$daggerVersion"

    //Networking
    val retrofitLib = "com.squareup.retrofit2:retrofit:$retrofit"
    val retrofitConverter = "com.squareup.retrofit2:converter-gson:$retrofit"
    val okhttpLib = "com.squareup.okhttp3:okhttp:$okhttp"
    val okhttplogging = "com.squareup.okhttp3:logging-interceptor:$okhttp"
    val coroutineAdapter =
      "com.jakewharton.retrofit:retrofit2-kotlin-coroutines-adapter:$coroutineAdapterVersion"
    val gsonLib = "com.google.code.gson:gson:$gson"

    //Coroutine
    val coroutineLib = "org.jetbrains.kotlinx:kotlinx-coroutines-core:$coroutine"
    val androidCoroutine = "org.jetbrains.kotlinx:kotlinx-coroutines-android:$coroutine"

    //lifeCycle
    val lifeCycleExtension = "androidx.lifecycle:lifecycle-extensions:$lifecycle"
    val lifeCycleViewModel = "androidx.lifecycle:lifecycle-viewmodel-ktx:$lifecycle"
  }

  object BuildPlugins {
    val androidGradle = "com.android.tools.build:gradle:$gradleVersion"
    val kotlinGradlePlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlinVersion"
  }
}
