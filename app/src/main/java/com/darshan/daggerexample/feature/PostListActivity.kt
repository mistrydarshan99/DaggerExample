package com.darshan.daggerexample.feature

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.darshan.daggerexample.R
import com.darshan.daggerexample.api.ConnectivityChecker
import com.darshan.daggerexample.feature.viewModel.PostViewModel
import com.darshan.daggerexample.feature.viewModel.inject
import io.plaidapp.core.util.delegates.contentView
import javax.inject.Inject

class PostListActivity : AppCompatActivity() {

  @Inject
  internal lateinit var viewModel: PostViewModel

  @Inject
  internal lateinit var connectivityChecker: ConnectivityChecker?

  private val binding by contentView<PostListActivity, com.darshan.daggerexample.databinding.ActivityMainBinding>(
    R.layout.activity_main
  )

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    inject(this)

    binding.viewModel = viewModel.also { vm ->
      vm.shotUiModel.observe(this, Observer {
        println(it)
      })


      if (connectivityChecker != null) {
        lifecycle.addObserver(connectivityChecker!!)
        connectivityChecker!!.connectedStatus.observe(this, Observer {
          if (it) {

          } else {
            handleNoNetworkConnection()
          }

        })
      } else {
        handleNoNetworkConnection()
      }

    }
  }

  private fun handleNoNetworkConnection() {

  }
}
