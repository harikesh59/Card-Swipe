package com.harikesh.swipe.remote

import io.reactivex.Single
import com.harikesh.swipe.data.model.CardDataEntity
import com.harikesh.swipe.data.repository.CardDataRemote
import com.harikesh.swipe.remote.mapper.PojoEntityMapper
import javax.inject.Inject

/**
 * Remote implementation for retrieving CardData instances. This class implements the
 * [CardDataRemote] from the Data layer as it is that layers responsibility for defining the
 * operations in which data store implementation layers can carry out.
 */
class CardDataRemoteImpl @Inject constructor(private val swipeApi: SwipeApi,
                                             private val entityMapper: PojoEntityMapper) :
        CardDataRemote {

    /**
     * Retrieve a list of [CardDataEntity] instances from the [SwipeApi].
     */
    override fun getCardData(): Single<List<CardDataEntity>> {
        return swipeApi.getCardData()
                .map {
                    it.data.map { listItem ->
                        entityMapper.mapFromRemote(listItem)
                    }
                }
    }

}