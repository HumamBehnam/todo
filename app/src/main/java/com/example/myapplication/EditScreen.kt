package com.example.myapplication

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Checkbox
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.Placeholder
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun EditScreen (
    editViewModel: EditViewModel = hiltViewModel(),
    navigateToList : () -> Unit,
){

    var text = remember{ mutableStateOf("") }
    val innerPadding = PaddingValues(0.dp)

    Scaffold(
        topBar = {
            TodoToolbar(
                title = "Edit Task",
                onClick = {
                    navigateToList()
                }
            )
        })
    { innerPadding ->

        val padding = innerPadding

        Column {

            var check by remember {
                mutableStateOf(false)
            }

            StyledOutlinedTextField("Title", "Enter title")

            StyledOutlinedTextField("Description", "Enter description")




            Checkbox(
                checked = check,
                onCheckedChange = {check = it},

            )


        }

    }
}


@Composable
fun StyledOutlinedTextField(
    title: String,
    placeholder: String

) {
    var value by remember { mutableStateOf("") }

    
    Column() {
        Text(
            text = title,
            modifier = Modifier.padding(horizontal = 20.dp)
        )
        OutlinedTextField(
            value = value,
            onValueChange = { value = it },
            maxLines = 2,
            textStyle = TextStyle(color = Color.Black),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
                .padding(top = 5.dp, bottom = 35.dp),
            placeholder = {Text(text = placeholder, color = Color.Gray)}
        )
    }
    
}