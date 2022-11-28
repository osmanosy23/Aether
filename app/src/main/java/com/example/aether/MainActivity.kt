package com.example.aether

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.google.android.material.bottomnavigation.BottomNavigationView
import getInfo

class MainActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        val picofday : ImageView = findViewById(R.id.myImageView)
//        val pictitle : TextView = findViewById(R.id.titleTextView)
        val bottomNav : BottomNavigationView = findViewById(R.id.bottomNavigationView)
//        getPicture(this) { myUrlArgument->
//            Glide
//                .with(this)
//                .load(myUrlArgument)
//                .centerCrop()
//                .placeholder(R.drawable.telescope_vec)
//                .into(picofday)
//        }

//        getInfo(this) {myUrlArgument ->
//            pictitle.text = myUrlArgument
//        }
        bottomNav.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.home_item-> {
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