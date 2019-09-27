package com.harikesh.swipe.remote.mapper

import com.harikesh.swipe.data.model.CardDataEntity
import com.harikesh.swipe.remote.model.CardModel
import javax.inject.Inject

/**
 * Map a [CardModel] to and from a [CardDataEntity] instance when data is moving between
 * this later and the Data layer
 */
open class PojoEntityMapper @Inject constructor(): EntityMapper<CardModel, CardDataEntity> {

    /**
     * Map an instance of a [CardModel] to a [CardDataEntity] model
     */
    override fun mapFromRemote(type: CardModel): CardDataEntity {
        return CardDataEntity(type.id, type.text)
    }

}