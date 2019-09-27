package com.harikesh.swipe.presentation.browse

import io.reactivex.observers.DisposableSingleObserver
import com.harikesh.swipe.domain.interactor.SingleUseCase
import com.harikesh.swipe.domain.model.CardData
import com.harikesh.swipe.presentation.mapper.CardDataMapper
import javax.inject.Inject

class BrowsePresenter @Inject constructor(val browseView: BrowseContract.View,
                                          val useCase: SingleUseCase<List<CardData>, Void>,
                                          val mapper: CardDataMapper):
        BrowseContract.Presenter {

    init {
        browseView.setPresenter(this)
    }

    override fun start() {
        retrieveCard()
    }

    override fun stop() {
        useCase.dispose()
    }

    override fun retrieveCard() {
        useCase.execute(BrowserSubscriber())
    }

    internal fun handleOnSuccess(cardData: List<CardData>) {
        browseView.hideErrorState()
        if (cardData.isNotEmpty()) {
            browseView.hideEmptyState()
            browseView.showCardData(cardData.map { mapper.mapToView(it) })
        } else {
            browseView.hideCardData()
            browseView.showEmptyState()
        }
    }

    inner class BrowserSubscriber: DisposableSingleObserver<List<CardData>>() {

        override fun onSuccess(t: List<CardData>) {
            handleOnSuccess(t)
        }

        override fun onError(exception: Throwable) {
            browseView.hideCardData()
            browseView.hideEmptyState()
            browseView.showErrorState()
        }

    }

}