package com.example.converterapp

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.converterapp.databinding.UnitConvertLayoutBinding

class ConverterActivity : AppCompatActivity() {
    lateinit var unitConvertLayoutBinding: UnitConvertLayoutBinding
    private lateinit var historyAdapter: HistoryAdapter

    private var currentHistoryNumber = 0

    private val currentQuantity: Quantity? by lazy { initCurrentQuantity()}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        unitConvertLayoutBinding = UnitConvertLayoutBinding.inflate(layoutInflater)
        setContentView(unitConvertLayoutBinding.root)

        val currentQuantity: Quantity = initCurrentQuantity()!!

        // Initialize from, to
        var convertingFromUnit: Unit = defaultInitUnit(currentQuantity)  // TODO Ask: after initialization of currentQuantity
        var convertingToUnit: Unit = defaultInitUnit(currentQuantity)

        // TextChanged listener
        unitConvertLayoutBinding.fromEditText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun onTextChanged(passedNumber: CharSequence?, p1: Int, p2: Int, p3: Int) {
                // Apply main convert logic
                // Here ConvertFromUnit, convertToUnit must be initialized
                if (passedNumber!!.isNotEmpty()) {
                    convert(passedNumber, convertingFromUnit, convertingToUnit)
                }
            }

            override fun afterTextChanged(p0: Editable?) {}
        })

        val spinnerData =
            currentQuantity?.unitList!!.map { unit -> unit.label }

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

                convertingFromUnit = initUnit(currentQuantity, selectedItem)!!

                // Call converter if new unit selected
                val passedNumber = unitConvertLayoutBinding.fromEditText.text
                if (passedNumber.isNotEmpty()) {
                    convert(passedNumber, convertingFromUnit, convertingToUnit)
                }

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

                convertingToUnit = initUnit(currentQuantity, selectedItem)!!

                // Call converter if new unit selected
                val passedNumber = unitConvertLayoutBinding.fromEditText.text
                if (passedNumber.isNotEmpty()) {
                    convert(passedNumber, convertingFromUnit, convertingToUnit)
                }

                Log.d("ConvActLog", "convertingToUnit initialized")
            }

            override fun onNothingSelected(parent: AdapterView<*>) {}
        }

        // History
        historyAdapter = HistoryAdapter()

        unitConvertLayoutBinding.apply {
            historyRecyclerView.layoutManager = LinearLayoutManager(this@ConverterActivity, RecyclerView.VERTICAL, false)
            historyRecyclerView.adapter = historyAdapter
        }

    }

    private fun initUnit(currentQuantity: Quantity, selectedItem: String) =
        currentQuantity?.unitList
            ?.find { it.label == selectedItem }

    private fun defaultInitUnit(currentQuantity: Quantity) = currentQuantity!!.unitList[0]

    private fun convert(passedNumber: CharSequence, convertingFromUnit: com.example.converterapp.Unit, convertingToUnit: com.example.converterapp.Unit) {
        val resultNumber = ConverterInteractor().plainConvert(
            passedNumber.toString().toDouble(),
            convertingFromUnit,
            convertingToUnit
        )
        // Is EditText.setText true?
        unitConvertLayoutBinding.toEditText.setText(resultNumber.toString())
        fillHistory()
    }

    private fun initCurrentQuantity() = ConverterRepository().availableValues.find {it .label == intent.getStringExtra("Quantity")}
    // Add new item to the top of the list and update adapter's list
    private fun fillHistory() {
        Log.d("ConvertActLog", "History filled.")
        val newHistoryItem: HistoryItem = collectInfoAboutConverting()
        historyAdapter.addHistoryItemToTop(newHistoryItem)
    }

    // Collects info from EditTests and spinners
    // Call everyTime after converting
    private fun collectInfoAboutConverting() = with (unitConvertLayoutBinding){
        HistoryItem(++currentHistoryNumber, fromEditText.text.toString(), spinnerFrom.selectedItem.toString(), toEditText.text.toString(), spinnerTo.selectedItem.toString())  // TODO position
    }
}

