package com.aasjunior.machinelearningapp.ui.view.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.aasjunior.machinelearningapp.ui.theme.MachineLearningAppTheme
import com.aasjunior.machinelearningapp.ui.view.components.AlgorithmsMLSelectBox
import com.aasjunior.machinelearningapp.ui.view.components.DocumentPicker
import com.aasjunior.machinelearningapp.ui.viewmodel.HomeViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(hvm: HomeViewModel){
    Scaffold {innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(16.dp))

            Label(text = "Selecionar o Algoritmo ML:")

            AlgorithmsMLSelectBox(hvm)

            Spacer(modifier = Modifier.height(16.dp))

            Label(text = "Inserir a Base de Dados:")

            DocumentPicker()

            Button(onClick = { /*TODO*/ }) {
                Text(text = "Executar Algoritmo")
            }
        }
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