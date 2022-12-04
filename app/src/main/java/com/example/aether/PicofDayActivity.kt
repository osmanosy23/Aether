package com.example.aether

import android.app.Activity
import android.media.Image
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import getInfo
import getPicture

class PicofDayActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_picof_day)

        val title = intent.extras?.getString("title")

        val curTitle: TextView = findViewById(R.id.podTextView)
        val curExp: TextView = findViewById(R.id.poddescTextView)
        val curImage: ImageView = findViewById(R.id.podImageView)
        val potText : TextView = findViewById(R.id.potentialTextView)

        curTitle.text = title

        getInfo(Activity(), "explanation") { myUrlArgument ->
            curExp.text = myUrlArgument
        }
        getPicture(Activity()) { myUrlArgument ->

            try {
                Glide
                    .with(this)
                    .load(myUrlArgument)
                    .centerCrop()
                    .placeholder(R.drawable.telescope_vec)
                    .into(curImage)


            } catch (_: Exception) {
                potText.text = myUrlArgument
            }
        }

        if(curTitle.text.toString().subSequence(0,5) == "Video"){
            getInfo(Activity(), "url") { myUrlArgument ->
                potText.text = myUrlArgument
            }
        }else{}
    }
}