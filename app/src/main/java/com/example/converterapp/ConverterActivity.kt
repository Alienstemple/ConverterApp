package com.example.converterapp

import android.content.Intent
import android.content.res.Resources
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import com.example.converterapp.databinding.UnitConvertLayoutBinding

class ConverterActivity : AppCompatActivity() {
    lateinit var unitConvertLayoutBinding: UnitConvertLayoutBinding

    lateinit var convertingFromUnit: Unit   // TODO move out of here!
    lateinit var convertingToUnit: Unit

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        unitConvertLayoutBinding = UnitConvertLayoutBinding.inflate(layoutInflater)
        setContentView(unitConvertLayoutBinding.root)

//        intent.getIntExtra() // TODO how to fetch context from intent (resources, R)

        unitConvertLayoutBinding.fromEditText.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun onTextChanged(passedNumber: CharSequence?, p1: Int, p2: Int, p3: Int) {
                // TODO toDouble - null safety, check input
                var resultNumber = ConverterInteractor().plainConvert(passedNumber.toString().toDouble(), convertingFromUnit, convertingToUnit)
                unitConvertLayoutBinding.toEditText.setText(resultNumber.toString())
            }

            override fun afterTextChanged(p0: Editable?) {}
        })

        val spinnerFromData = ConverterRepository().availableValues[0].unitList  // TODO getExtra label of Quantity

        val testSpinnerData = listOf("one", "two")  // TODO list of Unit.label items
        // Spiner from
        val spinnerFrom: Spinner = unitConvertLayoutBinding.spinnerFrom
        val spinnerFromArrayAdapter = ArrayAdapter(this,
            R.layout.spinner_item,
            testSpinnerData
        )
        spinnerFrom.adapter = spinnerFromArrayAdapter
        spinnerFrom.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                val selectedItem = parent.getItemAtPosition(position).toString()
                if (selectedItem == "one") {
                    Toast.makeText(this@ConverterActivity, "One selected", Toast.LENGTH_SHORT).show()
                    convertingFromUnit = Unit(R.string.mps, 1.0)  // Here we initialize from which unit we will convert
                    Log.d("ConvActLog", "convertingFromUnit initialized")
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>) {}
        }

        // Spiner to
        val spinnerTo: Spinner = unitConvertLayoutBinding.spinnerTo
        val spinnerToArrayAdapter = ArrayAdapter(this,
            R.layout.spinner_item,
            testSpinnerData
        )
        spinnerTo.adapter = spinnerToArrayAdapter
        spinnerTo.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                val selectedItem = parent.getItemAtPosition(position).toString()
                if (selectedItem == "two") {
                    Toast.makeText(this@ConverterActivity, "Two selected", Toast.LENGTH_SHORT).show()
                    convertingToUnit = Unit(R.string.kmph, 3.6)  // Here we initialize to which unit we will convert
                    Log.d("ConvActLog", "convertingToUnit initialized")
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>) {}
        }

    }
}