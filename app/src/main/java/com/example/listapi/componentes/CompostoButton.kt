package com.example.listapi.componentes

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun TaskStatusButtons(
    selectedStatus: String,
    onStatusSelected: (String) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp),
        horizontalArrangement = Arrangement.SpaceAround
    ) {
        Button(
            onClick = { onStatusSelected("Pendentes") },
            modifier = Modifier.weight(1f),
            colors = ButtonDefaults.buttonColors(
                containerColor = if (selectedStatus == "Pendentes") Color.Blue else Color.LightGray
            )
        ) {
            Text("Pendentes")
        }

        Button(
            onClick = { onStatusSelected("Em Progresso") },
            modifier = Modifier.weight(1f),
            colors = ButtonDefaults.buttonColors(
                containerColor = if (selectedStatus == "Em Progresso") Color.Blue else Color.LightGray
            ),
        ) {
            Text("Em Progresso", fontSize = 12.sp)
        }

        Button(
            onClick = { onStatusSelected("Terminados") },
            modifier = Modifier.weight(1f),
            colors = ButtonDefaults.buttonColors(
                containerColor = if (selectedStatus == "Terminados") Color.Blue else Color.LightGray
            )
        ) {
            Text("Terminados")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TaskStatusButtonsPreview() {
    var selectedStatus by remember { mutableStateOf("Em Progresso") }

    TaskStatusButtons(
        selectedStatus = selectedStatus,
        onStatusSelected = { newStatus ->
            selectedStatus = newStatus
        }
    )
}