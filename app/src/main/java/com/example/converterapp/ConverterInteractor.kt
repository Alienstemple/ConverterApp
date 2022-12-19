package com.example.converterapp

import android.util.Log

class ConverterInteractor {
    fun plainConvert(converterValue: Double, fromUnit: Unit, toUnit: Unit): Double {
        Log.d("ConvertInteractLog", "From $fromUnit to $toUnit")
        return converterValue * fromUnit.fromBaseRate * toUnit.toBaseRate
    }
}