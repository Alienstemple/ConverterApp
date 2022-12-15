package com.example.converterapp

import com.example.converterapp.Unit

class ConverterRepository {
    val availableValues: List<Quantity> = listOf(
        Quantity("Скорость", listOf(   // R.string.velocity
            Unit("м/с", 1.0),
            Unit("км/ч", 3.6)
        ))
    )
        get
        () = field
}