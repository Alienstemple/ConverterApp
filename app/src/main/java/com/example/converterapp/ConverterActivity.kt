package com.example.converterapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.converterapp.databinding.UnitConvertLayoutBinding

class ConverterActivity : AppCompatActivity() {
    lateinit var unitConvertLayoutBinding: UnitConvertLayoutBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        unitConvertLayoutBinding = UnitConvertLayoutBinding.inflate(layoutInflater)
        setContentView(unitConvertLayoutBinding.root)
    }
}