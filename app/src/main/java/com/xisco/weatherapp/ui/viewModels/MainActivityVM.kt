package com.xisco.weatherapp.ui.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.xisco.weatherapp.domain.use_cases.get_user.GetUserUseCase
import com.xisco.weatherapp.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class MainActivityVM @Inject constructor(
            private val getUserUseCase: GetUserUseCase
) : ViewModel() {
            private val _state = MutableLiveData(MainActivityState())
            val state: LiveData<MainActivityState>
                        get() = _state


             fun getUser(username: String) {
                        getUserUseCase(username).onEach { result ->
                                    when (result) {
                                                is Resource.Success -> {
                                                            _state.value = MainActivityState(userState = result.data)
                                                }
                                                is Resource.Loading ->{
                                                            _state.value = MainActivityState(isLoading = true)
                                                }
                                                is Resource.Error ->{
                                                            _state.value = MainActivityState(
                                                                        error = result.message ?: "An unexpected error occurred"
                                                            )
                                                }
                                    }
                        }
            }
}