package com.example.converterapp

import android.content.Context
import com.example.converterapp.Unit

class ConverterRepository() {
    var availableValues: List<Quantity> = listOf(
        Quantity(R.string.velocity, listOf(   // R.string.velocity
            Unit(R.string.mps, 1.0),
            Unit(R.string.kmph, 3.6)
        )),
        Quantity(R.string.square, listOf(   // R.string.square
            Unit(R.string.sqm, 1.0),
            Unit(R.string.ha, 0.0001)
        ))
    )

}