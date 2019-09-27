package com.harikesh.swipe.data.source

import io.reactivex.Completable
import io.reactivex.Single
import com.harikesh.swipe.data.model.CardDataEntity
import com.harikesh.swipe.data.repository.CardDataCache
import com.harikesh.swipe.data.repository.CardDataStore
import javax.inject.Inject

/**
 * Implementation of the [CardDataStore] interface to provide a means of communicating
 * with the local data source
 */
open class CardCacheDataStore @Inject constructor(private val cardDataCache: CardDataCache) :
        CardDataStore {

    /**
     * Clear all cardData from the cache
     */
    override fun clearCardData(): Completable {
        return cardDataCache.clearCardData()
    }

    /**
     * Save a given [List] of [CardDataEntity] instances to the cache
     */
    override fun saveCardData(list: List<CardDataEntity>): Completable {
        return cardDataCache.saveCardData(list)
                .doOnComplete {
                    cardDataCache.setLastCacheTime(System.currentTimeMillis())
                }
    }

    /**
     * Retrieve a list of [CardDataEntity] instance from the cache
     */
    override fun getCardData(): Single<List<CardDataEntity>> {
        return cardDataCache.getCardData()
    }

}