package com.harikesh.swipe.data.repository

import io.reactivex.Completable
import io.reactivex.Single
import com.harikesh.swipe.data.model.CardDataEntity

/**
 * Interface defining methods for the data operations related to CardData.
 * This is to be implemented by external data source layers, setting the requirements for the
 * operations that need to be implemented
 */
interface CardDataStore {

    fun clearCardData(): Completable

    fun saveCardData(list: List<CardDataEntity>): Completable

    fun getCardData(): Single<List<CardDataEntity>>

}