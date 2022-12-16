package com.example.converterapp

import android.content.Context
import android.content.Intent
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
            override fun onClick(quantity: Quantity, context: Context) {
                quantityShow(quantity, context)
            }

        })
        quantityAdapter.setQuantityList(quantityList)

        activityMainBinding.apply {
            valuesListRecycler.layoutManager = LinearLayoutManager(this@MainActivity, RecyclerView.VERTICAL, false)
            valuesListRecycler.adapter = quantityAdapter
        }
    }

    private fun quantityShow(quantity: Quantity, context: Context) {
        Toast.makeText(this, quantity.label, Toast.LENGTH_SHORT).show()
        val intent = Intent(context, ConverterActivity::class.java)
//        intent.putExtra()  // TODO pass Quantity
        intent.putExtra("Quantity label", quantity.label)
        startActivity(intent)
    }

    interface OnClickListener {
        fun onClick(quantity: Quantity, context: Context)
    }
}
