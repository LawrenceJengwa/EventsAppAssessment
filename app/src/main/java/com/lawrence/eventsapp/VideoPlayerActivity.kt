package com.lawrence.eventsapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import com.lawrence.eventsapp.databinding.ActivityVideoPlayerBinding
import com.lawrence.eventsapp.util.DateUtil.Companion.VIDEO_URL_KEY


class VideoPlayerActivity : AppCompatActivity() {

    private lateinit var binding: ActivityVideoPlayerBinding
    private lateinit var videoUrl: String
    private lateinit var exoPlayer: ExoPlayer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityVideoPlayerBinding.inflate(layoutInflater)
        setContentView(binding.root)
        exoPlayer = ExoPlayer.Builder(this).build()
        videoUrl = intent.extras?.getString(VIDEO_URL_KEY)!!
        prepareVideo(videoUrl)
    }

    private fun prepareVideo(url: String) {
        binding.exoPlayerView.player = exoPlayer
        val mediaItem: MediaItem = MediaItem.fromUri(url)
        exoPlayer.addMediaItem(mediaItem)
        exoPlayer.prepare()
        exoPlayer.playWhenReady = true
    }

    override fun onBackPressed() {
        super.onBackPressed()
        if (exoPlayer.isPlaying) {
            exoPlayer.stop()
            exoPlayer.release()
        }
    }
}