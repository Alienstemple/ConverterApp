package com.example.converterapp

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.converterapp.databinding.HistoryItemBinding

class HistoryAdapter()
    : RecyclerView.Adapter<HistoryAdapter.ViewHolder>() {

    private val TAG = "HistoryAdaptLog"

    private val historyList = mutableListOf<HistoryItem>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.history_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(historyList[position])
    }

    override fun getItemCount() = historyList.size

    fun setHistoryList(passedHistoryList: ArrayList<HistoryItem>) {
        val diffUtilCallback = HistoryDiffUtil(historyList, passedHistoryList)
        val diffResult = DiffUtil.calculateDiff(diffUtilCallback)
        historyList.apply {
            clear()
            addAll(passedHistoryList)
            Log.d(TAG, "Passed to history list: $passedHistoryList")
        }
        Log.d(TAG, "Inner list: $historyList")
        diffResult.dispatchUpdatesTo(this)
    }

    fun addHistoryItemToTop(item: HistoryItem) {
        historyList.add(0, item)
        notifyItemInserted(0)
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val historyItemBinding = HistoryItemBinding.bind(view)

        fun bind(historyItem: HistoryItem) = with(historyItemBinding) {
            historyItemBinding.historyItemTextView.text = historyItem.historyItem
            Log.d("HistoryAdaptLog", "Binded HistoryItem with name $historyItem")
        }
    }
}