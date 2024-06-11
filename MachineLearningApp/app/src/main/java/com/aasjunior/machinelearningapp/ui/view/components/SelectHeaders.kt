package com.aasjunior.machinelearningapp.ui.view.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Checkbox
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.aasjunior.machinelearningapp.ui.viewmodel.HomeViewModel

@Composable
fun SelectHeaders(hvm: HomeViewModel){
    val attributeHeaders by hvm.attributeHeaders.collectAsState()
    val classHeader by hvm.classHeader.collectAsState()

    LazyColumn {
        items(attributeHeaders.toList()) { (header, isSelected) ->
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically
            ){
                Checkbox(
                    checked = isSelected,
                    onCheckedChange = {
                        hvm.updateAttributeSelection(header, it)
                    }
                )
                Text(header)
            }
        }

        items(attributeHeaders.filter { (header, isSelected) ->
            !isSelected && header != classHeader
        }.toList()) { (header, _) ->
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically
            ){
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
