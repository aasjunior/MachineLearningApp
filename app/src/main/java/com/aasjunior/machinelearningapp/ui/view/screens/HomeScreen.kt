package com.aasjunior.machinelearningapp.ui.view.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.aasjunior.machinelearningapp.domain.enums.AlgorithmsML
import com.aasjunior.machinelearningapp.ui.theme.MachineLearningAppTheme
import com.aasjunior.machinelearningapp.ui.view.components.DocumentPicker
import com.aasjunior.machinelearningapp.ui.view.components.SelectBox
import com.aasjunior.machinelearningapp.ui.viewmodel.HomeViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(hvm: HomeViewModel){
    val options = AlgorithmsML.values().toList()
    val selectedOption = hvm.selectedOptionText.collectAsState()

    Scaffold {innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(16.dp))

            Text(text = "Algoritmo AM:")

            SelectBox(
                options = options,
                selectedOption = selectedOption,
                label = "Algoritmo AM",
                transform = { (it as? AlgorithmsML)?.description ?: "" },
                setSelectedOptionText = hvm::setSelectedOptionText
            )

            Text(text = "Base de dados:")

            DocumentPicker()

            Button(onClick = { /*TODO*/ }) {
                Text(text = "Executar Algoritmo")
            }
        }
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