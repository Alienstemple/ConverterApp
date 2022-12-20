package com.example.converterapp

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.converterapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val TAG = "MainActLog"
    lateinit var activityMainBinding: ActivityMainBinding
    lateinit var quantityAdapter: QuantityAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)

        // Initialize List, onClick, adapter for start screen RecyclerView
        val quantityList = ConverterRepository().availableValues.toMutableList()
        quantityAdapter = QuantityAdapter(object : OnClickListener {
            override fun onClick(quantity: Quantity, context: Context) {
                quantityShow(quantity, context)
            }

        })
        Log.d(TAG, "Quantity list is: ${quantityList}")
        quantityAdapter.setQuantityList(quantityList)

        activityMainBinding.apply {
            valuesListRecycler.layoutManager = LinearLayoutManager(this@MainActivity, RecyclerView.VERTICAL, false)
            valuesListRecycler.adapter = quantityAdapter
        }

        // Sorting
        activityMainBinding.sortValuesButton.setOnClickListener {
//            quantityList.sortedByDescending { it.label } // TODO even and odd clicks OR check is sorted
            quantityAdapter.setQuantityList(quantityList.sortedBy { it.label } as MutableList<Quantity>)
        }
    }


    private fun quantityShow(quantity: Quantity, context: Context) {
        Toast.makeText(this, quantity.label, Toast.LENGTH_SHORT).show()
        val intent = Intent(context, ConverterActivity::class.java)
        Log.d("MainActLog", "Context in quantityShow stores quant_label: ${quantity.label}")
        intent.putExtra("Quantity", quantity.label)  // Pass Quantity
        startActivity(intent)
    }

    interface OnClickListener {
        fun onClick(quantity: Quantity, context: Context)
    }
}
