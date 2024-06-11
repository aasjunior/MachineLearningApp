package com.aasjunior.machinelearningapp.ui.view.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.aasjunior.machinelearningapp.ui.view.components.Figure
import com.aasjunior.machinelearningapp.ui.view.components.Loading
import com.aasjunior.machinelearningapp.ui.view.components.TextLabel
import com.aasjunior.machinelearningapp.ui.view.components.Title
import com.aasjunior.machinelearningapp.ui.viewmodel.GeneticAlgorithmViewModel

@Composable
fun GeneticAlgorithmScreen(navController: NavHostController){
    val gavm: GeneticAlgorithmViewModel = viewModel()
    val geneticAlgorithmData by gavm.geneticAlgorithmData.collectAsState()
    val plotFitnessBase64 = gavm.geneticAlgorithmData.value?.plot_images?.plot_fitness
    val plotEvolutionBase64 = gavm.geneticAlgorithmData.value?.plot_images?.plot_evolution
    val loading = geneticAlgorithmData == null

    if(loading){
        Loading()
    }else{
        LazyColumn(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            item { Title("Algoritmo Genético") }
            item { TextLabel(label = "Tamanho da população:", value = "${geneticAlgorithmData?.size}") }
            item { TextLabel(label = "Número de gerações:", value = "${geneticAlgorithmData?.n_generations}") }
            item { TextLabel(label = "Número de filhos:", value = "${geneticAlgorithmData?.n_childrens}") }
            item {
                Text(text = "Função custo (fitness):", fontWeight = FontWeight.Bold)
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 10.dp),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(text = "${geneticAlgorithmData?.fitness}")
                }
            }

            item {
                Figure(
                    title = "Superfície da função custo",
                    plotBase64 = plotFitnessBase64
                )
            }

            item {
                Figure(
                    title = "Evolução Temporal",
                    plotBase64 = plotEvolutionBase64
                )
            }

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