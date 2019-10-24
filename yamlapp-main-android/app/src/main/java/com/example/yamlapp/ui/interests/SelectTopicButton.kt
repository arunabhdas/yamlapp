package com.example.yamlapp.ui.interests

import androidx.compose.Composable
import androidx.compose.unaryPlus
import androidx.ui.core.dp
import androidx.ui.foundation.selection.Toggleable
import androidx.ui.foundation.shape.DrawShape
import androidx.ui.foundation.shape.border.Border
import androidx.ui.foundation.shape.border.DrawBorder
import androidx.ui.foundation.shape.corner.CircleShape
import androidx.ui.graphics.vector.DrawVector
import androidx.ui.layout.Container
import androidx.ui.layout.Padding
import androidx.ui.material.MaterialColors
import androidx.ui.material.MaterialTheme
import androidx.ui.material.ripple.Ripple
import androidx.ui.material.surface.Surface
import androidx.ui.material.themeColor
import androidx.ui.res.vectorResource
import androidx.ui.tooling.preview.Preview
import com.example.yamlapp.R
import com.example.yamlapp.ui.darkThemeColors
import com.example.yamlapp.ui.lightThemeColors

@Composable
fun SelectTopicButton(
    onSelected: ((Boolean) -> Unit)? = null,
    selected: Boolean = false
) {
    Ripple(bounded = false) {
        Toggleable(checked = selected, onCheckedChange = onSelected) {
            Container(width = 36.dp, height = 36.dp) {
                if (selected) {
                    DrawSelectTopicButtonOn()
                } else {
                    DrawSelectTopicButtonOff()
                }
            }
        }
    }
}

@Composable
private fun DrawSelectTopicButtonOn() {
    DrawShape(
        shape = CircleShape,
        color = +themeColor { primary }
    )
    DrawVector(+vectorResource(R.drawable.ic_check))
}

@Composable
private fun DrawSelectTopicButtonOff() {
    val borderColor = (+themeColor { onSurface }).copy(alpha = 0.12f)
    DrawBorder(
        shape = CircleShape,
        border = Border(borderColor, 2.dp)
    )
    DrawVector(+vectorResource(R.drawable.ic_add))
}

@Preview("Off")
@Composable
fun SelectTopicButtonPreviewOff() {
    SelectTopicButtonPreviewTemplate(
        lightThemeColors,
        false
    )
}

@Preview("On")
@Composable
fun SelectTopicButtonPreviewOn() {
    SelectTopicButtonPreviewTemplate(
        lightThemeColors,
        true
    )
}

@Preview("Off - Dark")
@Composable
fun SelectTopicButtonPreviewOffDark() {
    SelectTopicButtonPreviewTemplate(
        darkThemeColors,
        false
    )
}

@Preview("On - Dark")
@Composable
fun SelectTopicButtonPreviewOnDark() {
    SelectTopicButtonPreviewTemplate(
        darkThemeColors,
        true
    )
}

@Composable
private fun SelectTopicButtonPreviewTemplate(themeColors: MaterialColors, selected: Boolean) {
    MaterialTheme(themeColors) {
        Surface {
            Padding(32.dp) {
                SelectTopicButton(selected = selected)
            }
        }
    }
}
