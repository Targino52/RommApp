package com.example.listapi.screens
import android.annotation.SuppressLint
import android.net.Uri
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.listapi.ViewModel.TodoViewModel
import com.example.listapi.room.TodoEntity


@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainScreen(viewModel: TodoViewModel, navController: NavController) {
    var inputTodo by remember { mutableStateOf("") }
    val enty by remember { mutableStateOf("") } // Consider renaming for clarity (e.g., "currentEntry")
    val showDialog = remember { mutableStateOf(false) }
    val todos = viewModel.todos.collectAsState(initial = emptyList())

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Projeto",
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentWidth(Alignment.CenterHorizontally),
                        fontWeight = FontWeight.Bold,
                        color = Color.Black,
                    )
                },

                actions = {
                    IconButton(onClick = { /* Handle search action */ }) {
                        Icon(
                            imageVector = Icons.Filled.Search,
                            contentDescription = "Pesquisar",
                            tint = Color.Blue
                        )
                    }
                },

            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { showDialog.value = true },
                containerColor = Color(0xFF006FFD),
                contentColor = MaterialTheme.colorScheme.background,
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth()
            ) {
                Text(text = "Criar Task")

            }
        },
        floatingActionButtonPosition = FabPosition.Center,

        content = { innerPadding ->
            if (todos.value.isEmpty()) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .padding(innerPadding)
                        .fillMaxSize()
                ) {
                    Text(text = "No todos available")
                }
            } else {
                LazyColumn(
                    modifier = Modifier
                        .padding(innerPadding)
                        .fillMaxSize(),
                    contentPadding = PaddingValues(16.dp)
                ) {
                    items(todos.value) {  // Destructure directly
                        Card(
                            onClick = {
                                navController.navigate("Card/${Uri.encode(it.id.toString())})/${Uri.encode(it.title)}")
                            },
                            modifier = Modifier
                                .padding(5.dp)
                                .fillMaxWidth(),
                            elevation = CardDefaults.cardElevation(4.dp)
                        ) {
                            Row {
                                Text(text = ""+it.id, modifier = Modifier.padding(16.dp).weight(1f))
                            }
                        }
                    }
                }
            }
        }
    )

    if (showDialog.value) {
        AlertDialog(
            onDismissRequest = { showDialog.value = false },
            dismissButton = {
                Button(
                    onClick = {
                        showDialog.value = false
                        inputTodo = enty // Reset input on cancel
                    }
                ) {
                    Text(text = "Cancel")
                }
            },
            confirmButton = {
                if (inputTodo.isNotEmpty()) {
                    Button(
                        onClick = {
                            viewModel.addTodo(TodoEntity(0, inputTodo))
                            showDialog.value = false
                            inputTodo = "" // Reset input on save
                        }
                    ) {
                        Text(text = "Save")
                    }
                }
            },
            title = { Text(text = "Add Todo") },

            text = {
                OutlinedTextField(
                    value = inputTodo,
                    onValueChange = { inputTodo = it },
                    label = { Text(text = "Nome Todo") },
                    placeholder = { Text(text = "Enter Todo") }
                )

            }



        )
    }
}
