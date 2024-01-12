package com.example.dessertclicker.ui

import androidx.lifecycle.ViewModel
import com.example.dessertclicker.data.Datasource
import com.example.dessertclicker.data.DessertUiState
import com.example.dessertclicker.model.Dessert
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class DessertViewModel: ViewModel() {
    private val _uiState = MutableStateFlow(DessertUiState())
    val uiState: StateFlow<DessertUiState> = _uiState.asStateFlow()

    val desserts = Datasource.dessertList

    init {
        resetDessert()
    }

    fun resetDessert() {
        _uiState.value = DessertUiState(0, 0, 0, desserts[0].price, desserts[0].imageId)
    }

    private fun determineDessertToShow(): Dessert {
        var dessertToShow = desserts.first()
        for (dessert in desserts) {
            if (uiState.value.dessertsSold >= dessert.startProductionAmount) {
                dessertToShow = dessert
            } else {
                break
            }
        }
        return dessertToShow
    }

    fun onDessertClicked(): Dessert {
        updateRevenueAndDessertsSold()
        return determineDessertToShow()
    }

    private fun updateRevenueAndDessertsSold() {
        val currentDessertPrice = _uiState.value.currentDessertPrice
        val currentRevenue = _uiState.value.revenue
        val newRevenue = currentRevenue + currentDessertPrice

        val currentSold = _uiState.value.dessertsSold

        _uiState.update { currentState->
            currentState.copy(
                revenue = newRevenue,
                dessertsSold = currentSold+1
            )
        }
    }


}