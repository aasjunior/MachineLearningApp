package com.aasjunior.machinelearningapp.domain.model

data class KnnResponse(
    val number_of_examples: Int,
    val number_of_classes: Int,
    val number_of_attributes: Int,
    val accuracy: String
)
