package com.xisco.weatherapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.xisco.weatherapp.R
import com.xisco.weatherapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
            private  lateinit var mainActivityBinding:ActivityMainBinding
            override fun onCreate(savedInstanceState: Bundle?) {
                        super.onCreate(savedInstanceState)
                         mainActivityBinding = DataBindingUtil.setContentView(this,R.layout.activity_main)
            }
}