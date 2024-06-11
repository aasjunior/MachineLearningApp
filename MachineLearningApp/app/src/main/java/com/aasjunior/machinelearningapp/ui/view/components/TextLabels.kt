package com.aasjunior.machinelearningapp.ui.view.components

import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontWeight

@Composable
fun TextLabel(label: String, value: String?){
    Row {
        Text(text = label, fontWeight = FontWeight.Bold)
        Text(text = " ")
        Text(text = value ?: "")
    }
}