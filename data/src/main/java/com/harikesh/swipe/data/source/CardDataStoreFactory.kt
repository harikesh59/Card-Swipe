package com.harikesh.swipe.data.source

import com.harikesh.swipe.data.repository.CardDataCache
import com.harikesh.swipe.data.repository.CardDataStore
import javax.inject.Inject

/**
 * Create an instance of a CardDataStore
 */
open class CardDataStoreFactory @Inject constructor(
        private val cardDataCache: CardDataCache,
        private val cacheDataStore: CardCacheDataStore,
        private val remoteDataStore: CardRemoteDataStore) {

    /**
     * Returns a DataStore based on whether or not there is content in the cache and the cache
     * has not expired
     */
    open fun retrieveDataStore(): CardDataStore {
        if (cardDataCache.isCached() && !cardDataCache.isExpired()) {
            return retrieveCacheDataStore()
        }
        return retrieveRemoteDataStore()
    }

    /**
     * Return an instance of the Remote Data Store
     */
    open fun retrieveCacheDataStore(): CardDataStore {
        return cacheDataStore
    }

    /**
     * Return an instance of the Cache Data Store
     */
    open fun retrieveRemoteDataStore(): CardDataStore {
        return remoteDataStore
    }

}