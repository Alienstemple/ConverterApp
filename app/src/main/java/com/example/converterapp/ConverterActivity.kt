package com.example.converterapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import com.example.converterapp.databinding.UnitConvertLayoutBinding

class ConverterActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener {
    lateinit var unitConvertLayoutBinding: UnitConvertLayoutBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        unitConvertLayoutBinding = UnitConvertLayoutBinding.inflate(layoutInflater)
        setContentView(unitConvertLayoutBinding.root)

        val spinnerFromData = ConverterRepository().availableValues[0].unitList  // TODO getExtra label of Quantity

        val testSpinnerData = listOf("one", "two")
        // Spiner from
        val spinnerFrom: Spinner = unitConvertLayoutBinding.spinnerFrom
        val spinnerFromArrayAdapter = ArrayAdapter(this,
            R.layout.spinner_item,
            testSpinnerData
        )
        spinnerFrom.adapter = spinnerFromArrayAdapter

    }

    override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {

        Toast.makeText(this, "SpinnerItem selected", Toast.LENGTH_SHORT).show()
    }

    override fun onNothingSelected(p0: AdapterView<*>?) {
        TODO("Not yet implemented")
    }
}