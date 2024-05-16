package com.aasjunior.machinelearningapp.domain.model

import java.util.jar.Attributes

data class DataModel(
    val src: String,
    val attributeHeaders: Set<String>,
    val classHeader: String
){}