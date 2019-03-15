plugins {
  id("com.android.application")
  id("kotlin-android")
  id("kotlin-android-extensions")
  id("kotlin-kapt")
}


android {
  compileSdkVersion(Deps.Android.compileSdk)
  defaultConfig {
    applicationId = Deps.Android.applicationId
    minSdkVersion(Deps.Android.minSdk)
    targetSdkVersion(Deps.Android.targetSdk)
    versionCode = Deps.Android.versionCode
    versionName = Deps.Android.versionName
  }
  buildTypes {
    named("release") {
      isMinifyEnabled = false
      proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
    }
  }
  kapt {
    generateStubs = true
  }
}

dependencies {
  implementation(Deps.Libs.kotlinStdLib)
  implementation(Deps.Libs.androidXCore)
  implementation(Deps.Libs.appCompact)
  implementation(Deps.Libs.constraintLayout)
  implementation(Deps.Libs.dagger)
  kapt(Deps.Libs.dagger_compiler)

  implementation(Deps.Libs.retrofitLib)
  implementation(Deps.Libs.retrofitConverter)
  implementation(Deps.Libs.okhttpLib)
  implementation(Deps.Libs.okhttplogging)
  implementation(Deps.Libs.gsonLib)
  implementation(Deps.Libs.coroutineLib)
  implementation(Deps.Libs.coroutineAdapter)
  implementation(Deps.Libs.androidCoroutine)

  implementation(Deps.Libs.lifeCycleExtension)
  implementation(Deps.Libs.lifeCycleViewModel)

}
