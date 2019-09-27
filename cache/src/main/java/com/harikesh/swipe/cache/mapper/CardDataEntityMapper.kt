package com.harikesh.swipe.cache.mapper

import com.harikesh.swipe.cache.model.CachedCardData
import com.harikesh.swipe.data.model.CardDataEntity
import javax.inject.Inject

/**
 * Map a [CachedCardData] instance to and from a [CardDataEntity] instance when data is moving between
 * this later and the Data layer
 */
class CardDataEntityMapper @Inject constructor() : EntityMapper<CachedCardData, CardDataEntity> {

    /**
     * Map a [CardDataEntity] instance to a [CachedCardData] instance
     */
    override fun mapToCached(type: CardDataEntity): CachedCardData {
        return CachedCardData(type.id, type.text)
    }

    /**
     * Map a [CachedCardData] instance to a [CardDataEntity] instance
     */
    override fun mapFromCached(type: CachedCardData): CardDataEntity {
        return CardDataEntity(type.id, type.text)
    }

}