package com.aasjunior.machinelearningapp.ui.view.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.unit.dp
import com.aasjunior.machinelearningapp.domain.enums.AlgorithmsML
import com.aasjunior.machinelearningapp.ui.viewmodel.HomeViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AlgorithmsMLSelectBox(hvm: HomeViewModel) {
    val options = AlgorithmsML.values().map { it.description }
    var expanded by remember { mutableStateOf(false) }
    val selectedOptionText by hvm.selectedAlgorithm.collectAsState()

    Column {
        OutlinedTextField(
            value = selectedOptionText?.description ?: "",
            onValueChange = {},
            label = { Text("Selecione o algoritmo") },
            shape = RoundedCornerShape(12.dp),
            trailingIcon = {
                IconButton(onClick = { expanded = true }) {
                    Icon(Icons.Filled.ArrowDropDown, contentDescription = null)
                }
            },
            readOnly = true
        )

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            options.forEach { selectionOption ->
                DropdownMenuItem(
                    onClick = {
                        hvm.updateAlgorithm(AlgorithmsML.fromDescription(selectionOption)!!)
                        expanded = false
                    },
                    text = { Text(selectionOption) }
                )
            }
        }
    }
}
