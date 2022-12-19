package com.example.converterapp

import android.content.Context
import com.example.converterapp.Unit

class ConverterRepository() {
    var availableValues: List<Quantity> = listOf(
        Quantity("Скорость", listOf(   // R.string.velocity
            Unit("м/с", 1.0),
            Unit("км/ч", 3.6)
        )),
        Quantity("Площадь", listOf(   // R.string.square
            Unit("кв.м.", 1.0),
            Unit("гектар", 0.0001)
        ))
    )

}