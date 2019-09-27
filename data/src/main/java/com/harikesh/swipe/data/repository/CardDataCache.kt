package com.harikesh.swipe.data.repository

import io.reactivex.Completable
import io.reactivex.Single
import com.harikesh.swipe.data.model.CardDataEntity

/**
 * Interface defining methods for the caching of Card data. This is to be implemented by the
 * cache layer, using this interface as a way of communicating.
 */
interface CardDataCache {

    /**
     * Clear all cardData from the cache
     */
    fun clearCardData(): Completable

    /**
     * Save a given list of cardData to the cache
     */
    fun saveCardData(list: List<CardDataEntity>): Completable

    /**
     * Retrieve a list of cardData, from the cache
     */
    fun getCardData(): Single<List<CardDataEntity>>

    /**
     * Checks if an element (User) exists in the cache.

     * @param userId The id used to look for inside the cache.
     * *
     * @return true if the element is cached, otherwise false.
     */
    fun isCached(): Boolean

    /**
     * Checks if an element (User) exists in the cache.

     * @param userId The id used to look for inside the cache.
     * *
     * @return true if the element is cached, otherwise false.
     */
    fun setLastCacheTime(lastCache: Long)

    /**
     * Checks if the cache is expired.

     * @return true, the cache is expired, otherwise false.
     */
    fun isExpired(): Boolean

}