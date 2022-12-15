package com.example.converterapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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
        quantityAdapter = QuantityAdapter(object : QuantityListener{
            override fun onQuantitySelected() {
               // TODO switch to next screen
            }
        })
        quantityAdapter.setQuantityList(quantityList)

        activityMainBinding.apply {
            valuesListRecycler.layoutManager = LinearLayoutManager(this@MainActivity, RecyclerView.VERTICAL, false)
            valuesListRecycler.adapter = quantityAdapter
        }
    }

}

interface QuantityListener {
    fun onQuantitySelected()  // TODO params?
}