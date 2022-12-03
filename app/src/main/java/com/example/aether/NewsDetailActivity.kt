package com.example.aether

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle
import java.util.*

class NewsDetailActivity : AppCompatActivity() {
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news_detail)


        val title = intent.extras?.getString("title")
        val author= intent.extras?.getString("author")
        val date = intent.extras?.getString("date")
        val description = intent.extras?.getString("description")
        val imageurl = intent.extras?.getString("image")
        val url = intent.extras?.getString("url")


        val curTitle : TextView = findViewById(R.id.detailNewsTitle)
        val curAuthor : TextView = findViewById(R.id.detailNewsAuthor)
        val curDate : TextView = findViewById(R.id.detailNewsDate)
        val curDescription : TextView = findViewById(R.id.detailNewsDescText)
        val curImage : ImageView = findViewById(R.id.detailNewsImage)
        val button : Button = findViewById(R.id.detailNewsButton)

        val zoneId = ZoneId.of("US/Eastern")
        val instant = (Instant.parse(date))
        val locale = Locale.ENGLISH
        val zdt = instant.atZone(zoneId)
        val format = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.FULL).withLocale(locale)
        curTitle.text=title
        curAuthor.text= "Author: $author"
        curDate.text= zdt.format(format)
        curDescription.text=description

        try {
            Glide
                .with(this)
                .load(imageurl)
                .centerCrop()
                .placeholder(R.drawable.telescope_vec)
                .into(curImage)
        }
            catch (_ : Exception) {}

        button.setOnClickListener {
            goLink(url)
        }

    }

    private fun goLink(s: String?) {
        val url = Uri.parse(s)
        startActivity(Intent(Intent.ACTION_VIEW,url))
    }
}