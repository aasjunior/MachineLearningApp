package com.aasjunior.machinelearningapp.ui.view.components

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun <T> SelectBox(
    options: List<T>,
    selectedOption: State<T>,
    label: String,
    transform: (T) -> String = { it.toString() },
    setSelectedOptionText: (String) -> Unit
) {
    var expand by remember { mutableStateOf(false) }

    ExposedDropdownMenuBox(
        expanded = expand,
        onExpandedChange = { expand = it }
    ) {
        OutlinedTextField(
            readOnly = true,
            shape = RoundedCornerShape(12.dp),
            value = transform(selectedOption.value),
            onValueChange = {},
            label = { Text(text = label) },
            trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expand) }
        )

        ExposedDropdownMenu(
            expanded = expand,
            onDismissRequest = { expand = false }
        ) {
            options.forEach { option ->
                DropdownMenuItem(
                    text = { Text(transform(option)) },
                    onClick = {
                        setSelectedOptionText(transform(option))
                        expand = false
                    }
                )
            }
        }
    }
}
