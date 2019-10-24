package com.example.yamlapp.ui.article

import android.content.Context
import android.content.Intent
import androidx.annotation.DrawableRes
import androidx.compose.Composable
import androidx.compose.ambient
import androidx.compose.state
import androidx.compose.unaryPlus
import androidx.ui.core.ContextAmbient
import androidx.ui.core.Text
import androidx.ui.core.dp
import androidx.ui.foundation.Clickable
import androidx.ui.graphics.vector.DrawVector
import androidx.ui.layout.Container
import androidx.ui.layout.FlexColumn
import androidx.ui.layout.FlexRow
import androidx.ui.layout.Padding
import androidx.ui.layout.WidthSpacer
import androidx.ui.material.AlertDialog
import androidx.ui.material.Button
import androidx.ui.material.TextButtonStyle
import androidx.ui.material.TopAppBar
import androidx.ui.material.ripple.Ripple
import androidx.ui.material.surface.Surface
import androidx.ui.material.themeTextStyle
import androidx.ui.res.vectorResource
import com.example.yamlapp.R
import com.example.yamlapp.data.posts
import com.example.yamlapp.model.Post
import com.example.yamlapp.ui.Screen
import com.example.yamlapp.ui.VectorImageButton
import com.example.yamlapp.ui.home.BookmarkButton
import com.example.yamlapp.ui.home.isFavorite
import com.example.yamlapp.ui.home.toggleBookmark
import com.example.yamlapp.ui.navigateTo

@Composable
fun ArticleScreen(postId: String) {

    var showDialog by +state { false }
    // getting the post from our list of posts by Id
    val post = posts.find { it.id == postId } ?: return

    if (showDialog) {
        FunctionalityNotAvailablePopup {
            showDialog = false
        }
    }

    FlexColumn {
        inflexible {
            TopAppBar(
                title = {
                    Text(
                        text = "Published in: ${post.publication?.name}",
                        style = +themeTextStyle { subtitle2 }
                    )
                },
                navigationIcon = {
                    VectorImageButton(R.drawable.ic_back) {
                        navigateTo(Screen.Home)
                    }
                }
            )
        }
        expanded(1f) {
            PostContent(post)
        }
        inflexible {
            BottomBar(post) { showDialog = true }
        }
    }
}

@Composable
private fun BottomBar(post: Post, onUnimplementedAction: () -> Unit) {
    val context = +ambient(ContextAmbient)
    Surface(elevation = 2.dp) {
        Container(height = 56.dp, expanded = true) {
            FlexRow {
                inflexible {
                    BottomBarAction(R.drawable.ic_favorite) {
                        onUnimplementedAction()
                    }
                    BookmarkButton(
                        isBookmarked = isFavorite(postId = post.id),
                        onBookmark = { toggleBookmark(postId = post.id) }
                    )
                    BottomBarAction(R.drawable.ic_share) {
                        sharePost(post, context)
                    }
                }
                expanded(flex = 1f) {
                    WidthSpacer(1.dp)
                }
                inflexible {
                    BottomBarAction(R.drawable.ic_text_settings) {
                        onUnimplementedAction()
                    }
                }
            }
        }
    }
}

@Composable
private fun BottomBarAction(
    @DrawableRes id: Int,
    onClick: () -> Unit
) {
    Ripple(
        bounded = false,
        radius = 24.dp
    ) {
        Clickable(onClick = onClick) {
            Padding(12.dp) {
                Container(width = 24.dp, height = 24.dp) {
                    DrawVector(+vectorResource(id))
                }
            }
        }
    }
}

@Composable
private fun FunctionalityNotAvailablePopup(onDismiss: () -> Unit) {
    AlertDialog(
        onCloseRequest = onDismiss,
        text = {
            Text(
                text = "Functionality not available \uD83D\uDE48",
                style = +themeTextStyle { body2 }
            )
        },
        confirmButton = {
            Button(
                text = "CLOSE",
                style = TextButtonStyle(),
                onClick = onDismiss
            )
        }
    )
}

private fun sharePost(post: Post, context: Context) {
    val intent = Intent(Intent.ACTION_SEND).apply {
        type = "text/plain"
        putExtra(Intent.EXTRA_TITLE, post.title)
        putExtra(Intent.EXTRA_TEXT, post.url)
    }
    context.startActivity(Intent.createChooser(intent, "Share post"))
}
