package com.aasjunior.machinelearningapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.aasjunior.machinelearningapp.ui.theme.MachineLearningAppTheme
import com.aasjunior.machinelearningapp.ui.view.screens.GeneticAlgorithmScreen
import com.aasjunior.machinelearningapp.ui.view.screens.HomeScreen
import com.aasjunior.machinelearningapp.ui.view.screens.KnnScreen
import com.aasjunior.machinelearningapp.ui.viewmodel.HomeViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val hvm: HomeViewModel = viewModel()
            val navController: NavHostController = rememberNavController()

            MachineLearningAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    NavHost(navController, startDestination = "home") {
                        composable("home") { HomeScreen(navController, hvm) }
                        composable("genetic-algorithm") { GeneticAlgorithmScreen(navController) }
                        composable("test-knn") { KnnScreen(navController) }
                    }
                }
            }
        }
    }
}