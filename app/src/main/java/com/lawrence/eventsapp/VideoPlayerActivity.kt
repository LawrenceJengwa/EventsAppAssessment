 package com.lawrence.eventsapp

import android.R
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.SimpleExoPlayer
import com.lawrence.eventsapp.databinding.ActivityVideoPlayerBinding
import com.lawrence.eventsapp.util.DateUtil.Companion.VIDEO_URL_KEY


 class VideoPlayerActivity : AppCompatActivity() {

    private lateinit var binding: ActivityVideoPlayerBinding
    private lateinit var videoUrl: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityVideoPlayerBinding.inflate(layoutInflater)
        setContentView(binding.root)
        videoUrl = intent.extras?.getString(VIDEO_URL_KEY)!!
        prepareVideo(videoUrl)


    }

     private fun prepareVideo(url: String) {
         val simpleExoPlayer = ExoPlayer.Builder(this).build()
         binding.exoPlayerView.player = simpleExoPlayer
         val mediaItem: MediaItem = MediaItem.fromUri(url)
         simpleExoPlayer.addMediaItem(mediaItem)
         simpleExoPlayer.prepare()
         simpleExoPlayer.playWhenReady = true

     }
}