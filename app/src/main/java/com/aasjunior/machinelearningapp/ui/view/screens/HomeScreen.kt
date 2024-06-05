package com.aasjunior.machinelearningapp.ui.view.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.aasjunior.machinelearningapp.R
import com.aasjunior.machinelearningapp.domain.enums.AlgorithmsML
import com.aasjunior.machinelearningapp.ui.theme.MachineLearningAppTheme
import com.aasjunior.machinelearningapp.ui.view.components.AlgorithmsMLSelectBox
import com.aasjunior.machinelearningapp.ui.view.components.BaseContent
import com.aasjunior.machinelearningapp.ui.view.components.DocumentPicker
import com.aasjunior.machinelearningapp.ui.viewmodel.HomeViewModel
import kotlinx.coroutines.launch

@Composable
fun HomeScreen(hvm: HomeViewModel){
    var step by remember { mutableStateOf(0) }
    val selectedAlgorithm by hvm.selectedAlgorithm.collectAsState()

    BaseContent {
        when(step){
            0 -> {
                SelectAlgorithm(hvm)

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End
                ){
                    if(selectedAlgorithm != AlgorithmsML.GeneticAlgorithm) NextButton{ step++ }
                    else SubmitButton()
                }
            }
            1 -> {
                SelectAttributes(hvm)

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ){
                    Button(onClick = { step-- }) {
                        Text(text = "Voltar")
                    }

                    Button(onClick = { step++ }) {
                        Text(text = "Avançar")
                    }
                }
            }
            2 -> {
                SelectClass(hvm)

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ){
                    Button(onClick = { step-- }) {
                        Text(text = "Voltar")
                    }

                    Button(onClick = { step++ }) {
                        Text(text = "Executar Algoritmo")
                    }
                }
            }
        }

    }
}

@Composable
private fun SelectAlgorithm(hvm: HomeViewModel){
    val coroutineScope = rememberCoroutineScope()
    val context = LocalContext.current
    var checkedState by remember { mutableStateOf(false) }

    Label(text = "Selecionar o Algoritmo ML:")

    AlgorithmsMLSelectBox(hvm)

    Spacer(modifier = Modifier.height(24.dp))

    Label(text = "Inserir a Base de Dados:")

    if(!checkedState){
        DocumentPicker()
    }

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically
    ){
        Checkbox(
            checked = checkedState,
            onCheckedChange = {
                checkedState = it
                if(it){
                    coroutineScope.launch {
                        hvm.readLocalResCSV(context, R.raw.iris)
                    }
                }
            }
        )
        Text(text = "Usar base local")
    }
}

@Composable
private fun SelectAttributes(hvm: HomeViewModel){
    val attributeHeaders by hvm.attributeHeaders.collectAsState()

    Label(text = "Selecionar Atributos:")

    LazyColumn {
        items(attributeHeaders.toList()) { (header, isSelected) ->
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Checkbox(
                    checked = isSelected,
                    onCheckedChange = {
                        hvm.updateAttributeSelection(header, it)
                    }
                )
                Text(header)
            }
        }
    }

}

@Composable
private fun SelectClass(hvm: HomeViewModel){
    val attributeHeaders by hvm.attributeHeaders.collectAsState()
    val classHeader by hvm.classHeader.collectAsState()

    Label(text = "Selecione a Classe:")

    LazyColumn {
        items(attributeHeaders.filter { (_, isSelected) -> !isSelected }.toList()) { (header, _) ->
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically
            ) {
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


@Composable
private fun NextButton(x: () -> Unit){
    Button(onClick = x ) {
        Text(text = "Avançar")
    }
}

@Composable
private fun SubmitButton(){
    Button(onClick = {  }) {
        Text(text = "Executar Algoritmo")
    }
}

@Composable
private fun Label(text: String){
    Row(
        modifier = Modifier.width(264.dp),
        horizontalArrangement = Arrangement.Start
    ){
        Text(text)
    }
}

@Preview
@Composable
private fun HomeScreenPreview(){
    val hvm: HomeViewModel = viewModel()

    MachineLearningAppTheme {
        // A surface container using the 'background' color from the theme
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            HomeScreen(hvm)
        }
    }
}