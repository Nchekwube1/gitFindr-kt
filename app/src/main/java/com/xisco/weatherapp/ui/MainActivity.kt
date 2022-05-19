package com.xisco.weatherapp.ui

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import com.xisco.weatherapp.R
import com.xisco.weatherapp.databinding.ActivityMainBinding
import com.xisco.weatherapp.ui.viewModels.MainActivityVM

class MainActivity : AppCompatActivity() {
            private val viewModel by viewModels<MainActivityVM>()
            private lateinit var mainActivityBinding: ActivityMainBinding
            override fun onCreate(savedInstanceState: Bundle?) {
                        super.onCreate(savedInstanceState)
                        mainActivityBinding =
                                    DataBindingUtil.setContentView(this, R.layout.activity_main)
                        mainActivityBinding.btnSearch.setOnClickListener { searchGithubUser() }
            }

            private fun searchGithubUser() {
                        val inputText = mainActivityBinding.etUsername
                        val currentUser = inputText.text.toString()
                        val view = this.currentFocus
                        if (view != null) {
                                    val imm =
                                                getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                                    imm.hideSoftInputFromWindow(view.windowToken, 0)
                        }


                        if (currentUser.isNotEmpty()) {
                                    mainActivityBinding.pbLoading.visibility = View.VISIBLE

//viewModel.getUser(currentUser)
                        } else {
                                    Toast.makeText(
                                                this,
                                                "Please enter a valid username",
                                                Toast.LENGTH_LONG
                                    ).show()
                        }
            }

}