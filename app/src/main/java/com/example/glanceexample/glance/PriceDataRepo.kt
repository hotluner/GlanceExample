package com.example.glanceexample.glance

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlin.random.Random

object PriceDataRepo {
    var ticker = "GOOGL"
    private var previousPrice = 0f
    var change = 0
    private var _currentPrice = MutableStateFlow(0f)
    val currentPrice: StateFlow<Float> get() = _currentPrice
    fun update() {
        val newPrice = Random.nextInt(20, 35) + Random.nextFloat()
        // Avoid division by zero on the first update.
        change = if (previousPrice > 0f) {
            ((newPrice - previousPrice) / previousPrice * 100).toInt()
        } else {
            0
        }
        previousPrice = newPrice
        _currentPrice.value = newPrice
    }
}