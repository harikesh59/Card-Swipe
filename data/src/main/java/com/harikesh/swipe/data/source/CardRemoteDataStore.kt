package com.harikesh.swipe.data.source

import io.reactivex.Completable
import io.reactivex.Single
import com.harikesh.swipe.data.model.CardDataEntity
import com.harikesh.swipe.data.repository.CardDataStore
import com.harikesh.swipe.data.repository.CardDataRemote
import javax.inject.Inject

/**
 * Implementation of the [CardDataStore] interface to provide a means of communicating
 * with the remote data source
 */
open class CardRemoteDataStore @Inject constructor(private val cardDataRemote: CardDataRemote) :
        CardDataStore {

    override fun clearCardData(): Completable {
        throw UnsupportedOperationException()
    }

    override fun saveCardData(list: List<CardDataEntity>): Completable {
        throw UnsupportedOperationException()
    }

    /**
     * Retrieve a list of [CardDataEntity] instances from the API
     */
    override fun getCardData(): Single<List<CardDataEntity>> {
        return cardDataRemote.getCardData()
    }

}