package com.xisco.weatherapp.ui.viewModels

import com.xisco.weatherapp.domain.model.GitUser

data class MainActivityState(
            val isLoading:Boolean = false,
            val userState : GitUser? = null,
            val  error: String = ""
)
