package com.example.converterapp

class ConverterInteractor {
    fun plainConvert(converterValue: Double, fromUnit: Unit, toUnit: Unit): Double {
        return converterValue * fromUnit.fromBaseRate * toUnit.toBaseRate
    }
}