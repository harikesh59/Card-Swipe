package com.harikesh.swipe.ui.browse

import androidx.recyclerview.widget.DiffUtil
import com.harikesh.swipe.presentation.model.CardDataView
import com.harikesh.swipe.ui.model.CardDataViewModel

/**
 * Created by Harikesh on 9/26/2019.
 */
class SpotDiffCallback(
        private val old: List<CardDataViewModel>,
        private val new: List<CardDataViewModel>
) : DiffUtil.Callback() {

    override fun getOldListSize(): Int {
        return old.size
    }

    override fun getNewListSize(): Int {
        return new.size
    }

    override fun areItemsTheSame(oldPosition: Int, newPosition: Int): Boolean {
        return old[oldPosition].id == new[newPosition].id
    }

    override fun areContentsTheSame(oldPosition: Int, newPosition: Int): Boolean {
        return old[oldPosition] == new[newPosition]
    }

}