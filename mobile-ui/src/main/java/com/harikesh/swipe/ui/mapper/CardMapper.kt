package com.harikesh.swipe.ui.mapper

import com.harikesh.swipe.presentation.model.CardDataView
import com.harikesh.swipe.ui.model.CardDataViewModel
import javax.inject.Inject

/**
 * Map a [CardDataView] to and from a [CardDataViewModel] instance when data is moving between
 * this layer and the Domain layer
 */
open class CardMapper @Inject constructor() : Mapper<CardDataViewModel, CardDataView> {

    /**
     * Map a [CardDataView] instance to a [CardDataViewModel] instance
     */
    override fun mapToViewModel(type: CardDataView): CardDataViewModel {
        return CardDataViewModel(type.id, type.text)
    }

}