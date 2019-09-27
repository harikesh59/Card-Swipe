package com.harikesh.swipe.presentation.mapper

import com.harikesh.swipe.domain.model.CardData
import com.harikesh.swipe.presentation.model.CardDataView
import javax.inject.Inject

/**
 * Map a [CardDataView] to and from a [CardData] instance when data is moving between
 * this layer and the Domain layer
 */
open class CardDataMapper @Inject constructor(): Mapper<CardDataView, CardData> {

    /**
     * Map a [CardData] instance to a [CardDataView] instance
     */
    override fun mapToView(type: CardData): CardDataView {
        return CardDataView(type.id, type.text)
    }


}