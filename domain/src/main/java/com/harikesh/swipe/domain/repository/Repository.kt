package com.harikesh.swipe.domain.repository

import io.reactivex.Completable
import io.reactivex.Single
import com.harikesh.swipe.domain.model.CardData

/**
 * Interface defining methods for how the data layer can pass data to and from the Domain layer.
 * This is to be implemented by the data layer, setting the requirements for the
 * operations that need to be implemented
 */
interface Repository {

    fun clearCardData(): Completable

    fun saveCardData(cardData: List<CardData>): Completable

    fun getCardData(): Single<List<CardData>>

}