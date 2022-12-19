package com.example.converterapp

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity
import com.example.converterapp.databinding.UnitConvertLayoutBinding

class ConverterActivity : AppCompatActivity() {
    lateinit var unitConvertLayoutBinding: UnitConvertLayoutBinding

    // Initialize from, to. Suppose, wrong
    var convertingFromUnit: Unit = defaultInitUnit()   // TODO move out of here! // TODO by lazy - default values
    var convertingToUnit: Unit = defaultInitUnit()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        unitConvertLayoutBinding = UnitConvertLayoutBinding.inflate(layoutInflater)
        setContentView(unitConvertLayoutBinding.root)

//        intent.getIntExtra() // TODO how to fetch context from intent (resources, R)

        // TextChanged listener
        unitConvertLayoutBinding.fromEditText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun onTextChanged(passedNumber: CharSequence?, p1: Int, p2: Int, p3: Int) {
                // TODO toDouble - null safety, check input
                // Apply main convert logic
                // Here ConvertFromUnit, convertToUnit must be initialized
                if (passedNumber!!.isNotEmpty()) {
                    var resultNumber = ConverterInteractor().plainConvert(
                        passedNumber.toString().toDouble(),
                        convertingFromUnit,
                        convertingToUnit
                    )
                    // Is EditText.setText true?
                    unitConvertLayoutBinding.toEditText.setText(resultNumber.toString())
                }

            }

            override fun afterTextChanged(p0: Editable?) {}
        })

        val spinnerData =
            ConverterRepository().availableValues[0].unitList.map { unit -> unit.label }

        val testSpinnerData = listOf("one", "two")  // TODO list of Unit.label items
        // Spiner from
        val spinnerFrom: Spinner = unitConvertLayoutBinding.spinnerFrom
        val spinnerFromArrayAdapter = ArrayAdapter(
            this,
            R.layout.spinner_item,
            spinnerData
        )
        spinnerFrom.adapter = spinnerFromArrayAdapter
        spinnerFrom.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View,
                position: Int,
                id: Long
            ) {
                val selectedItem = parent.getItemAtPosition(position).toString()

                convertingFromUnit = initUnit(selectedItem)!!
                Log.d("ConvActLog", "convertingFromUnit initialized")
            }

            override fun onNothingSelected(parent: AdapterView<*>) {}
        }

        // Spiner to
        val spinnerTo: Spinner = unitConvertLayoutBinding.spinnerTo
        val spinnerToArrayAdapter = ArrayAdapter(
            this,
            R.layout.spinner_item,
            spinnerData
        )
        spinnerTo.adapter = spinnerToArrayAdapter
        spinnerTo.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View,
                position: Int,
                id: Long
            ) {
                val selectedItem = parent.getItemAtPosition(position).toString()

                convertingToUnit = initUnit(selectedItem)!!

                Log.d("ConvActLog", "convertingToUnit initialized")

            }

            override fun onNothingSelected(parent: AdapterView<*>) {}
        }

    }

    private fun initUnit(selectedItem: String) =
        ConverterRepository().availableValues[0].unitList
            .find { it.label == selectedItem }  // TODO pass index of Quantity

    private fun defaultInitUnit() = ConverterRepository().availableValues[0].unitList[0]
}