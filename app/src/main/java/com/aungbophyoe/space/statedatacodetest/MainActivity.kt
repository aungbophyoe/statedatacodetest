package com.aungbophyoe.space.statedatacodetest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.aungbophyoe.space.statedatacodetest.databinding.ActivityMainBinding

/*
* 5.9.2022
* created by aungbophyoe
* */
class MainActivity : AppCompatActivity() {
    private var _binding : ActivityMainBinding? = null
    private val binding get() = _binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        val view = _binding!!.root
        setContentView(view)
        binding!!.apply {
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}