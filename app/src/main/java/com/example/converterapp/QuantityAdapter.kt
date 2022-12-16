package com.example.converterapp

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.converterapp.databinding.QuantityItemCardBinding

class QuantityAdapter(private val onClickListener: MainActivity.OnClickListener)
    : RecyclerView.Adapter<QuantityAdapter.ViewHolder>() {
    private val TAG = "HtmlMultiViewAdaptLog"

    private val quantityList = mutableListOf<Quantity>()
//    private val

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val quantityItemBinding = QuantityItemCardBinding.bind(view)

        fun bind(quantityItem: Quantity) = with(quantityItemBinding) {
            val quantityName = itemView.context.getString(quantityItem.label)
            quantityItemTextView.text = quantityName   // FIXME  Only first item pased
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

    fun setQuantityList(passedQuantityList: List<Quantity>) {
//        val diffUtilCallback = HtmlColorDiffUtil(colorGeneralList, colorlist)
//        val diffResult = DiffUtil.calculateDiff(diffUtilCallback)
        quantityList.apply {
            clear()
            addAll(passedQuantityList)
        }
//        diffResult.dispatchUpdatesTo(this)
    }
}

