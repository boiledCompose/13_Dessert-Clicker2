package com.example.dessertclicker.data

import androidx.annotation.StringRes

data class DessertUiState(
    val revenue:Int = 0,
    val dessertsSold:Int = 0,
    val currentDessertIndex:Int = 0,
    val currentDessertPrice:Int = 0,
    @StringRes val currentDessertImageId:Int ?= null
)
