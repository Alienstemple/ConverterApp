package com.example.converterapp

import android.content.Context
import com.example.converterapp.Unit

class ConverterRepository() {
    var availableValues: List<Quantity> = listOf(
        Quantity("Скорость", listOf(   // R.string.velocity
            Unit("м/с", 1.0),
            Unit("км/ч", 3.6),
            Unit("км/с", 0.001),
            Unit("миль/ч", 2.23693629)
        )),
        Quantity("Площадь", listOf(   // R.string.square
            Unit("кв.м.", 1.0),
            Unit("гектар", 0.0001),
            Unit("кв.ярд", 1.19599005),
            Unit("кв.см.", 10000.0)
        )),
        Quantity("Объем", listOf(   // R.string.square
            Unit("куб.м.", 1.0),
            Unit("куб.см.", 1000000.0),
            Unit("л", 1000.0)
        )),
        Quantity("Масса", listOf(   // R.string.square
            Unit("кг.", 1.0),
            Unit("г", 1000.0),
            Unit("т", 0.001),
            Unit("центнер", 0.01),
            Unit("фунт", 2.20462262)
        ))
    )

}