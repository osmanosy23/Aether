package com.example.aether

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import com.bumptech.glide.Glide
import getPicture

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val picofday : ImageView = findViewById(R.id.myImageView)

        getPicture(this) { myUrlArgument->
            Glide
                .with(this)
                .load(myUrlArgument)
                .centerCrop()
                .placeholder(R.drawable.telescope_vec)
                .into(picofday)
        }
    }
}