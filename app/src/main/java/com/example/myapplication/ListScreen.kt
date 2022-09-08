package com.example.myapplication

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.Checkbox
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.myapplication.ui.theme.BorderColor
import com.example.myapplication.ui.theme.CardBackground
import com.example.myapplication.ui.theme.Torquise

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun ListScreen(
    listViewModel: ListViewModel = hiltViewModel(),
    navigateToEditTask: () -> Unit,
){

    val allTasks = listViewModel.allTasks.observeAsState().value
    val searchResults = listViewModel.searchResults.observeAsState().value



    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    navigateToEditTask()
                    //listViewModel.insertTask(Task("waow", "veeem", true))
                          },
                shape = RoundedCornerShape(15.dp),
                backgroundColor = CardBackground,
                content = {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.padding(12.dp)
                    ) {
                        Icon(imageVector = Icons.Filled.Add, contentDescription = "", tint = Color.White)
                        Text(text = "Add task", color = Color.White)
                    }
                }
            )
        },
        content = {


            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(5.dp)
            ){


               allTasks?.let {
                   itemsIndexed(it) { index, task ->
                       todoCard(
                           title = task.title,
                           status = task.status,
                           deleteTask = {listViewModel.deleteTask(task.id)},
                           editTask = {
                               listViewModel.appState.currentTask = task
                               navigateToEditTask()
                           }
                       )
                   }
               }

                //itemsIndexed(todoList){ index, task ->
                //    todoCard(
                //        title = task.title,
                //        status = task.status,
                //        deleteTask = {listViewModel.deleteTask(index)}
                //    )
                //}



            }




        }
    )



}


@Composable
fun todoCard(
    title : String,
    status : Boolean,
    deleteTask: () -> Unit,
    editTask: () -> Unit,
){

    val checkedState = remember { mutableStateOf(status)}
    val openDialog = remember { mutableStateOf(false)  }




    Row(modifier = Modifier
        //.border(1.dp, Color.Red)
        .fillMaxWidth()
        .clickable {
            editTask()
        },
        verticalAlignment = Alignment.CenterVertically
    ){

        Checkbox(
            checked = checkedState.value,
            onCheckedChange = { changed ->
                checkedState.value = changed
            },
            enabled = false
        )


        Text(text = title, modifier = Modifier)


        Column(modifier = Modifier
            .fillMaxWidth(1f),
            horizontalAlignment = Alignment.End
        ) {

            IconButton(
                onClick = {
                    openDialog.value = true
                },

                ) {
                Icon(
                    painter = painterResource(id = R.drawable.trash),
                    contentDescription = "trash",
                    tint = Color.Black
                )

            }

            if (openDialog.value) {

                AlertDialog(
                    onDismissRequest = {
                        // Dismiss the dialog when the user clicks outside the dialog or on the back
                        // button. If you want to disable that functionality, simply use an empty
                        // onCloseRequest.
                        openDialog.value = false
                    },
                    title = {
                        Text(text = "Confirm deletion")
                    },
                    text = {
                        Text("This will permanently delete this task ")
                    },
                    confirmButton = {
                        TextButton(

                            onClick = {
                                openDialog.value = false
                                deleteTask()
                            }) {
                            Text("Delete task")
                        }
                    },
                    dismissButton = {
                        TextButton(

                            onClick = {
                                openDialog.value = false
                            }) {
                            Text("Cancel")
                        }
                    }
                )
            }

        }

    }

}