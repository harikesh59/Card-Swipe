package com.harikesh.swipe.domain.usecase.cardDataUseCase

import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.Single
import com.harikesh.swipe.domain.executor.PostExecutionThread
import com.harikesh.swipe.domain.executor.ThreadExecutor
import com.harikesh.swipe.domain.interactor.browse.GetCardData
import com.harikesh.swipe.domain.model.CardData
import com.harikesh.swipe.domain.repository.Repository
import com.harikesh.swipe.domain.test.factory.CardDataFactory
import org.junit.Before
import org.junit.Test

class GetCardDataTest {

    private lateinit var getCardData: GetCardData

    private lateinit var mockThreadExecutor: ThreadExecutor
    private lateinit var mockPostExecutionThread: PostExecutionThread
    private lateinit var mockRepository: Repository

    @Before
    fun setUp() {
        mockThreadExecutor = mock()
        mockPostExecutionThread = mock()
        mockRepository = mock()
        getCardData = GetCardData(mockRepository, mockThreadExecutor,
                mockPostExecutionThread)
    }

    @Test
    fun buildUseCaseObservableCallsRepository() {
        getCardData.buildUseCaseObservable(null)
        verify(mockRepository).getCardData()
    }

    @Test
    fun buildUseCaseObservableCompletes() {
        repositoryGetCardData(Single.just(CardDataFactory.list(2)))
        val testObserver = getCardData.buildUseCaseObservable(null).test()
        testObserver.assertComplete()
    }

    @Test
    fun buildUseCaseObservableReturnsData() {
        val list = CardDataFactory.list(2)
        repositoryGetCardData(Single.just(list))
        val testObserver = getCardData.buildUseCaseObservable(null).test()
        testObserver.assertValue(list)
    }

    private fun repositoryGetCardData(single: Single<List<CardData>>) {
        whenever(mockRepository.getCardData())
                .thenReturn(single)
    }

}