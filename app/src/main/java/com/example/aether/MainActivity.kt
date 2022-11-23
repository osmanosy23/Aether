package com.example.aether

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import getInfo
import getPicture

class MainActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val picofday : ImageView = findViewById(R.id.myImageView)
        val pictitle : TextView = findViewById(R.id.titleTextView)

        getPicture(this) { myUrlArgument->
            Glide
                .with(this)
                .load(myUrlArgument)
                .centerCrop()
                .placeholder(R.drawable.telescope_vec)
                .into(picofday)
        }

        getInfo(this) {myUrlArgument ->
            pictitle.text = myUrlArgument
        }
    }
}