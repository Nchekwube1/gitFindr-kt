package com.xisco.weatherapp.ui.viewModels

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.xisco.weatherapp.data.remote.dto.GitUserDto
import com.xisco.weatherapp.data.repository.UserRepository
import com.xisco.weatherapp.utils.DispatcherProvider
import com.xisco.weatherapp.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainActivityVM @Inject constructor(
            private val repository: UserRepository,
            private val dispatchers: DispatcherProvider
) : ViewModel() {

            sealed class GithubEvents {
                        class Success(val resultValue: GitUserDto) : GithubEvents()
                        class Failure(val errorText: String) : GithubEvents()
                        object Loading : GithubEvents()
                        object Empty : GithubEvents()
            }

            private val _userState = MutableStateFlow<GithubEvents>(GithubEvents.Empty)
            val userState: StateFlow<GithubEvents>
                        get() = _userState

            fun getUser(username: String) {
                        viewModelScope.launch(dispatchers.io) {
                                    _userState.value = GithubEvents.Loading

                                    when (val githubResponse = repository.getUser(username)) {

                                                is Resource.Error -> {
                                                            Log.d(
                                                                        "MainActivityVm",
                                                                        githubResponse.toString()
                                                            )
                                                            _userState.value = GithubEvents.Failure(
                                                                        githubResponse.message
                                                                                    ?: "An error occurred"
                                                            )
                                                }
                                                is Resource.Success -> {
                                                            val userDetails = githubResponse.data!!
                                                            _userState.value = GithubEvents.Success(
                                                                        userDetails
                                                            )
                                                }
                                    }
                        }
            }


}