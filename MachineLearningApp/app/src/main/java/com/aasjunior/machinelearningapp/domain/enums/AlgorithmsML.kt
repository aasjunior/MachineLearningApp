package com.aasjunior.machinelearningapp.domain.enums

enum class AlgorithmsML(val description: String){
    KNN("KNN"),
    DecisionTree("Arvore de Decisão"),
    GeneticAlgorithm("Algoritmo Genético");

    companion object{
        fun fromDescription(description: String): AlgorithmsML? {
            return values().find { it.description == description }
        }
    }
}