package com.aasjunior.machinelearningapp.domain.model

data class GeneticAlgorithmResponse(
    val size: Int,
    val n_childrens: Int,
    val n_generations: Int,
    val fitness: String,
    val plot_images: PlotImages
)
