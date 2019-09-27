package com.harikesh.swipe.data

import io.reactivex.Completable
import io.reactivex.Single
import com.harikesh.swipe.data.mapper.CardDataMapper
import com.harikesh.swipe.data.model.CardDataEntity
import com.harikesh.swipe.data.source.CardDataStoreFactory
import com.harikesh.swipe.data.source.CardRemoteDataStore
import com.harikesh.swipe.domain.model.CardData
import com.harikesh.swipe.domain.repository.Repository
import javax.inject.Inject

/**
 * Provides an implementation of the [Repository] interface for communicating to and from
 * data sources
 */
class DataRepository @Inject constructor(private val factory: CardDataStoreFactory,
                                         private val cardDataMapper: CardDataMapper) :
        Repository {

    override fun clearCardData(): Completable {
        return factory.retrieveCacheDataStore().clearCardData()
    }

    override fun saveCardData(cardData: List<CardData>): Completable {
        val entities = cardData.map { cardDataMapper.mapToEntity(it) }
        return saveCardDataEntities(entities)
    }

    private fun saveCardDataEntities(list: List<CardDataEntity>): Completable {
        return factory.retrieveCacheDataStore().saveCardData(list)
    }

    override fun getCardData(): Single<List<CardData>> {
        val dataStore = factory.retrieveDataStore()
        return dataStore.getCardData()
                .flatMap {
                    if (dataStore is CardRemoteDataStore) {
                        saveCardDataEntities(it).toSingle { it }
                    } else {
                        Single.just(it)
                    }
                }
                .map { list ->
                    list.map { listItem ->
                        cardDataMapper.mapFromEntity(listItem)
                    }
                }
    }

}