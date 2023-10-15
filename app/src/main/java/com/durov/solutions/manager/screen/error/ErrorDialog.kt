package com.durov.solutions.manager.screen.error

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.durov.solutions.manager.R

@Composable
fun ErrorDialog(
    cause: Throwable, onDissmiss: () -> Unit = {}
) {
    when (cause) {
        //todo add other error types
        else -> GeneralErrorDialog(onDissmiss)
    }
}

@Composable
private fun GeneralErrorDialog(onDissmiss: () -> Unit) {
    BaseErrorDialog(
        titleRes = R.string.error_general_text,
        buttonRes = R.string.error_general_button,
        onDissmiss = onDissmiss
    )
}

@Composable
private fun BaseErrorDialog(
    @StringRes titleRes: Int,
    @StringRes buttonRes: Int,
    onDissmiss: () -> Unit = {}
) {
    Dialog(onDismissRequest = onDissmiss) {
        Card(
            modifier = Modifier
                .height(200.dp)
                .width(300.dp)
                .padding(10.dp),
            shape = RoundedCornerShape(10.dp),
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceEvenly
            ) {
                Text(
                    text = stringResource(id = R.string.error_general_text),
                    style = TextStyle(fontSize = 18.sp)
                )
                Button(
                    onClick = onDissmiss
                ) {
                    Text(
                        text = stringResource(id = R.string.error_general_button)
                    )
                }
            }
        }
    }
}