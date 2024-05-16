package com.aasjunior.machinelearningapp.ui.view.components

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun DocumentPicker(){
    val result = remember {
        mutableStateOf<Uri?>(null)
    }

    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.OpenDocument()
    ){
        result.value = it
    }

    Column {
        Button(
            onClick = {
                launcher.launch(arrayOf("text/csv"))
            }
        ) {
            Text(text = "Select CSV")
        }
        result.value?.let{ csv ->
            Text(text = "CSV Path: ${csv.path.toString()}")
        }
    }
}

@Preview
@Composable
private fun DocumentPickerPreview(){
    DocumentPicker()
}
