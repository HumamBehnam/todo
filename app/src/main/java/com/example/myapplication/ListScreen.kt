package com.example.myapplication

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Checkbox
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
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
                          },
                shape = RoundedCornerShape(15.dp),
                backgroundColor = Color.DarkGray,
                content = {
                    Icon(imageVector = Icons.Filled.Add, contentDescription = "", tint = Color.Black)
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
                           deleteTask = {}
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
){

    val checkedState = remember { mutableStateOf(status)}

    Row(modifier = Modifier
        //.border(1.dp, Color.Red)
        .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ){

        Checkbox(
            checked = checkedState.value,
            onCheckedChange = { changed ->
                checkedState.value = changed
            }
        )


        Text(text = title, modifier = Modifier)


        Column(modifier = Modifier
            .fillMaxWidth(1f),
            horizontalAlignment = Alignment.End
        ) {

            IconButton(
                onClick = {
                    deleteTask()
                },

                ) {
                Icon(
                    painter = painterResource(id = R.drawable.trash),
                    contentDescription = "trash",
                    tint = Color.Black
                )

            }
        }

    }

}