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
        quantityAdapter = QuantityAdapter(QuantityAdapter.OnClickListener {
            Toast.makeText(applicationContext, "Item clicked", Toast.LENGTH_SHORT).show()
        })
        quantityAdapter.setQuantityList(quantityList)

        activityMainBinding.apply {
            valuesListRecycler.layoutManager = LinearLayoutManager(this@MainActivity, RecyclerView.VERTICAL, false)
            valuesListRecycler.adapter = quantityAdapter
        }
    }
}
