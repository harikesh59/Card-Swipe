package com.harikesh.swipe.data.mapper

import com.harikesh.swipe.data.model.CardDataEntity
import com.harikesh.swipe.domain.model.CardData
import javax.inject.Inject


/**
 * Map a [CardDataEntity] to and from a [CardData] instance when data is moving between
 * this later and the Domain layer
 */
open class CardDataMapper @Inject constructor(): Mapper<CardDataEntity, CardData> {

    /**
     * Map a [CardDataEntity] instance to a [CardData] instance
     */
    override fun mapFromEntity(type: CardDataEntity): CardData {
        return CardData(type.id, type.text)
    }

    /**
     * Map a [CardData] instance to a [CardDataEntity] instance
     */
    override fun mapToEntity(type: CardData): CardDataEntity {
        return CardDataEntity(type.id, type.text)
    }


}