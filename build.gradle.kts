// Top-level build file where you can add configuration options common to all sub-projects/modules.

buildscript {

  repositories {
    google()
    jcenter()

  }
  dependencies {
    classpath(Deps.BuildPlugins.kotlinGradlePlugin)
    classpath(Deps.BuildPlugins.androidGradle)
  }
}

allprojects {
  repositories {
    google()
    jcenter()
  }
}


