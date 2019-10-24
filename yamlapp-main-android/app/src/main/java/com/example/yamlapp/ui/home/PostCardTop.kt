package com.example.yamlapp.ui.home

import androidx.compose.Composable
import androidx.compose.ambient
import androidx.compose.unaryPlus
import androidx.ui.core.Clip
import androidx.ui.core.ContextAmbient
import androidx.ui.core.Text
import androidx.ui.core.dp
import androidx.ui.foundation.DrawImage
import androidx.ui.foundation.shape.corner.RoundedCornerShape
import androidx.ui.layout.Column
import androidx.ui.layout.Container
import androidx.ui.layout.HeightSpacer
import androidx.ui.layout.LayoutSize
import androidx.ui.layout.Padding
import androidx.ui.material.MaterialColors
import androidx.ui.material.MaterialTheme
import androidx.ui.material.MaterialTypography
import androidx.ui.material.surface.Surface
import androidx.ui.material.themeTextStyle
import androidx.ui.material.withOpacity
import androidx.ui.tooling.preview.Preview
import com.example.yamlapp.data.getPostsWithImagesLoaded
import com.example.yamlapp.data.posts
import com.example.yamlapp.model.Post
import com.example.yamlapp.ui.darkThemeColors
import com.example.yamlapp.ui.lightThemeColors
import com.example.yamlapp.ui.themeTypography

@Composable
fun PostCardTop(post: Post) {
    // TUTORIAL CONTENT STARTS HERE
    Padding(16.dp) {
        Column(crossAxisSize = LayoutSize.Expand) {
            post.image?.let { image ->
                Container(expanded = true, height = 180.dp) {
                    Clip(shape = RoundedCornerShape(4.dp)) {
                        DrawImage(image)
                    }
                }
            }
            HeightSpacer(16.dp)
            Text(
                text = post.title,
                style = (+themeTextStyle { h6 }).withOpacity(0.87f)
            )
            Text(
                text = post.metadata.author.name,
                style = (+themeTextStyle { body2 }).withOpacity(0.87f)
            )
            Text(
                text = "${post.metadata.date} - ${post.metadata.readTimeMinutes} min read",
                style = (+themeTextStyle { body2 }).withOpacity(0.6f)
            )
        }
    }
    // TUTORIAL CONTENT ENDS HERE
}

// Preview section

@Preview("Default colors")
@Composable
fun TutorialPreview() {
    TutorialPreviewTemplate()
}

@Preview("Dark colors")
@Composable
fun TutorialPreviewDark() {
    TutorialPreviewTemplate(colors = darkThemeColors)
}

@Preview("Font scaling 1.5", fontScale = 1.5f)
@Composable
fun TutorialPreviewFontscale() {
    TutorialPreviewTemplate()
}

@Composable
fun TutorialPreviewTemplate(
    colors: MaterialColors = lightThemeColors,
    typography: MaterialTypography = themeTypography
) {
    val context = +ambient(ContextAmbient)
    val previewPosts = getPostsWithImagesLoaded(posts.subList(1, 2), context.resources)
    val post = previewPosts[0]
    MaterialTheme(colors = colors, typography = typography) {
        Surface {
            PostCardTop(post)
        }
    }
}
