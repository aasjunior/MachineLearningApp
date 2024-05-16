package com.aasjunior.machinelearningapp.ui.view.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Checkbox
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.aasjunior.machinelearningapp.ui.viewmodel.HomeViewModel

@Composable
fun SelectHeaders(hvm: HomeViewModel){
    val attributeHeaders by hvm.attributeHeaders.collectAsState()
    val classHeader by hvm.classHeader.collectAsState()

    Column {
        attributeHeaders.forEach { (header, isSelected) ->
            Row {
                Checkbox(
                    checked = isSelected,
                    onCheckedChange = {
                        hvm.updateAttributeSelection(header, it)
                    }
                )
                Text(header)
            }
        }

        attributeHeaders.filter { (header, isSelected) ->
            !isSelected && header != classHeader
        }.forEach { (header, _) ->
            Row {
                RadioButton(
                    selected = classHeader == header,
                    onClick = {
                        hvm.updateClassSelection(header)
                    }
                )
                Text(header)
            }
        }
    }
}