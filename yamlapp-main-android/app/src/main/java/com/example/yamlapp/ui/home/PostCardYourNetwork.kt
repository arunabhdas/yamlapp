package com.example.yamlapp.ui.home

import androidx.compose.Composable
import androidx.compose.unaryPlus
import androidx.ui.core.Text
import androidx.ui.core.dp
import androidx.ui.foundation.Clickable
import androidx.ui.foundation.DrawImage
import androidx.ui.foundation.shape.corner.RoundedCornerShape
import androidx.ui.layout.Column
import androidx.ui.layout.Container
import androidx.ui.layout.LayoutSize
import androidx.ui.layout.Spacing
import androidx.ui.material.ripple.Ripple
import androidx.ui.material.surface.Card
import androidx.ui.material.themeTextStyle
import androidx.ui.material.withOpacity
import androidx.ui.res.imageResource
import androidx.ui.text.style.TextOverflow
import com.example.yamlapp.R
import com.example.yamlapp.model.Post
import com.example.yamlapp.ui.Screen
import com.example.yamlapp.ui.navigateTo

@Composable
fun PostCardPopular(post: Post) {
    Card(shape = RoundedCornerShape(4.dp)) {
        Ripple(bounded = true) {
            Clickable(onClick = {
                navigateTo(Screen.Article(post.id))
            }) {
                Container(width = 280.dp, height = 240.dp) {
                    Column(
                        mainAxisSize = LayoutSize.Expand,
                        crossAxisSize = LayoutSize.Expand
                    ) {
                        val image = post.image ?: +imageResource(R.drawable.placeholder_4_3)
                        Container(height = 100.dp, expanded = true) {
                            DrawImage(image)
                        }
                        Column(modifier = Spacing(16.dp)) {
                            Text(
                                text = post.title,
                                style = (+themeTextStyle { h6 }).withOpacity(0.87f),
                                maxLines = 2,
                                overflow = TextOverflow.Ellipsis
                            )
                            Text(
                                text = post.metadata.author.name,
                                style = (+themeTextStyle { body2 }).withOpacity(0.87f)
                            )
                            Text(
                                text = "${post.metadata.date} - " +
                                        "${post.metadata.readTimeMinutes} min read",
                                style = (+themeTextStyle { body2 }).withOpacity(0.6f)
                            )
                        }
                    }
                }
            }
        }
    }
}
