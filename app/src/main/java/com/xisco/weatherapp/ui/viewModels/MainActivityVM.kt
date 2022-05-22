package com.xisco.weatherapp.ui.viewModels

import androidx.lifecycle.ViewModel
import com.xisco.weatherapp.data.repository.UserRepository
import com.xisco.weatherapp.utils.DispatcherProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class MainActivityVM @Inject constructor(
            private val repository: UserRepository,
            private val dispatchers: DispatcherProvider
) : ViewModel() {

            sealed class GithubEvents {
                        class Success(val resultText: String) : GithubEvents()
                        class Failure(val resultText: String) : GithubEvents()
                        object Loading : GithubEvents()
                        object Empty : GithubEvents()
            }

            private val _userState = MutableStateFlow<GithubEvents>(GithubEvents.Empty)

            val userState: StateFlow<GithubEvents>
                        get() = _userState


}