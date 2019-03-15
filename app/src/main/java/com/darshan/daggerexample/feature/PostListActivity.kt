package com.darshan.daggerexample.feature

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.darshan.daggerexample.R
import com.darshan.daggerexample.feature.viewModel.PostViewModel
import com.darshan.daggerexample.feature.viewModel.inject
import io.plaidapp.core.util.delegates.contentView
import javax.inject.Inject

class PostListActivity : AppCompatActivity() {

  @Inject
  internal lateinit var viewModel: PostViewModel

  private val binding by contentView<PostListActivity, com.darshan.daggerexample.databinding.ActivityMainBinding>(
    R.layout.activity_main
  )

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    inject(this)
  }
}
