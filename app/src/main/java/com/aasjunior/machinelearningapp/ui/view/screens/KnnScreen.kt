package com.aasjunior.machinelearningapp.ui.view.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.aasjunior.machinelearningapp.ui.view.components.Loading
import com.aasjunior.machinelearningapp.ui.view.components.TextLabel
import com.aasjunior.machinelearningapp.ui.view.components.Title
import com.aasjunior.machinelearningapp.ui.viewmodel.KnnViewModel

@Composable
fun KnnScreen(navController: NavHostController){
    val kvm: KnnViewModel = viewModel()
    val knnData by kvm.knnData.collectAsState()
    val loading = knnData == null


    if(loading){
        Loading()
    }else{
        LazyColumn(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            item { Title("Algoritmo KNN") }
            item { TextLabel(label = "Número de exemplos:", value = "${knnData?.number_of_examples}") }
            item { TextLabel(label = "Número de classes:", value = "${knnData?.number_of_classes}") }
            item { TextLabel(label = "Número de atributos:", value = "${knnData?.number_of_attributes}") }
            item { TextLabel(label = "Acurácia:", value = "${knnData?.accuracy}") }
            item {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(12.dp),
                    horizontalArrangement = Arrangement.End
                ){
                    Button(onClick = { navController.navigate("home") }) {
                        Text(text = "Voltar")
                    }
                }
            }
        }
    }
}