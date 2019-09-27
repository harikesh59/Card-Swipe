package com.harikesh.swipe.remote

import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.Single
import com.harikesh.swipe.data.model.CardDataEntity
import com.harikesh.swipe.remote.mapper.PojoEntityMapper
import com.harikesh.swipe.remote.test.factory.CardDataFactory
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class CardDataRemoteImplTest {

    private lateinit var entityMapper: PojoEntityMapper
    private lateinit var swipeApi: SwipeApi

    private lateinit var cardDataRemoteImpl: CardDataRemoteImpl

    @Before
    fun setup() {
        entityMapper = mock()
        swipeApi = mock()
        cardDataRemoteImpl = CardDataRemoteImpl(swipeApi, entityMapper)
    }

    @Test
    fun completes() {
        serviceGetCardData(Single.just(CardDataFactory.cardDataResponse()))
        val testObserver = cardDataRemoteImpl.getCardData().test()
        testObserver.assertComplete()
    }

    @Test
    fun returnsData() {
        val response = CardDataFactory.cardDataResponse()
        serviceGetCardData(Single.just(response))
        val entities = mutableListOf<CardDataEntity>()
        response.data.forEach {
            entities.add(entityMapper.mapFromRemote(it))
        }

        val testObserver = cardDataRemoteImpl.getCardData().test()
        testObserver.assertValue(entities)
    }
    //</editor-fold>

    private fun serviceGetCardData(single: Single<SwipeApi.CardDataResponse>) {
        whenever(swipeApi.getCardData())
                .thenReturn(single)
    }
}