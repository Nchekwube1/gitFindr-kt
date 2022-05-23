package com.xisco.weatherapp.ui

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.lifecycleScope
import com.xisco.weatherapp.R
import com.xisco.weatherapp.databinding.ActivityMainBinding
import com.xisco.weatherapp.ui.viewModels.MainActivityVM
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
            private val viewModel: MainActivityVM by viewModels()
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

                                    viewModel.getUser(currentUser)

                                    lifecycleScope.launchWhenStarted {
                                                viewModel.userState.collect{
                                                            event->
                                                            when(event){
                                                                        is MainActivityVM.GithubEvents.Loading ->{
                                                                                    mainActivityBinding.pbLoading.isVisible = true
                                                                                    mainActivityBinding.clUsetDetail.isVisible = false
                                                                                    Toast.makeText(
                                                                                                this@MainActivity,
                                                                                                "Loading state $event",

                                                                                                Toast.LENGTH_SHORT
                                                                                    ).show()

                                                                        }
                                                                        is MainActivityVM.GithubEvents.Success->{
                                                                                    mainActivityBinding.pbLoading.isVisible = false
                                                                                    mainActivityBinding.clUsetDetail.isVisible = true
                                                                                    mainActivityBinding.tvUserBio.text = event.resultValue.bio
                                                                                    Toast.makeText(
                                                                                                this@MainActivity,
                                                                                                "Success state $event",

                                                                                                Toast.LENGTH_SHORT
                                                                                    ).show()

                                                                        }
                                                                        is MainActivityVM.GithubEvents.Failure->{
                                                                                    mainActivityBinding.pbLoading.isVisible = false
                                                                                    mainActivityBinding.tvErrorText.isVisible =true
                                                                                    mainActivityBinding.tvErrorText.text =event.errorText
                                                                                    Toast.makeText(
                                                                                                this@MainActivity,
                                                                                                "Failed state ------->${event.errorText}",
                                                                                                Toast.LENGTH_SHORT
                                                                                    ).show()

                                                                        }
                                                                        else -> Unit
                                                            }
                                                }
                                    }


                        } else {
                                    Toast.makeText(
                                                this,
                                                "Please enter a valid username",
                                                Toast.LENGTH_LONG
                                    ).show()
                        }
            }

}