package com.example.listapi

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedIconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.listapi.ViewModel.TodoViewModel
import com.example.listapi.room.TodoEntity

@Composable
fun CardScreen(
    navController: NavController,
    viewModel: TodoViewModel,
    todoId: String?,
    todoName: String?
) {

    // State for the dialog
    var showDialog by remember { mutableStateOf(false) }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp) // Add padding to the Column
    ) {
        Card(
            modifier = Modifier
                .height(250.dp)
                .fillMaxWidth(),
            elevation = CardDefaults.cardElevation(4.dp)
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center, // Center content vertically
                modifier = Modifier.fillMaxSize()
            ) {

                // Display the name of the ToDo
                Row {
                    Text(
                        text = "Name:",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    )

                    Text(
                        text = "" + todoName, // Safely handle null todoName
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    )
                }

                Spacer(modifier = Modifier.weight(1f)) // Push buttons to bottom

                // Buttons at the bottom
                Row(
                    horizontalArrangement = Arrangement.SpaceAround,
                    modifier = Modifier.fillMaxWidth()
                ) {

                    // Update Button
                    OutlinedIconButton(
                        onClick = { /* Update Todo logic */ },
                        shape = RoundedCornerShape(50.dp),
                        modifier = Modifier.size(50.dp) // Simplified size modifier
                    ) {
                        Icon(
                            imageVector = Icons.Filled.Create,
                            contentDescription = "Update",
                            tint = Color.Blue // More appropriate color for update
                        )
                    }

                    // Delete Button (triggers dialog)
                    OutlinedIconButton(
                        onClick = { showDialog = true },
                        shape = RoundedCornerShape(50.dp),
                        modifier = Modifier.size(50.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Filled.Delete,
                            contentDescription = "Delete",
                            tint = Color.Red
                        )
                    }
                }
            }
        }
    }

    // Alert Dialog
    if (showDialog) {
        AlertDialog(
            onDismissRequest = { showDialog = false },
            title = { Text("Delete Todo") },
            text = { Text("Tem Certeza papai ?") },
            confirmButton = {
                Button(onClick = {
                    todoId?.let { viewModel.deleteTodo(todo = TodoEntity (id = it.toInt(), title = todoName.toString())) } // Safe delete
                    navController.popBackStack()
                    showDialog = false
                }) {
                    Text("Yes")
                }
            },
            dismissButton = {
                Button(onClick = { showDialog = false }) {
                    Text("No")
                }
            }
        )
    }
}
