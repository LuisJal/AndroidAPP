package com.example.actividad2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Window
import com.example.actividad2.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var binding = ActivityMainBinding.inflate(layoutInflater)
        var view = binding.root
        setContentView(view)
        getSupportActionBar()?.hide()

        supportFragmentManager.beginTransaction().replace(R.id.Container,InicioFragment()).commit()

    }
}