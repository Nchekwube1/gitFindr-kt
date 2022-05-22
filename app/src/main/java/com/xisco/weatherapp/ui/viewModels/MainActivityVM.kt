package com.xisco.weatherapp.ui.viewModels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.xisco.weatherapp.data.remote.dto.toGitUser
import com.xisco.weatherapp.data.repository.UserRepository
import com.xisco.weatherapp.domain.model.GitUser
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainActivityVM @Inject constructor(
            private val repository: UserRepository
) : ViewModel() {


            private val _state = MutableLiveData<GitUser>()

            val state: LiveData<GitUser>
                        get() = _state


            init {
            }


}