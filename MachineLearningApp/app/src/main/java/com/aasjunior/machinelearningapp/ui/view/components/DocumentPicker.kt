package com.aasjunior.machinelearningapp.ui.view.components

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DocumentPicker() {
    val result = remember { mutableStateOf<Uri?>(null) }
    val text = remember { mutableStateOf("") }

    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.OpenDocument()
    ) {
        result.value = it
        text.value = it?.path.toString() ?: ""
    }

    Column {
        OutlinedTextField(
            value = text.value,
            onValueChange = { text.value = it },
            label = { Text("Selecione o CSV") },
            shape = RoundedCornerShape(12.dp),
            trailingIcon = {
                IconButton(onClick = { launcher.launch(arrayOf("text/csv")) }) {
                    Icon(Icons.Filled.Add, contentDescription = "Selecionar arquivo")
                }
            }
        )
        result.value?.let { csv ->
            Text(text = "Caminho do CSV: ${csv.path}")
        }
    }
}

@Preview
@Composable
private fun DocumentPickerPreview() {
    DocumentPicker()
}

