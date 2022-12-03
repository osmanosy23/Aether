package com.example.aether

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.google.android.youtube.player.YouTubeBaseActivity
import com.google.android.youtube.player.YouTubeInitializationResult
import com.google.android.youtube.player.YouTubePlayer
import com.google.android.youtube.player.YouTubePlayerView

class NewYoutubeActivity : YouTubeBaseActivity(), YouTubePlayer.OnInitializedListener,
    YouTubePlayer.PlaybackEventListener,
    YouTubePlayer.PlayerStateChangeListener {
    val API_KEY: String = "AIzaSyBBsi3TEzPeDfw9NHkwOpNnynO-7YgFICs"
    val VIDEO_ID: String = "nA9UZF-SZoQ"
    private lateinit var youtubePlayer: YouTubePlayerView
    lateinit var youtubePlayerInit: YouTubePlayer.OnInitializedListener

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_youtube)
        val youtubePlayer: YouTubePlayerView = findViewById(R.id.playerview)
        val playBtn: Button = findViewById(R.id.btnPlay)



        youtubePlayerInit = object : YouTubePlayer.OnInitializedListener {
            override fun onInitializationSuccess(
                p0: YouTubePlayer.Provider?,
                p1: YouTubePlayer?,
                p2: Boolean
            ) {
                if (p1 != null) {
                    p1.loadVideo(VIDEO_ID)
                }

            }

            override fun onInitializationFailure(
                p0: YouTubePlayer.Provider?,
                p1: YouTubeInitializationResult?
            ) {
                Toast.makeText(applicationContext, "Failed", Toast.LENGTH_SHORT).show()
            }
        }
        playBtn.setOnClickListener {
            youtubePlayer.initialize(API_KEY, youtubePlayerInit)
        }
    }

    override fun onPlaying() {
    }

    override fun onPaused() {
    }

    override fun onStopped() {
    }

    override fun onBuffering(p0: Boolean) {
    }

    override fun onSeekTo(p0: Int) {
    }

    override fun onLoading() {
    }

    override fun onLoaded(p0: String?) {
    }

    override fun onAdStarted() {
    }

    override fun onVideoStarted() {
    }

    override fun onVideoEnded() {
    }

    override fun onError(p0: YouTubePlayer.ErrorReason?) {
    }

    override fun onInitializationSuccess(
        p0: YouTubePlayer.Provider?,
        p1: YouTubePlayer?,
        p2: Boolean
    ) {
    }

    override fun onInitializationFailure(
        p0: YouTubePlayer.Provider?,
        p1: YouTubeInitializationResult?
    ) {
    }
}