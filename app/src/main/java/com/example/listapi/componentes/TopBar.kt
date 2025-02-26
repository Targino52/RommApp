package com.example.listapi.componentes

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun TopBar(title:String, buttonLeft: @Composable ()->Unit, buttonRight: @Composable ()->Unit,color: Color = Color.Black){
    Spacer(modifier = Modifier.height( 60.dp))

    Row(modifier = Modifier
        .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        buttonLeft()
        Text(
            text = title,
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
            color = color

        )
        buttonRight()
    }

}
