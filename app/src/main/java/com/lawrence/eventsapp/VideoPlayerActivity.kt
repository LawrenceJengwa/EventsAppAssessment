 package com.lawrence.eventsapp

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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
         binding.videoView.apply {
             setVideoURI(Uri.parse(url))
             requestFocus()
             start()
         }

     }
}