package com.example.myapplication

import androidx.annotation.DrawableRes
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.myapplication.ui.theme.BackgroundColor
import com.example.myapplication.ui.theme.TextColor
import com.example.myapplication.ui.theme.Torquise

@Composable
fun TodoToolbar(
    title: String? = null,
    onClick: () -> Unit = {}
) {
    TopAppBar(
        title = {
            Text(
                text = title.orEmpty(),
                style = MaterialTheme.typography.h5,
                color = Color.Black,
            )
        },
        backgroundColor = Color.White,
        elevation = 0.dp,
        navigationIcon =  {
            ToolbarNavigationButton(onClick)
        }
    )
}

@Composable
internal fun ToolbarNavigationButton(onClick: () -> Unit) {
    ToolbarButton(onClick = onClick, iconId = R.drawable.ic_back)
}

@Composable
private fun ToolbarButton(onClick: () -> Unit, @DrawableRes iconId: Int) {
    IconButton(onClick = onClick) {
        Icon(painterResource(iconId), contentDescription = null, tint = Color.Black)
    }
}