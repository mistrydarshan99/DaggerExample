package com.darshan.daggerexample.feature

import android.content.SharedPreferences
import android.graphics.drawable.AnimatedVectorDrawable
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.edit
import androidx.lifecycle.Observer
import com.darshan.daggerexample.R
import com.darshan.daggerexample.api.ConnectivityChecker
import com.darshan.daggerexample.feature.viewModel.PostListResultUiModel
import com.darshan.daggerexample.feature.viewModel.PostViewModel
import com.darshan.daggerexample.feature.viewModel.inject
import com.darshan.daggerexample.response.User
import io.plaidapp.core.util.delegates.contentView
import kotlinx.android.synthetic.main.activity_main.progressBar
import kotlinx.android.synthetic.main.activity_main.stub_no_connection
import kotlinx.android.synthetic.main.activity_main.tvTitle
import javax.inject.Inject

class PostListActivity : AppCompatActivity() {

  private var noConnection: ImageView? = null

  @Inject
  internal lateinit var viewModel: PostViewModel

  @set:Inject
  var connectivityChecker: ConnectivityChecker? = null

  @Inject
  internal lateinit var sharePreferences: SharedPreferences

  private val binding by contentView<PostListActivity, com.darshan.daggerexample.databinding.ActivityMainBinding>(
    R.layout.activity_main
  )

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    inject(this)

    sharePreferences.edit { putString(KEY_ID, "10") }

    binding.viewModel = viewModel.also { vm ->
      vm.shotUiModel.observe(this, Observer {
        for (user in it) {
          println("-------------------------------${user.id}")
        }
      })

      vm.uiState.observe(this, Observer {
        val uiModel = it ?: return@Observer

        if (uiModel.showProgress) {
          println("------------------------------------Progress")
          progressBar.visibility = View.VISIBLE
        }

        if (uiModel.showError != null && !uiModel.showError.consumed) {
          progressBar.visibility = View.GONE
          uiModel.showError.consume()?.let {
            println("------------------------------------Fail")
          }
        }
        if (uiModel.showSuccess != null && !uiModel.showSuccess.consumed) {
          progressBar.visibility = View.GONE
          uiModel.showSuccess.consume()?.let {
            val postResultUiModel: PostListResultUiModel = it
            postResultUiModel.listPost.forEach { user: User ->
              println("----------------------ID${user.id}")
            }
          }
        }
      })
    }

    if (connectivityChecker != null) {
      lifecycle.addObserver(connectivityChecker!!)
      connectivityChecker!!.connectedStatus.observe(this, Observer {
        if (it) {
          tvTitle.visibility = View.VISIBLE
          tvTitle.text = sharePreferences.getString(KEY_ID, "")
          if (noConnection != null) {
            noConnection!!.visibility = View.GONE
          }
        } else {
          handleNoNetworkConnection()
        }

      })
    } else {
      handleNoNetworkConnection()
    }
  }

  private fun handleNoNetworkConnection() {
    tvTitle.visibility = View.GONE
    if (noConnection == null) {
      noConnection = stub_no_connection.inflate() as ImageView
    }
    val avd = getDrawable(R.drawable.avd_no_connection) as AnimatedVectorDrawable
    if (noConnection != null) {
      noConnection!!.setImageDrawable(avd)
      avd.start()
    }
  }

  companion object {

    private const val KEY_ID = "KEY_ID"
  }
}
