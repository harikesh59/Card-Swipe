package com.harikesh.swipe.ui.browse

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.harikesh.swipe.ui.R
import com.harikesh.swipe.ui.model.CardDataViewModel
import javax.inject.Inject

class BrowseAdapter @Inject constructor() : RecyclerView.Adapter<BrowseAdapter.ViewHolder>() {

    var cardData: List<CardDataViewModel> = emptyList()

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val dataViewModel = cardData[position]
        holder.nameText.text = dataViewModel.text
    }

    fun getCardList(): List<CardDataViewModel> {
        return cardData
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater
                .from(parent.context)
                .inflate(R.layout.item_card, parent, false)
        return ViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return cardData.size
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var nameText: TextView = view.findViewById(R.id.text_name)
    }

}