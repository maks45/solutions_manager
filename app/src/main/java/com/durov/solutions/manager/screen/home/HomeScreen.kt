package com.durov.solutions.manager.screen.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import com.durov.solutions.manager.R
import com.durov.solutions.manager.screen.toolbar.SMHomeToolbar
import org.koin.androidx.compose.getViewModel

@Composable
fun HomeScreen(viewModel: HomeViewModel = getViewModel()) {
    Box(Modifier.fillMaxSize()) {
        Column(Modifier.fillMaxSize()) {
            SMHomeToolbar()
        }
        Button(
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(all = 20.dp),
            onClick = viewModel::addNewSubject
        ) {
            Text(
                text = stringResource(R.string.button_add_new_subject),
                style = TextStyle()
            )
        }
    }
}