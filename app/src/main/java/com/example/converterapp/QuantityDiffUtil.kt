package com.example.converterapp

import androidx.recyclerview.widget.DiffUtil

class QuantityDiffUtil(
    private val oldList: MutableList<Quantity>,
    private val newList: MutableList<Quantity>
) : DiffUtil.Callback() {
    override fun getOldListSize() = oldList.size

    override fun getNewListSize() = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int) =
        oldList[oldItemPosition] == newList[newItemPosition]

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int) =
        oldList[oldItemPosition] == newList[newItemPosition]
}