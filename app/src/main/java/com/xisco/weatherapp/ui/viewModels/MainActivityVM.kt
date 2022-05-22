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
//            private val getUserUseCase: GetUserUseCase
            private val repositoryImpl: UserRepository
) : ViewModel() {


//            private val _state = MutableLiveData(MainActivityState())
            private val _state = MutableLiveData<GitUser>()

//            val state: LiveData<MainActivityState>
            val state: LiveData<GitUser>
                        get() = _state


            init {
                        getUser("Nchekwube1")
            }

            fun getUser(username: String) {
                        viewModelScope.launch {
//                     getUserUseCase(username).onEach { result ->
//                                 when (result) {
//                                             is Resource.Success -> {
//                                                         _state.value = MainActivityState(userState = result.data)
//                                             }
//                                             is Resource.Loading ->{
//                                                         _state.value = MainActivityState(isLoading = true)
//                                             }
//                                             is Resource.Error ->{
//                                                         _state.value = MainActivityState(
//                                                                     error = result.message ?: "An unexpected error occurred"
//                                                         )
//                                             }
//                                 }
//                     }
                                    repositoryImpl.getUser(username).let{
                                               response ->  if(response.isSuccessful){
                                                           _state.value = response.body()!!.toGitUser()
                                    }else{
                                                Log.d("TAG", "getUser: An error with code ${response.code() } occurred")
                                    }
                                    }
                        }
            }
}