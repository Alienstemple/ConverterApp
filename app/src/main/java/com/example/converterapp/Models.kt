package com.example.converterapp

import android.os.Parcelable
import androidx.annotation.StringRes

/**
 * Физическая величина, имеет название label, хранит список единиц измерения unitList с коэффициентами
 */
//@Parcelize   // TODO 1 import, 2 try without @
data class Quantity (@StringRes val label: Int, val unitList: List<Unit>) //: Parcelable

/**
 * Единица измерения. Имеет название label,
 * хранит коэффициент перевода в основную величину toBaseRate,
 * обратный коэффициент перевода fromBaseRate
 */
data class Unit (@StringRes val label: Int, val toBaseRate: Double) {
    private val fromBaseRate: Double = 1.0 / toBaseRate
}

/**
 * Числовое значение, передаваемое на конвертацию
 * Хранит физическую величину converterQuantity,
 * непосредственно значение value
 */
data class ConverterValue(val converterQuantity: Quantity)