package com.aasjunior.machinelearningapp.ui.view.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
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
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.aasjunior.machinelearningapp.R
import com.aasjunior.machinelearningapp.domain.enums.AlgorithmsML
import com.aasjunior.machinelearningapp.ui.view.components.AlgorithmsMLSelectBox
import com.aasjunior.machinelearningapp.ui.view.components.BaseContent
import com.aasjunior.machinelearningapp.ui.view.components.DocumentPicker
import com.aasjunior.machinelearningapp.ui.viewmodel.HomeViewModel
import kotlinx.coroutines.launch

@Composable
fun HomeScreen(
    navController: NavHostController = rememberNavController(),
    hvm: HomeViewModel
){
    var step by remember { mutableStateOf(0) }
    val selectedAlgorithm by hvm.selectedAlgorithm.collectAsState()

    BaseContent {
        Spacer(modifier = Modifier.height(12.dp))
        when(step){
            0 -> {
                SelectAlgorithm(hvm)

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End
                ){
                    if(selectedAlgorithm != AlgorithmsML.GeneticAlgorithm) NextButton{ step++ }
                    else SubmitButton{
                        navController.navigate("genetic-algorithm")
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

    Spacer(modifier = Modifier.height(10.dp))

    AlgorithmsMLSelectBox(hvm)

    Spacer(modifier = Modifier.height(24.dp))

    if(hvm.selectedAlgorithm.value != AlgorithmsML.GeneticAlgorithm){

        if(!checkedState){
            Label(text = "Inserir a Base de Dados:")

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
}

@Composable
private fun NextButton(x: () -> Unit){
    Button(onClick = x ) {
        Text(text = "Avançar")
    }
}

@Composable
private fun SubmitButton(onClick: () -> Unit){
    Button(onClick = onClick) {
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