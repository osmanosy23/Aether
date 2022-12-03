package com.example.aether

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.google.android.material.bottomnavigation.BottomNavigationView
import getInfo

class MainActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val bottomNav: BottomNavigationView = findViewById(R.id.bottomNavigationView)

        supportFragmentManager.beginTransaction()
            .add(R.id.fragmentContainerView, HomeFragment.newInstance()).commit()
        bottomNav.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.home_item -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fragmentContainerView, HomeFragment.newInstance())
                        .commit()
                }
                R.id.telescope_item -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fragmentContainerView, ExploreFragment.newInstance())
                        .commit()
                }
                else -> {
                }
            }
            true
        }

    }
}