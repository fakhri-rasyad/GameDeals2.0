package com.d121211017.gamedealsnew.ui

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.d121211017.gamedealsnew.R
import com.d121211017.gamedealsnew.databinding.ActivityFilterBinding

class FilterActivity : AppCompatActivity() {
    private var _binding : ActivityFilterBinding? = null
    private val binding get() = _binding!!
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityFilterBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}