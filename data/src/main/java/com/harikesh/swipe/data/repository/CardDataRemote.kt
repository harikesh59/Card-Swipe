package com.harikesh.swipe.data.repository

import io.reactivex.Single
import com.harikesh.swipe.data.model.CardDataEntity

/**
 * Interface defining methods for the caching of Card Data. This is to be implemented by the
 * cache layer, using this interface as a way of communicating.
 */
interface CardDataRemote {

    /**
     * Retrieve a list of card, from the cache
     */
    fun getCardData(): Single<List<CardDataEntity>>

}