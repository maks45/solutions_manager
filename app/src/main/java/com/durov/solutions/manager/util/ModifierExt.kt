package com.durov.solutions.manager.util

import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha

fun Modifier.visible(visible: Boolean): Modifier {
    return this.alpha(
        if (visible) 1f else 0f
    )
}