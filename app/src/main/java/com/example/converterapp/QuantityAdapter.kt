package com.example.converterapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.converterapp.databinding.QuantityItemCardBinding

class QuantityAdapter(private val onClickListener: OnClickListener)
    : RecyclerView.Adapter<QuantityAdapter.ViewHolder>() {
    private val TAG = "HtmlMultiViewAdaptLog"

    private val quantityList = mutableListOf<Quantity>()

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val quantityItemBinding = QuantityItemCardBinding.bind(view)

        fun bind(quantityItem: Quantity) = with(quantityItemBinding) {
            quantityItemTextView.text = quantityItem.label // TODO pass ViewGroup context to adapter
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.quantity_item_card, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemView.setOnClickListener(
            onClickListener.onClick()
        )
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

    class OnClickListener(val clickListener: (quantity: Quantity) -> kotlin.Unit) {
        fun onClick(quantity: Quantity) = clickListener(quantity)
    }
}

