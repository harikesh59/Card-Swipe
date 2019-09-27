package com.harikesh.swipe.cache

import android.database.sqlite.SQLiteDatabase
import io.reactivex.Completable
import io.reactivex.Single
import com.harikesh.swipe.cache.db.Db
import com.harikesh.swipe.cache.db.DbOpenHelper
import com.harikesh.swipe.cache.db.constants.CardConstants
import com.harikesh.swipe.cache.db.mapper.LocalCardDataMapper
import com.harikesh.swipe.cache.mapper.CardDataEntityMapper
import com.harikesh.swipe.cache.model.CachedCardData
import com.harikesh.swipe.data.model.CardDataEntity
import com.harikesh.swipe.data.repository.CardDataCache
import javax.inject.Inject

/**
 * Cached implementation for retrieving and saving CardData instances. This class implements the
 * [CardDataCache] from the Data layer as it is that layers responsibility for defining the
 * operations in which data store implementation layers can carry out.
 */
class CardDataCacheImpl @Inject constructor(dbOpenHelper: DbOpenHelper,
                                            private val entityMapper: CardDataEntityMapper,
                                            private val mapper: LocalCardDataMapper,
                                            private val preferencesHelper: PreferencesHelper):
        CardDataCache {

    private val EXPIRATION_TIME = (60 * 10 * 1000).toLong()

    private var database: SQLiteDatabase = dbOpenHelper.writableDatabase

    /**
     * Retrieve an instance from the database, used for tests
     */
    internal fun getDatabase(): SQLiteDatabase {
        return database
    }

    /**
     * Remove all the data from all the tables in the database.
     */
    override fun clearCardData(): Completable {
        return Completable.defer {
            database.beginTransaction()
            try {
                database.delete(Db.CardDataTable.TABLE_NAME, null, null)
                database.setTransactionSuccessful()
            } finally {
                database.endTransaction()
            }
            Completable.complete()
        }
    }

    /**
     * Save the given list of [CardDataEntity] instances to the database.
     */
    override fun saveCardData(list: List<CardDataEntity>): Completable {
        return Completable.defer {
            database.beginTransaction()
            try {
                list.forEach {
                    saveCardData(entityMapper.mapToCached(it))
                }
                database.setTransactionSuccessful()
            } finally {
                database.endTransaction()
            }
            Completable.complete()
        }
    }

    /**
     * Retrieve a list of [CardDataEntity] instances from the database.
     */
    override fun getCardData(): Single<List<CardDataEntity>> {
        return Single.defer<List<CardDataEntity>> {
            val updatesCursor = database.rawQuery(CardConstants.QUERY_GET_ALL_DATA, null)
            val list = mutableListOf<CardDataEntity>()

            while (updatesCursor.moveToNext()) {
                val cardData = mapper.parseCursor(updatesCursor)
                list.add(entityMapper.mapFromCached(cardData))
            }

            updatesCursor.close()
            Single.just<List<CardDataEntity>>(list)
        }
    }

    /**
     * Helper method for saving a [CachedCardData] instance to the database.
     */
    private fun saveCardData(cachedCardData: CachedCardData) {
        database.insert(Db.CardDataTable.TABLE_NAME, null, mapper.toContentValues(cachedCardData))
    }

    /**
     * Checked whether there are instances of [CachedCardData] stored in the cache
     */
    override fun isCached(): Boolean {
        return database.rawQuery(CardConstants.QUERY_GET_ALL_DATA, null).count > 0
    }

    /**
     * Set a point in time at when the cache was last updated
     */
    override fun setLastCacheTime(lastCache: Long) {
        preferencesHelper.lastCacheTime = lastCache
    }

    /**
     * Check whether the current cached data exceeds the defined [EXPIRATION_TIME] time
     */
    override fun isExpired(): Boolean {
        val currentTime = System.currentTimeMillis()
        val lastUpdateTime = this.getLastCacheUpdateTimeMillis()
        return currentTime - lastUpdateTime > EXPIRATION_TIME
    }

    /**
     * Get in millis, the last time the cache was accessed.
     */
    private fun getLastCacheUpdateTimeMillis(): Long {
        return preferencesHelper.lastCacheTime
    }

}