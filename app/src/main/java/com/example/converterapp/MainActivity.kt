package com.example.converterapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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

        val quantityList = ConverterRepository().availableValues
        quantityAdapter = QuantityAdapter(object : OnClickListener {
            override fun onClick(quantity: Quantity) {
                quantityShow(quantity)
            }

        })
        quantityAdapter.setQuantityList(quantityList)

        activityMainBinding.apply {
            valuesListRecycler.layoutManager = LinearLayoutManager(this@MainActivity, RecyclerView.VERTICAL, false)
            valuesListRecycler.adapter = quantityAdapter
        }
    }

    private fun quantityShow(quantity: Quantity) {
        Toast.makeText(this, quantity.label, Toast.LENGTH_SHORT).show()
    }

    interface OnClickListener {
        fun onClick(quantity: Quantity)
    }
}
