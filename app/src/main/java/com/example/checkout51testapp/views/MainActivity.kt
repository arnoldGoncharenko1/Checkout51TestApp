package com.example.checkout51testapp.views

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil.setContentView
import com.example.checkout51testapp.R
import com.example.checkout51testapp.databinding.MainActivityBinding
import dagger.hilt.android.AndroidEntryPoint

/**
 * Main activity, used to store the offers fragment.
 */
@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView<MainActivityBinding>(this, R.layout.main_activity)
    }
}