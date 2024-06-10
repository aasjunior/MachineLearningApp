package com.aasjunior.machinelearningapp.ui.view.components

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Base64
import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.asImageBitmap


@Composable
fun ImageFromBase64(base64: String) {
    val imageBitmap = remember(base64) {
        val imageBytes = Base64.decode(base64, Base64.DEFAULT)
        BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.size)
    }.asImageBitmap()

    Image(bitmap = imageBitmap, contentDescription = null)
}

@Composable
fun ImageFromBase64String(base64String: String) {
    val imageBitmap = remember {
        mutableStateOf(loadImageFromBase64(base64String))
    }

    imageBitmap.value?.let { bitmap ->
        Image(bitmap = bitmap.asImageBitmap(), contentDescription = "")
    }
}

fun loadImageFromBase64(base64String: String): Bitmap {
    val decodedString = Base64.decode(base64String, Base64.DEFAULT)
    return BitmapFactory.decodeByteArray(decodedString, 0, decodedString.size)
}