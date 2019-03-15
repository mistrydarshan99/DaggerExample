package com.darshan.daggerexample.feature

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.darshan.daggerexample.R
import com.darshan.daggerexample.feature.viewModel.PostViewModel
import com.darshan.daggerexample.feature.viewModel.inject
import javax.inject.Inject

class PostListActivity : AppCompatActivity() {

  @Inject
  internal lateinit var viewModel: PostViewModel

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    inject(this)
  }
}
