package com.example.yamlapp.ui.home

import androidx.compose.Composable
import androidx.compose.unaryPlus
import androidx.ui.core.Opacity
import androidx.ui.core.Text
import androidx.ui.core.dp
import androidx.ui.foundation.Clickable
import androidx.ui.foundation.HorizontalScroller
import androidx.ui.foundation.VerticalScroller
import androidx.ui.layout.Column
import androidx.ui.layout.FlexColumn
import androidx.ui.layout.Padding
import androidx.ui.layout.Row
import androidx.ui.layout.Spacing
import androidx.ui.layout.WidthSpacer
import androidx.ui.material.Divider
import androidx.ui.material.TopAppBar
import androidx.ui.material.ripple.Ripple
import androidx.ui.material.themeTextStyle
import androidx.ui.material.withOpacity
import com.example.yamlapp.R
import com.example.yamlapp.data.posts
import com.example.yamlapp.model.Post
import com.example.yamlapp.ui.Screen
import com.example.yamlapp.ui.VectorImageButton
import com.example.yamlapp.ui.navigateTo

@Composable
fun HomeScreen(openDrawer: () -> Unit) {
    val postTop = posts[3]
    val postsSimple = posts.subList(0, 2)
    val postsPopular = posts.subList(2, 7)
    val postsHistory = posts.subList(7, 10)

    FlexColumn {
        inflexible {
            TopAppBar(
                title = { Text(text = "Jetnews") },
                navigationIcon = {
                    VectorImageButton(R.drawable.ic_jetnews_logo) {
                        openDrawer()
                    }
                }
            )
        }
        flexible(flex = 1f) {
            VerticalScroller {
                Column {
                    HomeScreenTopSection(post = postTop)
                    HomeScreenSimpleSection(posts = postsSimple)
                    HomeScreenPopularSection(posts = postsPopular)
                    HomeScreenHistorySection(posts = postsHistory)
                }
            }
        }
    }
}

@Composable
private fun HomeScreenTopSection(post: Post) {
    Padding(top = 16.dp, left = 16.dp, right = 16.dp) {
        Text(
            text = "Top stories for you",
            style = (+themeTextStyle { subtitle1 }).withOpacity(0.87f)
        )
    }
    Ripple(bounded = true) {
        Clickable(onClick = {
            navigateTo(Screen.Article(post.id))
        }) {
            PostCardTop(post = post)
        }
    }
    HomeScreenDivider()
}

@Composable
private fun HomeScreenSimpleSection(posts: List<Post>) {
    posts.forEach { post ->
        PostCardSimple(post)
        HomeScreenDivider()
    }
}

@Composable
private fun HomeScreenPopularSection(posts: List<Post>) {
    Padding(16.dp) {
        Text(
            text = "Popular on Jetnews",
            style = (+themeTextStyle { subtitle1 }).withOpacity(0.87f)
        )
    }
    HorizontalScroller {
        Row(modifier = Spacing(bottom = 16.dp, right = 16.dp)) {
            posts.forEach { post ->
                WidthSpacer(16.dp)
                PostCardPopular(post)
            }
        }
    }
    HomeScreenDivider()
}

@Composable
private fun HomeScreenHistorySection(posts: List<Post>) {
    posts.forEach { post ->
        PostCardHistory(post)
        HomeScreenDivider()
    }
}

@Composable
private fun HomeScreenDivider() {
    Padding(left = 14.dp, right = 14.dp) {
        Opacity(0.08f) {
            Divider()
        }
    }
}
