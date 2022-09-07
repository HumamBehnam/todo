package com.example.myapplication

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomAppBar
import androidx.compose.material.Checkbox
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.R
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.Placeholder
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.myapplication.ui.theme.BackgroundColor
import com.example.myapplication.ui.theme.BorderColor
import com.example.myapplication.ui.theme.CardBackground
import com.example.myapplication.ui.theme.Torquise

val HORIZONTAL_PAD = 20.dp

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
        },
        bottomBar = {
            BottomAppBar(
                modifier = Modifier
                    .height(80.dp)
                    .border(
                        width = 1.dp,
                        color = Color.White
                    ),
                backgroundColor = Color.White,

            ) {
                Row(modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(),
                ) {
                    FloatingActionButton(
                        backgroundColor = CardBackground,
                        content = {
                            Text("Cancel", color = Color.White)
                        },
                        onClick = {
                            navigateToList()
                        },
                        modifier = Modifier
                            .fillMaxWidth(0.5f)
                            .padding(horizontal = 20.dp, vertical = 11.dp)
                    )
                    FloatingActionButton(
                        backgroundColor = CardBackground,
                        onClick = {
                            editViewModel.insertTask(editViewModel.appState.currentTask)
                        },
                        modifier = Modifier
                            .fillMaxWidth(1f)
                            .padding(horizontal = 20.dp, vertical = 11.dp),
                        shape = RoundedCornerShape(60),
                        content = {
                            Text(text = "Edit", color = Color.White)
                        }
                    )
                }

            }
        }
    )
    { innerPadding ->

        val task by remember {editViewModel.currTask}


        val padding = innerPadding

        Column {

            var check by remember {
                mutableStateOf(false)
            }

            StyledOutlinedTextField(
                "Title",
                "Enter title",
                content = task.title,
                editViewModel::updateName
            )

            StyledOutlinedTextField(
                "Description",
                "Enter description",
                content = task.description,
                editViewModel::updateDescription
            )




            Text(
                "Status",
                modifier = Modifier.padding(horizontal = HORIZONTAL_PAD)
            )

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start
            ) {
                Checkbox(
                    checked = check,
                    onCheckedChange = {check = it},
                    modifier = Modifier.padding(horizontal = HORIZONTAL_PAD/2),

                    )
                Text("Done")
            }



        }

    }
}


@Composable
fun StyledOutlinedTextField(
    title: String,
    placeholder: String,
    content: String = "",
    changeText: (String) -> Unit
) {

    
    Column() {
        Text(
            text = title,
            modifier = Modifier.padding(horizontal = HORIZONTAL_PAD)
        )
        OutlinedTextField(
            value = content,
            onValueChange = { newText ->

                changeText(newText)
                            },
            maxLines = 2,
            textStyle = TextStyle(color = Color.Black),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = HORIZONTAL_PAD)
                .padding(top = 5.dp, bottom = 35.dp),
            placeholder = {Text(text = placeholder, color = Color.Gray)}
        )
    }
    
}