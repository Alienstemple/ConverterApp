package com.example.converterapp

import com.example.converterapp.Unit

class ConverterRepository {
    val availableValues: List<Quantity> = listOf(
        Quantity(R.string.velocity, listOf(
            Unit(R.string.mps, 1.0),
            Unit(R.string.kmph, 3.6)
        ))
    )
}