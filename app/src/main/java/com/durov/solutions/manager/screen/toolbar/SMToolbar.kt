package com.durov.solutions.manager.screen.toolbar

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.durov.solutions.manager.R

@Composable
fun SMToolbar(
    modifier: Modifier = Modifier,
    @StringRes titleRes: Int,
    onBack: () -> Unit
) {
    BaseToolbar(
        modifier = modifier,
        titleRes = titleRes,
        startIcon = R.drawable.arrow_back_black_24,
        onStartButtonClick = onBack
    )
}

@Composable
fun SMHomeToolbar(
    modifier: Modifier = Modifier,
    @StringRes titleRes: Int = R.string.screen_home_title,
) {
    BaseToolbar(modifier = modifier, titleRes = titleRes)
}

@Composable
private fun BaseToolbar(
    modifier: Modifier = Modifier,
    startIcon: Int? = null,
    endIcon: Int? = null,
    titleRes: Int,
    titleStyle: TextStyle = TextStyle(color = Color.White, fontSize = 20.sp),
    color: Color = MaterialTheme.colorScheme.primary,
    titlePaddings: PaddingValues = PaddingValues(all = 15.dp),
    onStartButtonClick: () -> Unit = {},
    onEndButtonClick: () -> Unit = {}
) {
    Surface(
        color = color
    ) {
        Row(
            modifier
                .fillMaxWidth()
                .wrapContentHeight(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            startIcon?.let {
                IconButton(onClick = onStartButtonClick) {
                    Icon(
                        modifier = Modifier.padding(10.dp),
                        painter = painterResource(id = it),
                        contentDescription = "start toolbar icon"
                    )
                }
            }
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(titlePaddings),
                text = stringResource(id = titleRes),
                style = titleStyle
            )
            endIcon?.let {
                IconButton(onClick = onEndButtonClick) {
                    Icon(
                        modifier = Modifier.padding(10.dp),
                        painter = painterResource(id = it),
                        contentDescription = "end toolbar icon"
                    )
                }
            }
        }
    }
}