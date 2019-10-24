package com.example.yamlapp.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.ui.core.setContent
import com.example.yamlapp.data.getPostsWithImagesLoaded
import com.example.yamlapp.data.posts

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        posts = getPostsWithImagesLoaded(
            posts,
            resources
        )
        setContent {
            JetnewsApp()
        }
    }
}
