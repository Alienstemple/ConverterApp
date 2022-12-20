package com.example.converterapp

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.converterapp.databinding.QuantityItemCardBinding

class QuantityAdapter(private val onClickListener: MainActivity.OnClickListener)
    : RecyclerView.Adapter<QuantityAdapter.ViewHolder>() {
    private val TAG = "QuantAdaptLog"

    private val quantityList = mutableListOf<Quantity>()

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val quantityItemBinding = QuantityItemCardBinding.bind(view)

        fun bind(quantityItem: Quantity) = with(quantityItemBinding) {
            val quantityName = quantityItem.label
            quantityItemTextView.text = quantityName
            Log.d("QuantityAdaptLog", "Binded Quantity with name $quantityName")
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.quantity_item_card, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val context = holder.itemView.context  // Here context of view is known. Pass it to onClick

        holder.itemView.setOnClickListener{
            onClickListener.onClick(quantityList[position], context)
        }

        holder.bind(quantityList[position])
    }

    override fun getItemCount() = quantityList.size

    fun setQuantityList(passedQuantityList: MutableList<Quantity>) {
        val diffUtilCallback = QuantityDiffUtil(quantityList, passedQuantityList)
        val diffResult = DiffUtil.calculateDiff(diffUtilCallback)
        quantityList.apply {
            clear()
            addAll(passedQuantityList)
            Log.d(TAG, "Passed to adapter list: $passedQuantityList")
        }
        Log.d(TAG, "Inner list: $quantityList")
        diffResult.dispatchUpdatesTo(this)
    }
}

