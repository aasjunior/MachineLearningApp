package com.aasjunior.machinelearningapp.domain.model

data class DataScheme(
    val src: String,
    val attributeHeaders: Set<String>,
    val classHeader: String
){}